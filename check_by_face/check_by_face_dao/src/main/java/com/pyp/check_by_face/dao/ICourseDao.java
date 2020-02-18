package com.pyp.check_by_face.dao;

import com.pyp.check_by_face.domain.PO.Course;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ICourseDao {
    /**
     * 根据课程id查找课程信息
     * @param course_id
     * @return
     * @throws Exception
     */
    @Select("select * from `course_by_teacher` where course_id=#{course_id}")
    Course findCourseByCid(@Param("course_id") String course_id)throws Exception;
}
