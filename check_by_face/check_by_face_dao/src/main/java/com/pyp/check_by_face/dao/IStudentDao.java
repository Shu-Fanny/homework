package com.pyp.check_by_face.dao;


import com.pyp.check_by_face.domain.PO.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IStudentDao {
    @Select("select * from studentinfo where student_id=#{student_id}")
    Student findBySid(String student_id) throws Exception;

    @Insert("insert select_courses values (#{class_id},#{student_id},#{join_time});")
    void addCourseBySid(SelectCourse selectCourse);

    /**
     * 查询某个班级学生的步骤
     * @return
     * @throws Exception
     */

    //查询学学校的所有学院
    @Select("SELECT distinct(department) FROM studentinfo")
    List<String> findAllDepartment()throws Exception;
    //查询所在学院的所有专业
    @Select("SELECT distinct(major) FROM studentinfo where department=#{department}")
    List<String> findMajorByDepartmentName(String department)throws Exception;
    //查询所在专业的所有班级
    @Select("SELECT distinct(class_name) FROM studentinfo where major=#{major}")
    List<String> findAllClazzByMajor(String major)throws  Exception;
    //根据班级名查询某个班的学生
    @Select("select * from studentinfo where class_name=#{class_name}")
    List<Student> findStudentByClazzName(String class_name)throws  Exception;

    //修改密码
    @Update("update studentinfo set student_pwd=#{student_pwd} where student_id=#{student_id}; ")
    void updateStudentPassword(@Param("student_id") String student_id, @Param("student_pwd") String student_pwd);

    //根据学生ID找到其课程
    @Select("select * from course_by_teacher where course_id in (select course_id from classinfo where class_id=#{class_id})")
    @Results({
            @Result(id = true, property = "course_id", column = "course_id"),
            @Result(property = "course_name", column = "course_name"),
            @Result(property = "course_picture", column = "course_picture"),
            @Result(property = "course_des", column = "course_des"),
            @Result(property = "teachers", column = "t_job_number", javaType = TeacherInfo.class, one = @One(select = "com.pyp.check_by_face.dao.ITeacherDao.findTeacherById"))
    })
    Course findCourseBySid(String class_id)throws Exception;

    @Insert("insert kaoqindatabase.${dataTableName} values (#{student_id},#{att_time},#{att_statu})")
    void addAttenceByStudentID(@Param("dataTableName") String dataTableName, @Param("student_id") String student_id, @Param("att_time")String format, @Param("att_statu")String status) throws Exception;

    @Update("update kaoqindatabase.${dataTableName} set att_statu=#{att_statu} where student_id=#{student_id} and att_time=#{att_time}")
    void updateAttendanceToClassStudent(@Param("dataTableName")String dataTableName, @Param("student_id")  String student_id,  @Param("att_time")String format,@Param("att_statu") String att_statu)throws Exception;


    @Select("select class_id from select_courses where student_id=#{student_id}")
    List<String> findClassIdByStudentId(String student_id);

    @Insert("insert into kaoqin_change(apply_time,student_id,statue_before_change,reason,support_material,class_id) values (#{apply_time},#{student_id},#{statue_before_change},#{reason},#{support_material},#{class_id})")
    void addAttendanceChange(Kaoqin_change kaoqin_change);

    @Update("update studentinfo set student_face_info=1 where student_id=#{student_id}")
    void updateStudent_face_info(String student_id)throws Exception;
}
