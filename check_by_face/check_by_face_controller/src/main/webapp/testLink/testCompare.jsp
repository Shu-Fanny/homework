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
<h3>开始考勤</h3>
<a href="/check_by_face_controller/compare/startAttendance.do?state=1">开始考勤</a>
<br><br><br>


<h3>--------------------------------------------------------------------------------------------------------------------</h3>
<h3>修改密码</h3>
<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/compare/addAttendanceToClassStudents.do">
    <input   class="box" name="picture" type="file"/><br />
    <input type="submit" value="提交" /><br>
</form>
</body>
</html>
