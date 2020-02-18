package com.pyp.check_by_face.dao;

import com.pyp.check_by_face.domain.PO.EveryAttendance;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IAttendenceDao {
    /**
     * 根据class_id找到班级名称
     */
    @Select("select className from classinfo where class_id=#{class_Id}")
    String findClassNameByClassId(String class_id);

    /**
     * 根据学生id在动态生成的考勤表中找到其出勤记录
     */
    @Select("select count(*) from ${dataName} where student_id=#{student_id} and att_statu='出勤'")
    int findNormalBySid(@Param("dataName") String dataName, @Param("student_id") String student_id)throws  Exception;

    /**
     * 根据学生id在动态生成的考勤表中找到其旷课记录
     */
    @Select("select count(*) from ${dataName} where student_id=#{student_id} and att_statu='旷课'")
    int findAbsenceBySid(@Param("dataName") String dataName, @Param("student_id")String student_id)throws  Exception;

    /**
     * 根据学生id在动态生成的考勤表中找到其迟到记录
     */
    @Select("select count(*) from ${dataName} where student_id=#{student_id} and att_statu='迟到'")
    int findLateBySid(@Param("dataName") String dataName, @Param("student_id")String student_id)throws  Exception;

    /**
     * 根据学生id在动态生成的考勤表中找到其事假记录
     */
    @Select("select count(*) from ${dataName} where student_id=#{student_id} and att_statu='事假'")
    int findCasualLeaveBySid(@Param("dataName") String dataName,@Param("student_id") String student_id)throws  Exception;

    /**
     * 根据学生id在动态生成的考勤表中找到其病假记录
     */
    @Select("select count(*) from ${dataName} where student_id=#{student_id} and att_statu='病假'")
    int findSickLeaveBySid(@Param("dataName") String dataName, @Param("student_id")String student_id)throws  Exception;

    /**
     * 根据表名及学生id找到学生的所有考勤记录
     * @param dataName
     * @param student_id
     * @return
     * @throws Exception
     */
    @Select("select * from ${dataName} where student_id=#{student_id}")
    List<EveryAttendance> findEveryAttendanceBySid(@Param("dataName")String dataName, @Param("student_id")String student_id)throws Exception;

    /**
     * 根据表名及学生id和考勤状态找到学生在该门课程中所有班级的所有考勤记录
     * @param dataName
     * @param student_id
     * @param att_statu
     * @return
     */
    @Select("select * from `1_17物联网` where student_id=123 and att_statu like'%${att_statu}%';")
    List<EveryAttendance> findAttendanceBySidAndAid(@Param("dataName") String dataName, @Param("student_id") String student_id,@Param("att_statu") String att_statu);
}
