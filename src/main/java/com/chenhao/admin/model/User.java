package com.chenhao.admin.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by chenhao on 2015/9/7.
 */
public class User {

    private int id;
    @NotBlank(message = "中文名称不能为空")
    private String name;
    @NotBlank(message = "英文名称不能为空")
    private String ename;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotNull(message = "年龄不能为空")
    private int age;
    @NotNull(message = "生日不能为空")
    @JsonFormat(pattern = "yyy-MM-dd")
    private Date birthday;
    @NotBlank(message = "性别不能为空")
    private String sex;
    @NotBlank(message = "角色不能为空")
    private String role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
