
function judge () {
	var user_number = document.forms["form"]["ID"].value;
	var user_pwd = document.forms["form"]["pwd"].value;
	
	
	if(user_number == ""|| user_number == null){
		alert("账号不能为空");
		return false;
	};
	if(user_number.length !== 10){
		alert("账号必须为10位");
		return false;
	};
	if(user_pwd== ""||user_pwd ==null ){
		alert("密码不能为空");
		return false;
	};
	if(user_pwd.length < 6 || user_pwd.length > 16){
		alert("密码为6到16位");
		return false;
	};
	
	
	
	var radio = document.getElementsByName("role");
	
	for(var i = 0; i<radio.length;i++)
	{
		if(radio[i].checked)
		{
//			alert(radio[i].value);
			radio = radio[i].value;
		}
	}
	


	$.ajax({
		url: "http://192.168.0.4:8899/check_by_face_controller/user/login.do",
		type : "post",
		dataType : "json",
		data:{"tJobNumber":user_number,"t_pwd":user_pwd,"role":radio},
		async:true,
		
		success:function(data){
			if(data==1){
				window.location.replace("http://192.168.0.4:8899/check_by_face_controller/jsp/homePage.html");
			}
			if(data==2 || data==3){
                $("#error").css("display","inherit");
			}
		}
	});
	
//}
//$(function(){
//	$("#login").click(function(){
//		var name = $("#ID").val();
//		var pwd = $("#pwd").val();
//		var role = $("input[name='role']:checked").val();
//					
//		$.ajax({
//			type:"post",
//			url:"/check/TeacherServlet?method=LoginUI",
//			data:{name:name,pwd:pwd,role:role},
//			async:true
//		});
//	})
}
