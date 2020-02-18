<%@ page import="com.pyp.check_by_face.domain.PO.TeacherInfo" %>
<%@ page import="com.pyp.check_by_face.utils.FaceMatch.company.FaceMatchUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/2
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<h3></h3>
<%
    TeacherInfo teacher = new TeacherInfo();
    teacher.setT_job_number("2003082802");
    teacher.setT_name("郝丽萍");
    //teacher.setT_job_number("123");
    //teacher.setT_name("123");
    //设置登陆的教师
    session.setAttribute("teacher",teacher);
    //设置课程id
    session.setAttribute("course_id","1");
    //设置班级id
    session.setAttribute("class_id","1");
%>


<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>退出登陆</h3>
<a href="/check_by_face_controller/teacher/logout.do">退出登陆</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>修改个人资料</h3>
<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/updateTeacherDetails.do">
    邮箱：<input type="text"  name="t_email"/><br />
    电话：<input type="text"  name="t_tel"/><br />
    性别：<input type="text"  name="t_sex"/><br />
    职工号：<input type="text"name="t_job_number"/><br />
    <input type="submit" value="提交" /><br>
</form>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>修改头像</h3>
<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/updateTeacherImage.do">
    <input id="file" class="box" name="img" type="file"><br />
    <input type="submit" value="提交" /><br>
</form>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>修改密码</h3>
<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/updateTeacherPwd.do">
    密码：<input type="text"  name="t_pwd"/><br />
    <input type="submit" value="提交" /><br>
</form>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>添加课程</h3>
<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/addCourseToTeacher.do">
    <input   class="box" name="coursepicture" type="file"/><br />
    课程名称：<input type="text" id="123" name="course_name"/><br />
    课程描述：<input type="text" id="321" name="course_des"/><br />
    <input type="submit" value="提交" /><br>
</form>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>查询教师授课信息</h3>
<a href="/check_by_face_controller/teacher/findCourseByTid.do">查询教师授课信息</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>找到某门课程的授课班级</h3>
<a href="/check_by_face_controller/teacher/findClassByCourseId.do">查询授课班级</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>创建班级</h3><br>
<form method="post" action="/check_by_face_controller/teacher/addClassOfCourse.do">
    班级名称：<input type="text" name="className"/><br />
    <input type="submit" value="提交" /><br>
</form><br><br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>通过某门课程授课班级的ID找到该班级下的学生</h3>
<a href="/check_by_face_controller/teacher/findStudentByClassId.do?class_id=1">通过某门课程授课班级的ID找到该班级下的学生</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>教师为某个班添加学生</h3><br>
<form method="post" action="/check_by_face_controller/teacher/addStudentToClassOfCourse.do?class_id=35">
    学生id：<input type="text" name="student_id"/><br />
    学生id：<input type="text" name="student_id"/><br />
    学生id：<input type="text" name="student_id"/><br />
    学生id：<input type="text" name="student_id"/><br />
    <input type="submit" value="提交" /><br>
</form><br><br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>寻找某门课程的某门班级的考勤表</h3>
<a href="/check_by_face_controller/teacher/findAttendancesByClassIdAndCourseId.do">寻找某门课程的某门班级的考勤表</a>
<br><br><br>
<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>寻找某门课程的某个班级的某个学生的详细考勤表</h3>
<a href="/check_by_face_controller/teacher/findAttendanceBySid.do?student_id=1713610147">寻找某门课程的某个班级的某个学生的详细考勤表</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>192.168.0.9:8899/check_by_face_controller/teacher/findDepartment.do</h3>
<a href="/check_by_face_controller/teacher/findDepartment.do">查找所有学院</a>
<br><br><br>
<h3>192.168.0.9:8899/check_by_face_controller/teacher/findMajorByDepartmentName.do?department=xxx</h3>
<a href="/check_by_face_controller/teacher/findMajorByDepartmentName.do?department=信息工程学院">查找该学院的所有专业</a>
<br><br><br>
<h3>192.168.0.9:8899/check_by_face_controller/teacher/findAllClazzByMajor.do?major=xxx</h3>
<a href="/check_by_face_controller/teacher/findAllClazzByMajor.do?major=物联网应用技术专业">查找该专业的所有班级</a>
<br><br><br>
<h3>192.168.0.9:8899/check_by_face_controller/teacher/findStudentByClazzName.do?class_name=xxx</h3>
<a href="/check_by_face_controller/teacher/findStudentByClazzName.do?class_name=17物联网">查找该班级的所有学生</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>查找某班级提交的考勤修改审核表</h3>
<a href="/check_by_face_controller/teacher/findAttendanceChange.do?state=0">查找某班级提交的未审核考勤修改表</a><br>
<a href="/check_by_face_controller/teacher/findAttendanceChange.do?state=1">查找某班级提交的已审核考勤修改表</a>
<br><br><br>

<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>更新考勤审核信息</h3>
<a href="/check_by_face_controller/teacher/updateAttendanceChange.do?statue_if_agree=1&id=1">同意</a><br>
<a href="/check_by_face_controller/teacher/updateAttendanceChange.do?statue_if_agree=0&id=2">不同意</a>
<br><br><br>


</body>
</html>