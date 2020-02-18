package com.pyp.domain;

public class StuWork {
    private Student student;
    private HomeWork homeWork;
    private String state;
    private String score;
    private String file_path;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public HomeWork getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(HomeWork homeWork) {
        this.homeWork = homeWork;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    @Override
    public String toString() {
        return "StuWork{" +
                "student=" + student +
                ", homeWork=" + homeWork +
                ", state='" + state + '\'' +
                ", score='" + score + '\'' +
                ", file_path='" + file_path + '\'' +
                '}';
    }
}
