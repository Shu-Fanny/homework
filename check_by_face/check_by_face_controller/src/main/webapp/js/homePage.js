//$(function(){
//
//	$("#append").click(function(){
//		alert("1");
//		window.open('CreateCourse.html');//跳转界面
////      location.href='CreateCourse.html';  不跳转界面
// });
//});


var vm = new Vue({
	el:'#app',
	data:{
		list:'',
		},
		methods:{
			
		},
		mounted(){
			//http://192.168.0.4:8899/check_by_face_controller/teacher/findCourseByTid.do
			this.$http.get('../source/course.json').then(result=>{
			this.list = result.body;
		})
		}
});

