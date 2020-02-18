package com.pyp.check_by_face.domain.VO;

/**
 * 单个学生在某门课程中的总考勤统计表
 */
public class Attendance {
    private String student_id;
    private String Student_name;
    private int normal;  //出勤
    private int absence; //缺勤
    private int late;   //迟到
    private int casual_leave;  //事假
    private int sick_leave;   //病假

    //private String att_time; //考勤时间
    ////所在表
    //private String class_id;
    //private String className;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return Student_name;
    }

    public void setStudent_name(String student_name) {
        Student_name = student_name;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getAbsence() {
        return absence;
    }

    public void setAbsence(int absence) {
        this.absence = absence;
    }

    public int getLate() {
        return late;
    }

    public void setLate(int late) {
        this.late = late;
    }

    public int getCasual_leave() {
        return casual_leave;
    }

    public void setCasual_leave(int casual_leave) {
        this.casual_leave = casual_leave;
    }

    public int getSick_leave() {
        return sick_leave;
    }

    public void setSick_leave(int sick_leave) {
        this.sick_leave = sick_leave;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "student_id='" + student_id + '\'' +
                ", Student_name='" + Student_name + '\'' +
                ", normal=" + normal +
                ", absence=" + absence +
                ", late=" + late +
                ", casual_leave=" + casual_leave +
                ", sick_leave=" + sick_leave +
                '}';
    }
}
