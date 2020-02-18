<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>创建课程</title>
		<link rel="stylesheet" href="css/CreateCourse.css" />
		<script type="text/javascript" src="js/jquery-1.12.4.js" ></script>
		<script type="text/javascript" src="js/jquery.form.js" ></script>
		<script type="text/javascript" src="js/CreateCourse.js" ></script>
	</head>
	<body>
		<div class="box">
			<div class="header">
				<img src="img/leftheader.png" class="left_header" />
				<img src="img/rightHeader.png" class="right_header"  />
			</div>
			<div class="middle">
				<div class="left_box">
					<div class="head_portrait"></div>
					<div class="menu">
						<p>用户名</p>
						<a href="">我的课程</a>
						<a href="">基本资料</a>
						<a href="">密码管理</a>
						<a href="">修改头像</a>
					</div>
				</div>
				<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/addCourseToTeacher.do" id="from1" >
				<div class="right_box">
			
					<h1>创建课程</h1>
					<br />
					<hr color=black SIZE=2/>
					<br /><br />
					<div class="name_box">
						<h3>课程名称：</h3>
						<input type="text" name="course_name" id="course_name"/>
					</div>
					<div class="des_box">
						<h3>课程说明：</h3>
						<textarea class="explain_Input" name="cueser_des" id="cueser_des"></textarea>
					</div>
					<div class="img_box">
						<h3>课程封面：</h3>
						<div class="pic_box">
							<img src="" class="pic" id="show" name="coursepicture" />
						</div>
						<div class="text_box">
							<img src="img/pic_up.png" class="pic_up" onclick="addScore()" />
							<li>作为课程的宣传图，用于课程主页的显示</li>
							<li>支持JPG、JPGE、GIF、PNG格式</li>
							<li>文件小于4MB</li>	
							<li>图片大小为长250px 宽150px</li>
				        </div> 
				        <button type="submit" class="finsh">完成</button>
					</div>
				</div>
				<input type="file" name="coursepicture" id="file" accept="image/*" onchange="changepic(this)" style="display: none;" />
			</div>
			</form>
			<iframe id="rfFrame" name="rfFrame" src="about:blank" style="display:none;"></iframe>  
			<div class="nav"></div>
		</div>
		
	</body>
</html>
