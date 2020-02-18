package com.pyp.check_by_face.domain.PO;

import java.util.List;

public class TeacherInfo {
    private String t_job_number;
    private String t_name;
    private String t_pwd;
    private String t_email;
    private String t_tel;
    private String t_sex;
    private String t_head_image;
    private String department;
    private String major;

    //教师授课
    private List<Course> courses;

    public String getT_job_number() {
        return t_job_number;
    }

    public void setT_job_number(String t_job_number) {
        this.t_job_number = t_job_number;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_pwd() {
        return t_pwd;
    }

    public void setT_pwd(String t_pwd) {
        this.t_pwd = t_pwd;
    }

    public String getT_email() {
        return t_email;
    }

    public void setT_email(String t_email) {
        this.t_email = t_email;
    }

    public String getT_tel() {
        return t_tel;
    }

    public void setT_tel(String t_tel) {
        this.t_tel = t_tel;
    }

    public String getT_sex() {
        return t_sex;
    }

    public void setT_sex(String t_sex) {
        this.t_sex = t_sex;
    }

    public String getT_head_image() {
        return t_head_image;
    }

    public void setT_head_image(String t_head_image) {
        this.t_head_image = t_head_image;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Override
    public String toString() {
        return "TeacherInfo{" +
                "t_job_number='" + t_job_number + '\'' +
                ", t_name='" + t_name + '\'' +
                ", t_pwd='" + t_pwd + '\'' +
                ", t_email='" + t_email + '\'' +
                ", t_tel='" + t_tel + '\'' +
                ", t_sex='" + t_sex + '\'' +
                ", t_head_image='" + t_head_image + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", courses=" + courses +
                '}';
    }
}
