<%@ page import="com.pyp.check_by_face.domain.PO.TeacherInfo" %><%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/2
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <% TeacherInfo teacher = new TeacherInfo();
       teacher.setT_job_number("2003082802");
       teacher.setT_name("郝丽萍");
       session.setAttribute("teacher",teacher);%>
    <a href="/check_by_face_controller/teacher/findTeacherInfo.do">查找教师的基本资料</a><br>
    <a href="/check_by_face_controller/teacher/findCourseByTid.do">查找教师的授课信息</a><br>
    <a href="/check_by_face_controller/teacher/test.do?dataName=acd">动态生成数据表</a>


</body>
</html>
