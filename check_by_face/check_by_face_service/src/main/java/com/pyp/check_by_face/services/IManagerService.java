package com.pyp.check_by_face.services;

import com.pyp.check_by_face.domain.PO.Student;
import com.pyp.check_by_face.domain.PO.SysManager;
import com.pyp.check_by_face.domain.PO.TeacherInfo;

import java.util.List;

public interface IManagerService {
    SysManager findManagerById(String tJodNumber)throws Exception;

    List<TeacherInfo> findTeachersInfo()throws Exception;

    List<Student> findStudentsInfo()throws Exception;

    void updateTeacherInfo(String t_name,String t_job_number,String department,String major)throws Exception;

    void addTeacherInfo(TeacherInfo teacherInfo)throws Exception;

    void deleteTeacherInfo(String t_job_number)throws Exception;

    void updateStudentsInfo(String student_name, String student_id, String department, String major, String class_name)throws Exception;

    void addStudentsInfo(Student student)throws Exception;

    void deleteStudentsInfo(String student_id)throws Exception;
}
