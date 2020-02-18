package com.pyp.check_by_face.services.impl;

import com.pyp.check_by_face.dao.IManagerDao;
import com.pyp.check_by_face.domain.PO.Student;
import com.pyp.check_by_face.domain.PO.SysManager;
import com.pyp.check_by_face.domain.PO.TeacherInfo;
import com.pyp.check_by_face.services.IManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements IManagerService {

    @Autowired
    private IManagerDao managerDao;


    @Override
    public SysManager findManagerById(String tJodNumber)throws Exception{
        return managerDao.findManagerById(tJodNumber);
    }

    @Override
    public List<TeacherInfo> findTeachersInfo() throws Exception {
        return managerDao.findTeachersInfo();
    }

    @Override
    public List<Student> findStudentsInfo() throws Exception {
        return managerDao.findStudentsInfo();
    }

    @Override
    public void updateTeacherInfo(String t_name,String t_job_number,String department,String major) throws Exception {
        System.out.println(t_name+"-"+t_job_number+"-"+department+"-"+major);
        managerDao.updateTeacherInfo(t_name,t_job_number,department,major);
    }

    @Override
    public void addTeacherInfo(TeacherInfo teacherInfo) throws Exception {
        managerDao.addTeacherInfo(teacherInfo);
    }

    @Override
    public void deleteTeacherInfo(String t_job_number) throws Exception {
        managerDao.deleteTeacherInfo(t_job_number);
    }

    @Override
    public void updateStudentsInfo(String student_name, String student_id, String department, String major, String class_name) throws Exception {
        managerDao.updateStudentsInfo(student_name,student_id,department,major,class_name);
    }

    @Override
    public void addStudentsInfo(Student student) throws Exception {
        managerDao.addStudentsInfo(student);
    }

    @Override
    public void deleteStudentsInfo(String student_id) throws Exception {
        managerDao.deleteStudentsInfo(student_id);
    }
}
