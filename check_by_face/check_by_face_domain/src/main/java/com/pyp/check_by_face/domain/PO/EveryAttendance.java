package com.pyp.check_by_face.domain.PO;

public class EveryAttendance {
    private String att_time;
    private String att_statu;
    private String student_id;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getAtt_time() {
        return att_time;
    }

    public void setAtt_time(String att_time) {
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
        return "EveryAttendance{" +
                "att_time='" + att_time + '\'' +
                ", att_statu='" + att_statu + '\'' +
                ", student_id='" + student_id + '\'' +
                '}';
    }
}
