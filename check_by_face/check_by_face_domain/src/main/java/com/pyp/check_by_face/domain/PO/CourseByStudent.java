package com.pyp.check_by_face.domain.PO;

import java.sql.Date;

public class CourseByStudent {
    private String student_id;
    private Date att_time;
    private String att_statu;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public Date getAtt_time() {
        return att_time;
    }

    public void setAtt_time(Date att_time) {
        this.att_time = att_time;
    }

    public String getAtt_statu() {
        return att_statu;
    }

    public void setAtt_statu(String att_statu) {
        this.att_statu = att_statu;
    }

    @Override
    public String toString() {
        return "CourseByStudent{" +
                "student_id='" + student_id + '\'' +
                ", att_time=" + att_time +
                ", att_statu='" + att_statu + '\'' +
                '}';
    }
}
