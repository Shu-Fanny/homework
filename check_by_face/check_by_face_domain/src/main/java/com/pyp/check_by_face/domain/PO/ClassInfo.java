package com.pyp.check_by_face.domain.PO;

/**
 * 记录某门课程中的某门班级
 */
public class ClassInfo {
    private int class_id;
    //该班级对应的课程
    private int course_id;
    private String classname;
    //课程类
    private Course course;

    public int getClass_id() {
        return class_id;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public int getCourse_id() {
        return course_id;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "ClassInfo{" +
                "class_id=" + class_id +
                ", course_id=" + course_id +
                ", classname='" + classname + '\'' +
                ", course=" + course +
                '}';
    }
}
