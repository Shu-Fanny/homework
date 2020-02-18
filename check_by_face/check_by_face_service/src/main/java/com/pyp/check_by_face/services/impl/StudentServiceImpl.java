package com.pyp.check_by_face.services.impl;

import com.pyp.check_by_face.dao.IAttendenceDao;
import com.pyp.check_by_face.dao.IStudentDao;
import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.Attendance;
import com.pyp.check_by_face.domain.VO.StuCourseAndClassId;
import com.pyp.check_by_face.services.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private IStudentDao studentDao;
    @Autowired
    private IAttendenceDao attendenceDao;

    @Override
    public Student findBySid(String s_job_number) throws Exception {
        return studentDao.findBySid(s_job_number);
    }

    /**
     * 通过某门课程的id找到其授课班级
     */
    @Override
    public void addCourseBySid(SelectCourse selectCourse) throws Exception {
        studentDao.addCourseBySid(selectCourse);
    }

    @Override
    public void deleteCourseBySid() throws Exception {

    }

    /**
     * 更改学生登陆密码
     */
    @Override
    public void updateStudentPassword(String student_id, String student_pwd) throws Exception{
        studentDao.updateStudentPassword(student_id,student_pwd);
    }

    @Override
    public Course findCourseBySid(String class_id) throws Exception {
        return studentDao.findCourseBySid(class_id);
    }

    @Override
    public List<String> findClassIdByStudentId(String student_id) throws Exception {
        return studentDao.findClassIdByStudentId(student_id);
    }

    @Override
    public Attendance findAttendanceBySid(String dataName, String student_id) throws Exception {
        Attendance attendance = new Attendance();
        attendance.setNormal(attendenceDao.findNormalBySid(dataName,student_id));
        attendance.setAbsence(attendenceDao.findAbsenceBySid(dataName,student_id));
        attendance.setCasual_leave(attendenceDao.findCasualLeaveBySid(dataName,student_id));
        attendance.setSick_leave(attendenceDao.findSickLeaveBySid(dataName,student_id));
        attendance.setLate(attendenceDao.findLateBySid(dataName,student_id));
        return attendance;
    }

    @Override
    public List<EveryAttendance> findEveryAttendanceBySid(String dataName, String student_id) throws Exception {
        return attendenceDao.findEveryAttendanceBySid(dataName,student_id);
    }

    @Override
    public void addAttendanceChange(Kaoqin_change kaoqin_change) throws Exception {
        studentDao.addAttendanceChange(kaoqin_change);
    }

    @Override
    public List<EveryAttendance> findAttendanceBySidAndAid(String dataName, String student_id, String att_statu) throws Exception {
        return attendenceDao.findAttendanceBySidAndAid(dataName,student_id,att_statu);
    }

    @Override
    public void updateStudent_face_info(String student_id) throws Exception {
        studentDao.updateStudent_face_info(student_id);
    }
}
