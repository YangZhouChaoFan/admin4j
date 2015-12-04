package com.chenhao.admin.service;


import com.chenhao.admin.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chenhao on 2015/9/7.
 */
public interface UserService {

    User queryUserById(int id);

    List<User> queryUser(Map<String, Object> map);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(ArrayList<Integer> ids);
}
