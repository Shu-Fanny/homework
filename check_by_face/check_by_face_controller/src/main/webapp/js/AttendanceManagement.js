$(function(){
	$("#creatClass_one").click(function(){
		$("#addCourse").show();
	})		
	$("#close").click(function(){
		$("#addCourse").hide();
	})
	$("#btn2").click(function(){
		$("#addCourse").hide();
	})

	

});

 $(document).ready(function () {
 	//	$.ajax({
    //	type:"get",
    //	url:"../test5.json",
    //	async:true,
    //	success:function(data){
    //
    //		$.each(data, function(index,item) {
    //			$("#lichange").append("<li onclick='' id='"+item.Id+"'>"+item.className+"</li>");
    //
    //			var aa = "#"+item.Id+"";
	//			var urlr = "http://192.168.0.3:8899/check_by_face_controller/teacher/findAttendancesByClassIdAndCourseId.do";
	//
	//			$(aa).click(function(){
	//			$("#table").bootstrapTable('refresh',{url:urlr});
	//			$(this).addClass('change_color').siblings('li').removeClass('change_color');
    //})
    //		});
    //
    //	}
    //});
 	
     $('#table').bootstrapTable({
         url:'http://192.168.0.4:8899/check_by_face_controller/teacher/findAttendancesByClassIdAndCourseId.do',
         uniqueId: "id",
         locale: "zh-CN",            // 语言
         toolbar: "#toolbar",        // 工具栏
         search: true,               // 显示搜索
         height: 540,				//根据页面高度定义表格高度
         showColumns: false,          //隐藏列
         striped: true,              // 是否显示行间隔色
         showRefresh: false,         // 是否显示刷新按钮
         clickToSelect: true,       // 是否启用点击选中行
         showToggle: false,          // 是否显示详细视图和列表视图的切换按钮
         cardView: false,            // 是否显示详细视图javascript:void(0)
         sortable: false,            // 是否启用排序
         sortOrder: "asc",           // 排序方式
         pagination: true,           // 是否显示分页
         sidePagination: "client",
         searchAlign: "left",// 分页方式：client客户端分页，server服务端分页
         pageList:[10],
         

         columns: [
         {checkbox: false,visible: false},
		 {field: 'student_id', title: '学号', sortable: true, width: 280 },
	     {field: 'student_name', title: '姓名', sortable: true, width: 280 },
	     {field: 'normal',  title: '出勤', sortable: true, width: 280 },
	     {field: 'absence',  title: '旷课', sortable: true, width: 280 },
         {field: 'casual_leave', title: '事假', sortable: true, width: 280 },
         {field: 'late', title: '迟到', sortable: true, width: 280 },
         {field: 'sick_leave', title: '病假', sortable: true, width: 280 },
     　　	]
     });
    
     
     
 });
        
$("#btnExport").click(function(){
        $('#table').tableExport({
        type:'excel',
        escape:'false',
        fileName: 'UV_statistic_table'
        });
    });
