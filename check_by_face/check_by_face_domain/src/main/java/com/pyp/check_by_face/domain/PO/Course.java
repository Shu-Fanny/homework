package com.pyp.check_by_face.domain.PO;

import jdk.nashorn.internal.parser.Token;

import java.util.List;

public class Course {
    private String course_id;
    private String course_name;
    private String course_picture;
    private String course_des;
    //教师外键
    private String t_job_number;
    //教师授课
    private TeacherInfo teachers;

    public Course() {
    }

    public String getCourse_id() {
        return course_id;
    }

    public void setCourse_id(String course_id) {
        this.course_id = course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getCourse_picture() {
        return course_picture;
    }

    public void setCourse_picture(String course_picture) {
        this.course_picture = course_picture;
    }

    public TeacherInfo getTeachers() {
        return teachers;
    }

    public void setTeachers(TeacherInfo teachers) {
        this.teachers = teachers;
    }

    public String getT_job_number() {
        return t_job_number;
    }

    public void setT_job_number(String t_job_number) {
        this.t_job_number = t_job_number;
    }

    public String getCourse_des() {
        return course_des;
    }

    public void setCourse_des(String course_des) {
        this.course_des = course_des;
    }

    @Override
    public String toString() {
        return "Course{" +
                "course_id='" + course_id + '\'' +
                ", course_name='" + course_name + '\'' +
                ", course_picture='" + course_picture + '\'' +
                ", course_des='" + course_des + '\'' +
                ", t_job_number='" + t_job_number + '\'' +
                ", teachers=" + teachers +
                '}';
    }
}
