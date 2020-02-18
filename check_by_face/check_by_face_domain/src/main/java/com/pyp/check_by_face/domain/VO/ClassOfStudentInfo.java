package com.pyp.check_by_face.domain.VO;

public class ClassOfStudentInfo {
    private String student_name;
    private String student_id;
    private String department;
    private String major;
    private String class_name;
    private String join_time;

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
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

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }

    @Override
    public String toString() {
        return "ClassOfStudentInfo{" +
                "student_name='" + student_name + '\'' +
                ", student_id='" + student_id + '\'' +
                ", department='" + department + '\'' +
                ", major='" + major + '\'' +
                ", class_name='" + class_name + '\'' +
                ", join_time='" + join_time + '\'' +
                '}';
    }
}
