package com.pyp.check_by_face.dao;

import com.pyp.check_by_face.domain.PO.*;
import com.pyp.check_by_face.domain.VO.ClassOfStudentInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface ITeacherDao {

    //教师登陆
    @Select("select * from teacherinfo where t_job_number=#{tJodNumber}")
    TeacherInfo findTeacherById(String tJodNumber)throws Exception;

    //根据教师tid查询授课信息
    //这样写不安全，透露教师隐私
    @Select("select * from course_by_teacher where t_job_number=#{t_job_number}")
    @Results({
            @Result(id = true, property = "course_id", column = "course_id"),
            @Result(property = "course_name", column = "course_name"),
            @Result(property = "course_picture", column = "course_picture"),
            @Result(property = "course_des", column = "course_des"),
            @Result(property = "teachers", column = "t_job_number", javaType = TeacherInfo.class, one = @One(select = "com.pyp.check_by_face.dao.ITeacherDao.findTeacherById"))
    })
    List<Course> findCourseByTid(String t_job_number);

    /*
     *给教师添加课程
     */
    @Select("insert course_by_teacher(t_job_number,course_name,course_picture,course_des) values (#{t_job_number},#{course_name},#{course_picture},#{course_des})")
    void addCourseToTeacher(Course course)throws Exception;


    /*
     *教师添加班级到课程中
     * 添加记录到 classinfo ，方便后期学生考勤表的动态创建
     */
    @Insert("insert classinfo(course_id, className) VALUES (#{course_id},#{className});")
    void addClassOfCourse(@Param("course_id") String course_id,@Param("className") String className)throws Exception;


    /*
     *动态创建考勤表
     * @Param：dataName=class_id+course_id
     */
    @Update("create table ${dataName}\n" +
            "(\n" +
            "  student_id char(10)              not null,\n" +
            "  att_time   datetime              not null,\n" +
            "  att_statu  char(10) default '旷课' not null,\n" +
            "  constraint `${dataName}_studentinfo_student_id_fk`\n" +
            "    foreign key (student_id) references studentinfo (student_id)\n" +
            ")\n" +
            "  charset = utf8;")
    void creatAttendanceByDataName(@Param("dataName") String dataName);

    /**
     * 通过某门课程的id找到其授课班级
     */
    @Select("select * from classinfo where course_id=#{course_id}")
    @Results({
            @Result(id = true, property = "class_id", column = "class_id"),
            @Result(property = "classname", column = "classname"),
            @Result(property = "course", column = "course_id", javaType = Course.class, one = @One(select = "com.pyp.check_by_face.dao.ICourseDao.findCourseByCid"))
    })
    List<ClassInfo> findClassByCourseId(String course_id);

    /**
     * 通过某门课程授课班级的ID找到该班级下的学生
     */
    @Select("select stu.student_name,stu.student_id,stu.department,stu.major,stu.class_name,sc.join_time \n" +
            "  from select_courses as sc inner join studentinfo as stu on sc.student_id = stu.student_id\n" +
            "where sc.class_id=#{class_id}")
    List<ClassOfStudentInfo> findStudentByClassId(String class_id);

    @Select("select att.student_id from ${dataName} as att left join studentinfo as s on att.student_id = s.student_id where \n " +
            "att.att_statu=#{att_statu} and att.att_time=#{att_time} and s.student_face_info=1 and s.student_id=#{student_id}")
    String findSidByDateAndStatu(@Param("dataName")String dataName,@Param("att_statu") String statu,@Param("att_time") String date,@Param("student_id") String student_id)throws Exception;

    @Select("select * from `kaoqin_change` where class_id=#{class_id} and state=#{state}")
    List<Kaoqin_change> findAttendanceChange(@Param("class_id") String class_id,@Param("state") String state)throws Exception;

    @Update("update `kaoqin_change` set statue_if_agree=#{statue_if_agree},state=1,reason_not_agree=#{reason_not_agree} where id=#{id}")
    void updateAttendanceChange(Kaoqin_change kaoqin_change)throws Exception;

    @Update("update `teacherinfo` set t_email=#{t_email},t_tel=#{t_tel},t_sex=#{t_sex},t_job_number=#{t_job_number} where t_job_number=#{t_job_number}")
    void updateTeacherDetails(TeacherInfo teacher)throws Exception;

    @Update("update `teacherinfo` set t_head_image=#{t_head_image} where t_job_number=#{t_job_number}")
    void updateTeacherImage(TeacherInfo teacher)throws Exception;

    @Update("update `teacherinfo` set t_pwd=#{t_pwd} where t_job_number=#{t_job_number}")
    void updateTeacherPwd(TeacherInfo teacher)throws Exception;

    @Select("select * from `teacherinfo` where major=#{major}")
    List<Student> findTeacherByMajor(@Param("major") String major)throws Exception;
}
