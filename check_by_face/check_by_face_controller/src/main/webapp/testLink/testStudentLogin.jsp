<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录界面</title>
	</head>
	<body>
					<h1>智慧考核系统</h1>
					<form method="post"  action="/check_by_face_controller/student/login.do">
						<label class="login_ID">
							<font>账 号:</font> <input type="text" class="textboxStyle" id="ID" name="student_id" />
						</label>
						<br />
						<label class="login_pwd">
							<font>密 码:</font> <input type="password" class="textboxStyle" id="pwd" name="student_pwd" />
						</label>
						<input type="submit" value="提交">
						</div>
				  	</form>

					<h3>测试某个学生的课程信息</h3>
					<a href="/check_by_face_controller/student/findCourseBySid.do">学生的课程信息</a><br><br><br>
					<h3>测试某个学生的课程考勤总览</h3>
					<a href="/check_by_face_controller/student/findAttendanceBySid.do?class_id=1">学生的课程信息</a><br><br><br>
                    <h3>测试某个学生的课程每条考勤</h3>
                    <a href="/check_by_face_controller/student/findEveryAttendanceBySid.do?class_id=1">学生的课程信息</a><br><br><br>

					<h3>上传学生库图片</h3>
	<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/student/addStudentPicture.do">
		<input id="file" class="box" name="picture" type="file"   />
		<input type="submit" value="上传">
	</form><br><br><br>

	<form method="post" enctype="multipart/form-data"  action="/check_by_face_controller/student/addAttendanceChange.do">
		文件：<input class="box" name="picture" type="file"   />
		状态：<input name="att_statu"/>
		理由：<input name="reason"/>
		时间：<input name="att_time" value="2019-01-01 10:10:10"/>
		<input type="submit" value="上传">
	</form>
	</body>
</html>
