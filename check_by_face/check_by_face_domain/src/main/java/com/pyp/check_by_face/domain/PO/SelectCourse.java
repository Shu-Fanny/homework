package com.pyp.check_by_face.domain.PO;

import java.sql.Date;

public class SelectCourse {
    private String class_id;
    private String student_id;
    private String join_time;

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getJoin_time() {
        return join_time;
    }

    public void setJoin_time(String join_time) {
        this.join_time = join_time;
    }

    @Override
    public String toString() {
        return "SelectCourse{" +
                "class_id='" + class_id + '\'' +
                ", student_id='" + student_id + '\'' +
                ", join_time='" + join_time + '\'' +
                '}';
    }
}
