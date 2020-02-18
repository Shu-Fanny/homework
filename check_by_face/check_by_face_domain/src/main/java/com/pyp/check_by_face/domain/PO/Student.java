package com.pyp.check_by_face.domain.PO;

public class Student {
    private String student_id;
    private String student_pwd;
    private String student_name;
    private String student_sex;
    private String student_tel;
    private String department;
    private String major;
    private String class_name;
    private String student_head_image;
    private int student_face_info;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_pwd() {
        return student_pwd;
    }

    public void setStudent_pwd(String student_pwd) {
        this.student_pwd = student_pwd;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_sex() {
        return student_sex;
    }

    public void setStudent_sex(String student_sex) {
        this.student_sex = student_sex;
    }

    public String getStudent_tel() {
        return student_tel;
    }

    public void setStudent_tel(String student_tel) {
        this.student_tel = student_tel;
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

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getStudent_head_image() {
        return student_head_image;
    }

    public void setStudent_head_image(String student_head_image) {
        this.student_head_image = student_head_image;
    }

    public int getStudent_face_info() {
        return student_face_info;
    }

    public void setStudent_face_info(int student_face_info) {
        this.student_face_info = student_face_info;
    }


    @Override
    public String toString() {
        return "Student{" +
                "student_id='" + student_id + '\'' +
                ", student_pwd='" + student_pwd + '\'' +
                ", student_name='" + student_name + '\'' +
                ", student_sex='" + student_sex + '\'' +
                ", student_tel='" + student_tel + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", class_name='" + class_name + '\'' +
                ", student_head_image='" + student_head_image + '\'' +
                ", student_face_info=" + student_face_info +
                '}';
    }
}
