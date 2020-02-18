package com.pyp.check_by_face.services;

import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.Attendance;
import com.pyp.check_by_face.domain.VO.ClassOfStudentInfo;

import java.util.Date;
import java.util.List;

public interface ITeacherService {
    /*
     *教师登陆
     */
    TeacherInfo findTeacherById(String tJodNumber)throws Exception;

    /*
     *通过教师Tid查询授课信息
     *@t_job_number：session域中的数据
     */
    List<Course> findCourseByTid(String t_job_number)throws Exception;

    /*
     * 根据教师id添加课程
     * @course: 表单提交信息
     * @tid:session域中的数据
     */
    void addCourseToTeacher(Course course)throws Exception;

    /*
     * 根据教师tid删除课程
     * @cid: 课程信息带的数据，查找课程的时候将其作为json发送过去
     * @tid:session域中的数据
     */
    void deleteCourseByTidAndCid(String tid,String cid) throws Exception;

    /*
     * 根据教师id修改课程信息
     * @cid: 课程信息带的数据，查找课程的时候将其作为json发送过去
     * @tid:session域中的数据
     */
    void updateCourseByTidAndCid(String tid,String cid) throws  Exception;

    void addClassOfCourse(String course_id, String className)throws Exception;

    List<ClassInfo> findClassByCourseId(String course_id)throws Exception;

    /*
     *教师为某门课程下的某个班级id导入学生名单
     */
    void addStudentToClassOfCourse(SelectCourse selectCourse) throws  Exception;

    /**
     * 根据classid 找到其名称
     * @param class_id
     * @return
     */
    String findClassNameByClassId(String class_id);

    /**
     * 通过某门课程授课班级的ID找到该班级下的学生
     */
    List<ClassOfStudentInfo> findStudentByClassId(String class_id)throws Exception;

    List<Attendance> findAttendancesByClassIdAndCourseId(String course_id, String class_id)throws  Exception;


    List<String> findAllDepartment() throws Exception;
    List<String> findMajorByDepartmentName(String department) throws Exception;
    List<String> findAllClazzByMajor(String major) throws Exception;
    List<Student> findStudentByClazzName(String class_name) throws Exception;

    void addAttendanceToClassStudents(List<ClassOfStudentInfo> studentInfos, String dataTableName, String format,String status)throws Exception;

    void updateAttendanceToClassStudent(String dataTableName, String student_id, String format, String att_statu)throws  Exception;

    String findSidByDateAndStatu(String dataTableName,String statu,String date,String student_id) throws Exception;

    List<Kaoqin_change> findAttendanceChange(String class_id,String state)throws Exception;

    void updateAttendanceChange(Kaoqin_change kaoqin_change)throws Exception;

    void updateTeacherDetails(TeacherInfo teacher)throws Exception;

    void updateTeacherImage(TeacherInfo teacher)throws Exception;

    void updateTeacherPwd(TeacherInfo teacher)throws Exception;

    List<Student> findTeacherByMajor(String major)throws Exception;
}
