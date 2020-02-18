package com.pyp.dao;

import com.pyp.domain.HomeWork;
import com.pyp.domain.StuWork;
import com.pyp.domain.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IHomeWordDao {
    @Select("select * from `homework` where hid=#{hid}")
    HomeWork findHomeWorkByHid(@Param("hid") String hid) throws Exception;

    @Select("select * from `student_homework` where sid=#{sid} and state=#{state} limit #{startIndex},#{pageSize}")
    @Results({
            @Result(property = "student", column = "sid", javaType = Student.class, one = @One(select = "com.pyp.dao.IStudentDao.findStudentBySid")),
            @Result(property = "homeWork", column = "hid", javaType = HomeWork.class, one = @One(select = "com.pyp.dao.IHomeWordDao.findHomeWorkByHid")),
            @Result(property = "state", column = "state"),
            @Result(property = "score", column = "score")
    })
    List<StuWork> findHomeWorkByState(@Param("sid") String sid, @Param("state") String state,
                                      @Param("startIndex") int startIndex,@Param("pageSize") int pageSize)throws Exception;

    //根据学生id以及他的作业提交状态查询
    @Select("select count(*) from `student_homework` where sid=#{sid} and state=#{state}")
    int findTotalRecords(@Param("sid") String sid,@Param("state") String state);

    //查找总共有多少条作业记录
    @Select("select count(*) from `homework`")
    int findTotalHWRecords()throws Exception;

    //查找作业条数并分页
    @Select("select * from `homework` limit  #{startIndex},#{pageSize}")
    List<HomeWork> findAllHomeWorkWithPage(@Param("startIndex") int startIndex,@Param("pageSize") int pageSize);

    //查找学生作业已提交的作业条数
    @Select("select count(*) from `student_homework` where state=#{state}")
    int findTotalRecordsByState(@Param("state") String state);

    /**
     * 查找所有提交的作业
     * @param state 1：已提交，但是未审核  2：已提交，并且已批改
     * @param startIndex 开始条数
     * @param pageSize 一页几条
     * @return
     */
    @Select("select * from `student_homework` where state=#{state} limit #{startIndex},#{pageSize}")
    @Results({
            @Result(property = "student", column = "sid", javaType = Student.class, one = @One(select = "com.pyp.dao.IStudentDao.findStudentBySid")),
            @Result(property = "homeWork", column = "hid", javaType = HomeWork.class, one = @One(select = "com.pyp.dao.IHomeWordDao.findHomeWorkByHid")),
            @Result(property = "state", column = "state"),
            @Result(property = "score", column = "score")
    })
    List<StuWork> findCommitHomeWorkWithPage(@Param("state") String state, @Param("startIndex")int startIndex,@Param("pageSize") int pageSize);

    /**
     * 教师发布作业
     * @param homeWork
     * @throws Exception
     */
    @Insert("insert `homework` values (#{hid},#{title},#{desc},#{file_path})")
    void addHomeWork(HomeWork homeWork)throws Exception;
}
