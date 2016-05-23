package com.chenhao.admin.controller;

import com.chenhao.admin.model.Response;
import com.chenhao.admin.model.User;
import com.chenhao.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import javax.validation.Valid;
import java.util.*;


/**
 * Created by chenhao on 2015/9/7.
 */

@Controller
@RequestMapping("/userController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private ShardedJedisPool shardedJedisPool;

    @RequestMapping("/login")
    @ResponseBody
    public Response login(@RequestBody Map<String, Object> map) {
        Response res = new Response();
        List<Map<String, Object>> users = userService.login(map);
        if (users == null || users.size() == 0) {
            return res.failure("登陆失败");
        }
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        String skey = "session:" + users.get(0).get("id");
        String token = UUID.randomUUID().toString();
        if (shardedJedis.exists(skey)) {
            String oldtoken = shardedJedis.get(skey);
            shardedJedis.del(skey);
            shardedJedis.del("token:" + oldtoken);
        }
        if (shardedJedis.setnx(skey, token) == 1) {
            shardedJedis.expire(skey, 3600 * 24);
            shardedJedis.hset("token:" + token, "name", (String) users.get(0).get("name"));
            shardedJedis.expire("token:" + token, 3600 * 24);
        } else {
            token = shardedJedis.get(skey);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        return new Response().success(data);
    }

    @RequestMapping("/queryUserById")
    @ResponseBody
    public Response queryUserById(int id) {
        User user = userService.queryUserById(id);
        return new Response().success(user);
    }

    @RequestMapping("/queryUser")
    @ResponseBody
    public Response queryUser(@RequestBody Map<String, Object> map) {
        Response res = new Response();
        List<User> users = userService.queryUser(map);
        if (map.containsKey("count")) {
            res.setCount((Integer) map.get("count"));
        }
        res.success(users);
        return res;
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Response insertUser(@RequestBody @Valid User user) {
        userService.insertUser(user);
        return new Response().success("新增用户成功");
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Response updateUser(@RequestBody @Valid User user) {
        userService.updateUser(user);
        return new Response().success("修改用户成功");
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Response deleteUser(@RequestBody ArrayList<Integer> ids) {
        userService.deleteUser(ids);
        return new Response().success("删除用户成功");
    }


}
