package com.pyp.check_by_face.domain.PO;

import java.util.List;

public class SysManager {
    private String s_job_number;
    private String s_name;
    private String s_pwd;
    private String s_sex;
    private String s_head_image;
    private String s_email;
    private String s_tel;

    public SysManager() {
    }

    public SysManager(String s_job_number, String s_name, String s_pwd, String s_sex, String s_head_image, String s_email, String s_tel) {
        this.s_job_number = s_job_number;
        this.s_name = s_name;
        this.s_pwd = s_pwd;
        this.s_sex = s_sex;
        this.s_head_image = s_head_image;
        this.s_email = s_email;
        this.s_tel = s_tel;
    }

    public String getS_job_number() {
        return s_job_number;
    }

    public void setS_job_number(String s_job_number) {
        this.s_job_number = s_job_number;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_pwd() {
        return s_pwd;
    }

    public void setS_pwd(String s_pwd) {
        this.s_pwd = s_pwd;
    }

    public String getS_sex() {
        return s_sex;
    }

    public void setS_sex(String s_sex) {
        this.s_sex = s_sex;
    }

    public String getS_head_image() {
        return s_head_image;
    }

    public void setS_head_image(String s_head_image) {
        this.s_head_image = s_head_image;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_tel() {
        return s_tel;
    }

    public void setS_tel(String s_tel) {
        this.s_tel = s_tel;
    }

    @Override
    public String toString() {
        return "SysManager{" +
                "s_job_number='" + s_job_number + '\'' +
                ", s_name='" + s_name + '\'' +
                ", s_pwd='" + s_pwd + '\'' +
                ", s_sex='" + s_sex + '\'' +
                ", s_head_image='" + s_head_image + '\'' +
                ", s_email='" + s_email + '\'' +
                ", s_tel='" + s_tel + '\'' +
                '}';
    }
}
