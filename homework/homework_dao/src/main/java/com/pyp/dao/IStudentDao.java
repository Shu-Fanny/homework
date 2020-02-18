package com.pyp.dao;

import com.pyp.domain.Student;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface IStudentDao {
    /**
     * 根据学生用户名找到其详细信息
     * @param s_username
     * @return
     * @throws Exception
     */
    @Select("select * from `student` where s_username=#{s_username}")
    Student findStudentByUserName(@Param("s_username") String s_username)throws Exception;

    /**
     * 根据学生id找到其详细信息
     * @param sid
     * @return
     * @throws Exception
     */
    @Select("select * from `student` where sid=#{sid}")
    Student findStudentBySid(@Param("sid") String sid)throws Exception;

    /**
     * 根据学生id和作业id确定其所在的记录，找到之后将作业路径更新到此记录中
     * @param file_path
     * @param sid
     * @param hid
     * @throws Exception
     */
    @Update("update `student_homework` set file_path=#{file_path},state=1 where sid=#{sid} and hid=#{hid}")
    void updateStuWork(@Param("file_path") String file_path, @Param("sid") String sid,  @Param("hid") String hid)throws Exception;

    /**
     * 找到所有学生
     * @return
     * @throws Exception
     */
    @Select("select * from `student`")
    List<Student> findAllStudent()throws Exception;

    /**
     * 向学生作业中插入一条记录
     * @param sid
     * @param hid
     * @throws Exception
     */
    @Insert("insert `student_homework`(sid,hid) values (#{sid},#{hid})")
    void addHomeWork(@Param("sid") String sid,@Param("hid") String hid)throws Exception;
}
