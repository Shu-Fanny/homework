package com.pyp.domain;

import java.util.List;

public class HomeWork {
    private String hid;
    private String title;
    private String desc;
    private String file_path;
    private List<Student> students;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "HomeWork{" +
                "hid='" + hid + '\'' +
                ", title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", file_path='" + file_path + '\'' +
                ", students=" + students +
                '}';
    }
}
