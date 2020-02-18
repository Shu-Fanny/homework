package com.pyp.check_by_face.domain.PO;

import java.util.List;

public class Role {

    private String id;
    private String roleName;
    private String roleDesc;
    //该角色具有的权限
    private List<Permission> permissions;
    //拥有该角色的教师
    private List<TeacherInfo> teacherInfos;
    //拥有该角色的管理员
    private List<SysManager> sysManagers;

    public Role() {
    }

    public Role(String id, String roleName, String roleDesc, List<Permission> permissions, List<TeacherInfo> teacherInfos, List<SysManager> sysManagers) {
        this.id = id;
        this.roleName = roleName;
        this.roleDesc = roleDesc;
        this.permissions = permissions;
        this.teacherInfos = teacherInfos;
        this.sysManagers = sysManagers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<TeacherInfo> getTeacherInfos() {
        return teacherInfos;
    }

    public void setTeacherInfos(List<TeacherInfo> teacherInfos) {
        this.teacherInfos = teacherInfos;
    }

    public List<SysManager> getSysManagers() {
        return sysManagers;
    }

    public void setSysManagers(List<SysManager> sysManagers) {
        this.sysManagers = sysManagers;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", permissions=" + permissions +
                ", teacherInfos=" + teacherInfos +
                ", sysManagers=" + sysManagers +
                '}';
    }
}
