package com.wxt.serializable;

import java.io.Serializable;

/**
 * @Auther: ThomasWu
 * @Date: 2021/5/30 11:42
 * @Description:
 */
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String age;

    private String address;

    private String sex;

    public User(String name, String age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

}
