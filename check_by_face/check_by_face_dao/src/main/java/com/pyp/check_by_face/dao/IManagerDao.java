package com.pyp.check_by_face.dao;

import com.pyp.check_by_face.domain.PO.Student;
import com.pyp.check_by_face.domain.PO.SysManager;
import com.pyp.check_by_face.domain.PO.TeacherInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IManagerDao {
    @Select("select * from sysmanager where s_job_number=#{tJodNumber}")
    SysManager findManagerById(String tJodNumber)throws Exception;

    @Select("select * from teacherinfo")
    List<TeacherInfo> findTeachersInfo()throws Exception;

    @Update("update teacherinfo set t_name=#{t_name},t_job_number=#{t_job_number},department=#{department},major=#{major} where t_job_number=#{t_job_number}")
    void updateTeacherInfo(@Param("t_name") String t_name, @Param("t_job_number") String t_job_number, @Param("department") String department,@Param("major") String major);

    @Insert("insert into teacherinfo values (#{t_job_number},#{t_name},#{t_pwd},#{t_head_image},#{t_email},#{t_tel},#{t_sex},#{department},#{major})")
    void addTeacherInfo(TeacherInfo teacherInfo)throws Exception;

    @Delete("delete from teacherinfo where t_job_number=#{t_job_number};")
    void deleteTeacherInfo(String t_job_number)throws Exception;



    @Select("select * from studentinfo")
    List<Student> findStudentsInfo()throws Exception;

    @Update("update studentinfo set student_name=#{student_name},student_id=#{student_id},department=#{department},major=#{major}, class_name=#{class_name} where student_id=#{student_id}")
    void updateStudentsInfo(@Param("student_name") String student_name,@Param("student_id") String student_id, @Param("department")String department,@Param("major") String major,@Param("class_name") String class_name);

    @Insert("insert into studentinfo values (#{student_id},#{student_pwd},#{student_name},#{student_sex},#{student_tel},#{department},#{major},#{class_name},null,0)")
    void addStudentsInfo(Student student);

    @Delete("delete from studentinfo where student_id=#{student_id};")
    void deleteStudentsInfo(String student_id);
}
