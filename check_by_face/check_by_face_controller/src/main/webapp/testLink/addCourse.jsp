<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>上传图片</title>
		<style>
			.pic{
				width: 100px;
				height: 100px;
			}
			.box{
				width: 100px;
				height: 40px;
				display: inline-block;
			}
		</style>
		<script>
			function changepic(obj){
				//console.log(obj.files[0]);//这里可以获取上传文件的name
				var newsrc=getObjectURL(obj.files[0]);
				document.getElementById('show').src=newsrc;
				

				}
			//建立一個可存取到該file的url
			function getObjectURL(file){
				var url = null;
				// 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
				if(window.createObjectURL!=undefined){   // basic
					url = window.createObjectURL(file); 
				}else if(window.URL!=undefined){ // mozilla(firefox)
					url = window.URL.createObjectURL(file);
				}else if(window.webitURL!=undefined){ // webkit or chrome
					url = window.webkitURL.createObjectURL(file);
				}
				
				return url;
				}
			
			

			
			
//			function changepic() {
//              var reads= new FileReader();
//
//              f=document.getElementById('file').files[0];
//
//              reads.readAsDataURL(f);
// 
//              reads.onload=function (e) {
//
//              document.getElementById('show').src=this.result;
        </script>
	</head>
	<body>
		<img class="pic" id="show" src="" />
		<br />
		<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/addCourseToTeacher.do">
			课程姓名<input type="text" id="" name="course_name"/><br />
		<input id="file" class="box" name="coursepicture" type="file" accept="image/*" onchange="changepic(this)" />
			课程描述<input type="text" id="" name="course_des"/><br />
		<input type="submit" value="提交" />
		</form>
	</body>

</html>
