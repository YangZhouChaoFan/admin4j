package com.chenhao.admin.service;

import com.chenhao.admin.dao.UserMapper;
import com.chenhao.admin.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhao on 2015/9/7.
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    public UserMapper userDao;

    @Override
    public User queryUserById(int id) {
        return userDao.queryUserById(id);
    }

    @Override
    public List<User> queryUser(Map<String, Object> map) {
        return userDao.queryUser(map);
}

    @Override
    public void insertUser(User user) {
        userDao.insertUser(user);
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public void deleteUser(ArrayList<Integer> ids) {
        userDao.deleteUser(ids);
    }

    @Override
    public List<Map<String, Object>> login(Map<String, Object> map) {
        return userDao.login(map);
    }

}
