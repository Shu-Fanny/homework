package com.pyp.check_by_face.services.impl;
import com.pyp.check_by_face.dao.IAttendenceDao;
import com.pyp.check_by_face.dao.IStudentDao;
import com.pyp.check_by_face.dao.ITeacherDao;
import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.Attendance;
import com.pyp.check_by_face.domain.VO.ClassOfStudentInfo;
import com.pyp.check_by_face.services.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {

    @Autowired
    private ITeacherDao teacherDao;
    @Autowired
    private IStudentDao studentDao;
    @Autowired
    private IAttendenceDao attendenceDao;

    @Override
    public TeacherInfo findTeacherById(String tJodNumber) throws Exception {
        return teacherDao.findTeacherById(tJodNumber);
    }

    /*
     *通过教师Tid查询授课信息
     *@t_job_number：session域中的数据
     */
    @Override
    public List<Course> findCourseByTid(String t_job_number) throws Exception {
        return teacherDao.findCourseByTid(t_job_number);
    }

    @Override
    public void addCourseToTeacher(Course course) throws Exception {
        teacherDao.addCourseToTeacher(course);
    }

    /*
     *教师添加班级到课程中--添加记录到 classinfo ，方便学生考勤表的动态创建
     */
    @Override
    public void addClassOfCourse(String course_id, String className) throws Exception {
        teacherDao.addClassOfCourse(course_id,className);
        teacherDao.creatAttendanceByDataName(course_id+"_"+className);
    }

    /**
     * 通过某门课程的id找到其授课班级
     */
    @Override
    public List<ClassInfo> findClassByCourseId(String course_id) throws Exception {
        return teacherDao.findClassByCourseId(course_id);
    }

    /*
     *教师为某门课程下的某个班级id导入学生名单
     */
    @Override
    public void addStudentToClassOfCourse(SelectCourse selectCourse) throws Exception {
            studentDao.addCourseBySid(selectCourse);
    }

    @Override
    public String findClassNameByClassId(String class_id) {
        return attendenceDao.findClassNameByClassId(class_id);
    }

    /**
     * 通过某门课程授课班级的ID找到该班级下的学生
     */
    @Override
    public List<ClassOfStudentInfo> findStudentByClassId(String class_id) throws Exception {
        //找到某个班级下的所有学生
        List<ClassOfStudentInfo> students = teacherDao.findStudentByClassId(class_id);
        return students;
    }



    /**
     * 根据拿到的课程id和班级id找某个班的考勤记录
     * @param course_id
     * @param class_id
     * @return
     * @throws Exception
     */
    @Override
    public List<Attendance> findAttendancesByClassIdAndCourseId(String course_id, String class_id) throws Exception {
        //1、在 select_courses 获取该门课程该班级的学生名单
        //此处不该如此变化，日后有时间再改
        List<ClassOfStudentInfo> students = teacherDao.findStudentByClassId(class_id);
        //2、根据拿到的学生名单,以 课程id+班级id 作为数据表名，在其寻找该名同学的所有考勤信息
        String className = attendenceDao.findClassNameByClassId(class_id);
        List<Attendance> attendances = new ArrayList<>();
        String dataName = course_id+"_"+className;
        for (ClassOfStudentInfo student:students) {
            Attendance attendance = new Attendance();
            //2.1、设置学生学号
            attendance.setStudent_id(student.getStudent_id());
            //2.2、设置学生姓名
            attendance.setStudent_name(student.getStudent_name());
            //2.3、设置该学生出勤的次数
            attendance.setNormal(attendenceDao.findNormalBySid(dataName,student.getStudent_id()));
            //2.4、设置该学生旷课的次数
            attendance.setAbsence(attendenceDao.findAbsenceBySid(dataName,student.getStudent_id()));
            //2.5、设置该学生迟到的次数
            attendance.setLate(attendenceDao.findLateBySid(dataName,student.getStudent_id()));
            //2.6、设置该学生病假的次数
            attendance.setSick_leave(attendenceDao.findSickLeaveBySid(dataName,student.getStudent_id()));
            //2.7、设置该学生事假的次数
            attendance.setCasual_leave(attendenceDao.findCasualLeaveBySid(dataName,student.getStudent_id()));
            //3、将此次考勤信息记录到添加到考勤集合
            attendances.add(attendance);
        }
        return attendances;
    }


    @Override
    public void deleteCourseByTidAndCid(String tid, String cid) throws Exception {

    }
    @Override
    public void updateCourseByTidAndCid(String tid, String cid) throws Exception {

    }


    /**
     * 添加学生的步骤
     */
    /**
     * 第一步，选择学院
     * @return
     * @throws Exception
     */
    @Override
    public List<String> findAllDepartment() throws Exception {
        return studentDao.findAllDepartment();
    }

    /**
     * 第二步，根据学院名称选择专业
     * @param department
     * @return
     * @throws Exception
     */
    @Override
    public List<String> findMajorByDepartmentName(String department) throws Exception {
        return studentDao.findMajorByDepartmentName(department);
    }
    /**
     * 第三步，根据专业名称选择班级
     * @param major
     * @return
     * @throws Exception
     */
    @Override
    public List<String> findAllClazzByMajor(String major) throws Exception {
        return studentDao.findAllClazzByMajor(major);
    }
    /**
     * 第三步，根据班级名称找到该班级下所有学生
     * @param class_name
     * @return
     * @throws Exception
     */
    @Override
    public List<Student> findStudentByClazzName(String class_name) throws Exception {
        return studentDao.findStudentByClazzName(class_name);
    }

    @Override
    public void addAttendanceToClassStudents(List<ClassOfStudentInfo> studentInfos, String dataTableName, String format,String status) throws Exception {
        for (ClassOfStudentInfo studentInfo:studentInfos){
            String student_id = studentInfo.getStudent_id();
            studentDao.addAttenceByStudentID(dataTableName,student_id,format,status);
        }
    }

    @Override
    public void updateAttendanceToClassStudent(String dataTableName, String student_id, String format, String att_statu) throws Exception {
        studentDao.updateAttendanceToClassStudent(dataTableName,student_id,format,att_statu);
    }

    @Override
    public String findSidByDateAndStatu(String dataTableName,String statu ,String date,String student_id) throws Exception {
        return teacherDao.findSidByDateAndStatu(dataTableName,statu,date,student_id);
    }

    @Override
    public List<Kaoqin_change> findAttendanceChange(String class_id,String state) throws Exception {
        return teacherDao.findAttendanceChange(class_id,state);
    }

    @Override
    public void updateAttendanceChange(Kaoqin_change kaoqin_change) throws Exception {
        teacherDao.updateAttendanceChange(kaoqin_change);
    }

    @Override
    public void updateTeacherDetails(TeacherInfo teacher) throws Exception {
        teacherDao.updateTeacherDetails(teacher);
    }

    @Override
    public void updateTeacherImage(TeacherInfo teacher) throws Exception {
        teacherDao.updateTeacherImage(teacher);
    }

    @Override
    public void updateTeacherPwd(TeacherInfo teacher) throws Exception {
        teacherDao.updateTeacherPwd(teacher);
    }

    @Override
    public List<Student> findTeacherByMajor(String major) throws Exception {
        return teacherDao.findTeacherByMajor(major);
    }

}
