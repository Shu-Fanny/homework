package com.pyp.check_by_face.domain.PO;

public class Kaoqin_change {
    private String id;              //主键，唯一标识
    private String apply_time;          //申请时间
    private String student_id;          //学生id
    private String statue_before_change;//更改前的考勤状态
    private String statue_after_change;   //更改后的考勤状态
    private String reason;  //申请理由
    private String support_material;   //佐证材料
    private String statue_if_agree; //是否同意
    private String reason_not_agree; //不同意理由
    private String class_id; //哪个班级的同学提交的
    private String state; //是否审核

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getStatue_before_change() {
        return statue_before_change;
    }

    public void setStatue_before_change(String statue_before_change) {
        this.statue_before_change = statue_before_change;
    }

    public String getStatue_after_change() {
        return statue_after_change;
    }

    public void setStatue_after_change(String statue_after_change) {
        this.statue_after_change = statue_after_change;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSupport_material() {
        return support_material;
    }

    public void setSupport_material(String support_material) {
        this.support_material = support_material;
    }

    public String getStatue_if_agree() {
        return statue_if_agree;
    }

    public void setStatue_if_agree(String statue_if_agree) {
        this.statue_if_agree = statue_if_agree;
    }

    public String getReason_not_agree() {
        return reason_not_agree;
    }

    public void setReason_not_agree(String reason_not_agree) {
        this.reason_not_agree = reason_not_agree;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Kaoqin_change{" +
                "id='" + id + '\'' +
                ", apply_time='" + apply_time + '\'' +
                ", student_id='" + student_id + '\'' +
                ", statue_before_change='" + statue_before_change + '\'' +
                ", statue_after_change='" + statue_after_change + '\'' +
                ", reason='" + reason + '\'' +
                ", support_material='" + support_material + '\'' +
                ", statue_if_agree='" + statue_if_agree + '\'' +
                ", reason_not_agree='" + reason_not_agree + '\'' +
                ", class_id='" + class_id + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
