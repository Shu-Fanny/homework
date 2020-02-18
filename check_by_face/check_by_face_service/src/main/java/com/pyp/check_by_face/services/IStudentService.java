package com.pyp.check_by_face.services;

import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.Attendance;
import com.pyp.check_by_face.domain.VO.StuCourseAndClassId;

import java.util.List;

public interface IStudentService {
    /*
     *查找学生信息
     */
    Student findBySid(String sid) throws Exception;

    /*
     * 学生选课
     * @sid: session域中数据
     * @course:表单数据
     */
    void addCourseBySid(SelectCourse selectCourse)throws  Exception;

    /*
     * 学生退出某课程
     * @sid: session域中数据
     * @cid:课程信息中隐藏的数据，传json时已经传过去了
     */
    void deleteCourseBySid()throws Exception;

    /**
     * 更改学生登陆密码
     */
    void updateStudentPassword(String student_id,String student_pwd)throws Exception;

    Course findCourseBySid(String class_id)throws Exception;

    List<String> findClassIdByStudentId(String student_id)throws Exception;

    Attendance findAttendanceBySid(String dataName, String student_id)throws Exception;

    List<EveryAttendance> findEveryAttendanceBySid(String dataName, String student_id)throws Exception;

    void addAttendanceChange(Kaoqin_change kaoqin_change) throws Exception;

    List<EveryAttendance> findAttendanceBySidAndAid(String dataName, String student_id, String att_statu)throws Exception;

    void updateStudent_face_info(String student_id)throws Exception;
}
