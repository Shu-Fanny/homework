$(function(){
	$("#exit").click(function(){
		alert("退出成功");
//  window.location.replace("http://www.baidu.com");
	});
});
//var radio = document.getElementsByName("sex");	
//for(var i = 0; i<radio.length;i++)
//{
//	if(radio[i].checked)
//	{
////		alert(radio[i].value);
//		radio = radio[i].value;
//	}
//}
	
getList();
function getList(){
	$.ajax({
		type:"get",
		url:"",
		dataType: "json",
		async:true,
		success:function(data){
			$("#couser_email").val(data[0].couser_email);
			$("#couser_phoneName").val(data[0].couser_phoneName);
			$("#couser_number").val(data[0].couser_number);
			
			
			if(data[0].couser_sex==3){
				$("#couser_sex").attr("checked",'checked');
			}
			if(data[0].couser_sex==4){
			
                $("#couser_woman").attr("checked",'checked');
			}	
		},
		error: function(e){
		}
	});
	
	
//	function initTextInfo(text){
//		console.log(a);
//		var $couser_email = $(".email a");
//		var $couser_phoneName = $(".phoneName input value");
//		var $couser_number = $(".number input value");
//		
//		$couser_email.text(text.couser_email);
//		$couser_phoneName.text(text.couser_phoneName);
//		$couser_number.text(text.couser_number);
//		
//	}
}
