package com.pyp.domain;

public class Teacher {
    private String tid;
    private String t_username;
    private String t_password;
    private String t_name;
    private String t_age;
    private String t_phone;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getT_username() {
        return t_username;
    }

    public void setT_username(String t_username) {
        this.t_username = t_username;
    }

    public String getT_password() {
        return t_password;
    }

    public void setT_password(String t_password) {
        this.t_password = t_password;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_age() {
        return t_age;
    }

    public void setT_age(String t_age) {
        this.t_age = t_age;
    }

    public String getT_phone() {
        return t_phone;
    }

    public void setT_phone(String t_phone) {
        this.t_phone = t_phone;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "tid='" + tid + '\'' +
                ", t_username='" + t_username + '\'' +
                ", t_password='" + t_password + '\'' +
                ", t_name='" + t_name + '\'' +
                ", t_age='" + t_age + '\'' +
                ", t_phone='" + t_phone + '\'' +
                '}';
    }
}
