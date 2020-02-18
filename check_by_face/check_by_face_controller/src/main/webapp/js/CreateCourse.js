function addScore(){
	document.getElementById("file").click();
}

			
//function changepic(obj){
//	//console.log(obj.files[0]);//这里可以获取上传文件的name
//	var newsrc=getObjectURL(obj.files[0]);
//	document.getElementById('show').src=newsrc;
//	}
////建立一個可存取到該file的url
//function getObjectURL(file){
//	var url = null;
//	// 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
//	if(window.createObjectURL!=undefined){   // basic
//		url = window.createObjectURL(file); 
//	}else if(window.URL!=undefined){ // mozilla(firefox)
//		url = window.URL.createObjectURL(file);
//	}else if(window.webitURL!=undefined){ // webkit or chrome
//		url = window.webkitURL.createObjectURL(file);
//	}
//	return url;
//}


function changepic() {
    var reads= new FileReader();

    f=document.getElementById('file').files[0];

    reads.readAsDataURL(f);
   
    reads.onload=function (e) {

    document.getElementById('show').src=this.result;
    }
}


$(function(){
	$("#from1").ajaxForm(function(data){
		if(data==1){
				alert("添加成功！");
			window.location.href = "homePage.html";
			} else if(data==2){                     
				alert("上传文件失败");
			}else if(data==3){
				alert("课程添加失败，课程名不能重复");
			}
	});
});

