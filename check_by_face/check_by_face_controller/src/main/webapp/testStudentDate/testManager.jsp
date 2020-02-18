<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/19
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<h3>添加教师</h3>
<form method="post" action="/check_by_face_controller/manager/addTeacherInfo.do">
    工号：<input type="text" name="t_job_number"/><br />
    姓名：<input type="text" name="t_name"/><br />
    密码：<input type="text" name="t_pwd"/><br />
    邮箱：<input type="text" name="t_email"/><br />
    电话：<input type="text" name="t_tel"/><br />
    性别：<input type="text" name="t_sex"/><br />
    院系：<input type="text" name="department"/><br />
    专业：<input type="text" name="major"/><br />
    <input type="submit" value="提交" /><br>
</form>


<a href="/check_by_face_controller/manager/deleteTeacherInfo.do?t_job_number=23">删除教师</a>

<h3>更新教师信息</h3>
<form method="post" action="/check_by_face_controller/manager/updateTeacherInfo.do">
    工号：<input type="text" name="t_job_number"/><br />
    姓名：<input type="text" name="t_name"/><br />
    院系：<input type="text" name="department"/><br />
    专业：<input type="text" name="major"/><br />
    <input type="submit" value="提交" /><br>
</form>

<a href="/check_by_face_controller/manager/findTeachersInfo.do">查找教师</a>



-------------------------------------------------------------------------------------------------
<h3>添加学生</h3>
<form method="post" action="/check_by_face_controller/manager/addStudentsInfo.do">
    工号：<input type="text" name="student_id"/><br />
    姓名：<input type="text" name="student_name"/><br />
    密码：<input type="text" name="student_pwd"/><br />
    电话：<input type="text" name="student_tel"/><br />
    性别：<input type="text" name="student_sex"/><br />
    院系：<input type="text" name="department"/><br />
    专业：<input type="text" name="major"/><br />
    班级：<input type="text" name="class_name"/><br />
    班级：<input type="text" name="student_head_image"/><br />
    <input type="submit" value="提交" /><br>
</form>


<a href="/check_by_face_controller/manager/deleteStudentsInfo.do?student_id=2345">删除学生</a>


<h3>更新学生信息</h3>
<form method="post" action="/check_by_face_controller/manager/updateStudentsInfo.do">
    工号：<input type="text" name="student_id"/><br />
    姓名：<input type="text" name="student_name"/><br />
    院系：<input type="text" name="department"/><br />
    专业：<input type="text" name="major"/><br />
    班级：<input type="text" name="class_name"/><br />
    <input type="submit" value="提交" /><br>
</form>

<a href="/check_by_face_controller/manager/findStudentsInfo.do">查找学生</a>

</body>
</html>
