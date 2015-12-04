package com.chenhao.admin.controller;

import com.alibaba.fastjson.JSON;
import com.chenhao.admin.model.User;
import com.chenhao.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by chenhao on 2015/9/7.
 */

@Controller
@RequestMapping("/userController")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/queryUserById")
    @ResponseBody
    public Map<String, Object> queryUserById(int id) {
        Map<String, Object> res = new HashMap();
        User user = userService.queryUserById(id);
        res.put("data", JSON.toJSONString(user));
        res.put("status", "success");
        return res;
    }

    @RequestMapping("/queryUser")
    @ResponseBody
    public Map<String, Object> queryUser(@RequestBody Map<String, Object> map) {
        Map<String, Object> res = new HashMap();
        List<User> users = userService.queryUser(map);
        res.put("status", "success");
        res.put("data", JSON.toJSONString(users));
        res.put("count", map.get("count"));
        return res;
    }

    @RequestMapping("/insertUser")
    @ResponseBody
    public Map<String, Object> insertUser(@RequestBody @Valid User user) {
        Map<String, Object> res = new HashMap();
        userService.insertUser(user);
        res.put("status", "success");
        return res;
    }

    @RequestMapping("/updateUser")
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody @Valid User user) {
        Map<String, Object> res = new HashMap();
        userService.updateUser(user);
        res.put("status", "success");
        return res;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(@RequestBody ArrayList<Integer> ids) {
        Map<String, Object> res = new HashMap();
        userService.deleteUser(ids);
        res.put("status", "success");
        return res;
    }


}
