package com.pyp.dao;

import com.pyp.domain.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface ITeacherDao {
    @Select("select * from `teacher` where t_username=#{t_username}")
    Teacher findTeacherByUserName(@Param("t_username") String t_username)throws Exception;


    @Update("update `student_homework` set score=#{score},state=#{state} where sid=#{sid} and hid=#{hid}")
    void updateHomeWorkScore(@Param("sid") String sid,@Param("hid") String hid,
                             @Param("state")String state,@Param("score") String score)throws Exception             ;
}
