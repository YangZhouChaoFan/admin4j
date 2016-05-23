package com.chenhao.admin.dao;

import com.chenhao.admin.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by chenhao on 2015/9/7.
 */

public interface UserMapper {

    User queryUserById(int id);

    List<User> queryUser(Map<String, Object> map);

    void insertUser(User user);

    void updateUser(User user);

    void deleteUser(ArrayList<Integer> ids);

    List<Map<String,Object>> login(Map<String, Object> map);
}
