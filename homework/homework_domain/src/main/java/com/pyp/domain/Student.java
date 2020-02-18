package com.pyp.domain;

import java.util.List;

public class Student {
    private String sid;
    private String s_username;
    private String s_password;
    private String s_name;
    private String s_age;
    private String s_phone;
    private List<HomeWork> homeWorks;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getS_username() {
        return s_username;
    }

    public void setS_username(String s_username) {
        this.s_username = s_username;
    }

    public String getS_password() {
        return s_password;
    }

    public void setS_password(String s_password) {
        this.s_password = s_password;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_age() {
        return s_age;
    }

    public void setS_age(String s_age) {
        this.s_age = s_age;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public List<HomeWork> getHomeWorks() {
        return homeWorks;
    }

    public void setHomeWorks(List<HomeWork> homeWorks) {
        this.homeWorks = homeWorks;
    }


    @Override
    public String toString() {
        return "Student{" +
                "sid='" + sid + '\'' +
                ", s_username='" + s_username + '\'' +
                ", s_password='" + s_password + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_age='" + s_age + '\'' +
                ", s_phone='" + s_phone + '\'' +
                ", homeWorks=" + homeWorks +
                '}';
    }
}
