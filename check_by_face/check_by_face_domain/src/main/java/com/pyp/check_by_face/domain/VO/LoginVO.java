package com.pyp.check_by_face.domain.VO;

public class LoginVO {
    private String role;
    private String tJodNumber;
    private String t_pwd;

    public LoginVO() {
    }

    public LoginVO(String role, String tJodNumber, String t_pwd) {
        this.role = role;
        this.tJodNumber = tJodNumber;
        this.t_pwd = t_pwd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String gettJodNumber() {
        return tJodNumber;
    }

    public void settJodNumber(String tJodNumber) {
        this.tJodNumber = tJodNumber;
    }

    public String getT_pwd() {
        return t_pwd;
    }

    public void setT_pwd(String t_pwd) {
        this.t_pwd = t_pwd;
    }


    @Override
    public String toString() {
        return "LoginVO{" +
                "role='" + role + '\'' +
                ", tJodNumber='" + tJodNumber + '\'' +
                ", t_pwd='" + t_pwd + '\'' +
                '}';
    }
}
