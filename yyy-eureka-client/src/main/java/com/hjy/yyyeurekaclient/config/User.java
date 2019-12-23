package com.hjy.yyyeurekaclient.config;

import java.io.Serializable;

public class User implements Serializable {

    private String username;
    private int age;
    private String mail;
    private int sex;

    public User(String username, int age, String mail, int sex) {
        this.username = username;
        this.age = age;
        this.mail = mail;
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
