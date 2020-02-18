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
    </script>
</head>
<body>

<img class="pic" id="show" src="" />
<br />
<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/teacher/addCourseToTeacher.do">
    <% TeacherInfo teacher = new TeacherInfo();
        teacher.setT_job_number("2003082802");
        teacher.setT_name("郝丽萍");
        session.setAttribute("teacher",teacher);%>
    <input id="file" class="box" name="coursepicture" type="file" accept="image/*" onchange="changepic(this)" /><br />
    课程名称：<input type="text" id="123" name="course_name"/><br />
    课程描述：<input type="text" id="321" name="course_des"/><br />
    <input type="submit" value="提交" /><br>
</form>

<!-- 设置课程id，假设已经点击某门课程-->
<% session.setAttribute("course_id","1");
   session.setAttribute("class_id","1");%>
<h1>创建班级</h1><br>
<form method="post" action="/check_by_face_controller/teacher/addClassOfCourse.do">
    班级名称：<input type="text" name="className"/><br />
    <input type="submit" value="提交" /><br>
</form><br><br><br><br>

<h1>找到某门课程的授课班级</h1>
<a href="/check_by_face_controller/teacher/findClassByCourseId.do">查询授课班级</a>
<br><br><br>

<h1>找到某门课程的授课班级的学生</h1>
<a href="/check_by_face_controller/teacher/findStudentByClassId.do?class_id=17">查询授课班级</a>
<br><br><br>

<h1>为某个班级导入学生</h1>
<a href="/check_by_face_controller/teacher/addStudentToClassOfCourse.do?class_id=1&student_id=1713610147">查询授课班级</a>
<br><br><br>

<h1>寻找某门课程的某门班级的考勤表</h1>

<a href="/check_by_face_controller/teacher/findAttendancesByClassIdAndCourseId.do">寻找某门课程的某门班级的考勤表</a>
<br><br><br>

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
<a href="/check_by_face_controller/teacher/findAllDepMajorClazz.do">查找所有学院所有专业所有班级</a>
<br><br><br>

<h3><%FaceMatchUtils faceMatchUtils = new FaceMatchUtils();
//String s1 = "E:\\Program Files\\JetBrains\\workspace\\check_sys\\check_by_face\\check_by_face_controller\\src\\main\\webapp\\img\\test\\1234.png";
//String s2 = "E:\\Program Files\\JetBrains\\workspace\\check_sys\\check_by_face\\check_by_face_controller\\src\\main\\webapp\\img\\test\\12345.png";
//        out.println(s2);
//        boolean b = faceMatchUtils.comparePicture(s1,s2);
//        out.println(b);%></h3>


<h1>对比人脸</h1><br>
<form method="post" action="/check_by_face_controller/teacher/addAttendanceToClassStudents.do">
    班级id：<input type="text" name="class_id"/><br/>
    <input type="submit" value="提交" /><br>
</form><br><br><br><br>


<form method="post" enctype="multipart/form-data" action="/check_by_face_controller/test/testUpload.do">
    <input  class="box" name="picture" type="file" accept="image/*" onchange="changepic(this)" /><br />
    <input type="submit" value="提交" /><br>
</form><br><br><br><br>


<h3>192.168.0.9:8899/check_by_face_controller/teacher/findAttendanceChange.do?statue_if_agree=0<br>
    <% String cid = (String) session.getAttribute("class_id");out.println("session域中的class_id："+cid);%> </h3>
<a href="/check_by_face_controller/teacher/findAttendanceChange.do?statue_if_agree=0">根据班级以及审核情况查找考勤申请</a>
<br><br><br>
</body>
</html>