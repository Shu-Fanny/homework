<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>登录界面</title>
		<link rel="stylesheet" href="./css/Login.css" />
		<script type="text/javascript" src="./js/login.js" ></script>
		<script type="text/javascript" src="./js/jquery-1.12.4.js" ></script>
	</head>
	<body>
		<div class="box">
			<div class="header"></div>
			<div class="login">
				<div class="login_box">
					<h1>智慧考核系统</h1>
					<form name="form">
						<label class="login_ID">
							<font>账 号:</font> <input type="text" class="textboxStyle" id="ID" name="tJodNumber" />
						</label>
						<br />
						<label class="login_pwd">
							<font>密 码:</font> <input type="password" class="textboxStyle" id="pwd" name="t_pwd" />
						</label>
						<div class="register">
							<input type="radio" id="" name="role" value="1" class="radio" checked />
							<font>教师</font>
			                <input type="radio" id="" name="role" value="2" class="radio" />
			                <font>管理员</font>
			                <br>
			                <input type="button" id="login" name="" class="btn" value="确认登录" onclick="judge()"></input>
			                <a href="#" class="changepwd">忘记密码?</a>
			                <br />
			                <span class="error" id="error">
			    		        您的工号或密码有误 请重新输入!
			                </span>
						</div>

				  	</form>
				</div>
			</div>
		</div>
		
	</body>
</html>
