<%@ page import="com.pyp.check_by_face.domain.PO.Student" %><%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/16
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<h2>学生登陆</h2>
    <form method="post" action="/check_by_face_controller/student/login.do">
        学号：<input type="text" name="student_id"/><br />
        密码：<input type="text" name="student_pwd"/><br />
        <input type="submit" value="提交" /><br>
    </form>
<a href="/check_by_face_controller/student/findCourseBySid.do">查找学生的班级</a><br>

<a href="/check_by_face_controller/test/comparePicture.do">图片比较</a><br>

<% session.setAttribute("class_id","1");%><br>
<form method="post" action="/check_by_face_controller/student/findAttendanceBySidAndAid.do">
    考勤情况：<input type="text" name="att_statu"/><br />
    <input type="submit" value="查询" /><br>
</form>
</body>
</html>
