package com.pyp.check_by_face.domain.VO;

public class DepMajorClazz {
    private String dep;
    private String major;
    private String clazz;

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "DepMajorClazz{" +
                "dep='" + dep + '\'' +
                ", major='" + major + '\'' +
                ", clazz='" + clazz + '\'' +
                '}';
    }
}
