$(document).ready(function () {
	$("#creatClass").click(function(){
		$("#addCourse").show();
	});
	$("#creatStudent").click(function(){
		$("#addStudent").show();
	});
	$("#creatClass_one").click(function(){
		$("#addCourse").show();
	})		
	$("#close").click(function(){
		$("#addCourse").hide();
	})
	$("#close_addStudent").click(function(){
		$("#addStudent").hide();
	})
	$("#btn4").click(function(){
		$("#addStudent").hide();
	})
	$("#btn2").click(function(){
		$("#addCourse").hide();
	})
/*	$("li").click(function(){
    	$("#classList_ul li").removeClass('checket');
    	$(this).addClass('change_color').siblings('li').removeClass('change_color');
  
    })*/
    
    $("#btn1").click(function(){
//  	$("#classNameInput").val();
		if ($("#className").val() != "") {

			var value=$("#className").val();
			$.ajax({
				type:"get",
				url:"/check_by_face_controller/teacher/addClassOfCourse.do?className="+value
			});

//			$("#classList_ul").append("<li>"+$("#classNameInput").val()+"</li>");
			$("#addCourse").hide();
		}
		else{
			alert("名字不能为空");
		}
	
    })
    
    $.ajax({
    	type:"get",
    	url:"http://192.168.0.4:8899/check_by_face_controller/teacher/findClassByCourseId.do",
    	async:true,
//      xhrFields: {
//          withCredentials: true // 这里设置了withCredentials
//      },
    	success:function(data){
    		
    		$.each(data, function(index,item) {
    			
    			$("#classList_ul").append("<li onclick='' id='"+item.class_id+"'>"+item.classname+"</li>");
    			$('ul.classList_ul').children("li:eq(0)").addClass('checket');
    			
    			var aa = "#"+item.class_id+"";
				var val=$(this).attr("class_id");
				
				$(aa).click(function(){
				var urlr = "http://192.168.0.4:8899/check_by_face_controller/teacher/findStudentByClassId.do?class_id="+val;
				$("#td").bootstrapTable('refresh',{url:urlr});
    			$("#classList_ul li").removeClass('checket');				
				$(this).addClass('change_color').siblings('li').removeClass('change_color'); 
				
                })
    		});	
    	}
    });

	


	$('#td').bootstrapTable({
		url:"./test2.json",
		uniqueId: "student_id",
		locale: "zh-CN",            // 语言
		toolbar: "#toolbar",        // 工具栏
		search: true,               // 显示搜索
		height: 580,				//根据页面高度定义表格高度
		showColumns: false,         //隐藏列
		striped: true,              // 是否显示行间隔色
		showRefresh: false,         // 是否显示刷新按钮
		clickToSelect: true,        // 是否启用点击选中行
		showToggle: false,          // 是否显示详细视图和列表视图的切换按钮
		cardView: false,            // 是否显示详细视图javascript:void(0)
		sortable: false,            // 是否启用排序
		sortOrder: "asc",           // 排序方式
		pagination: false,          // 是否显示分页
		sidePagination: "client",   // 分页方式：client客户端分页，server服务端分页
		
						          //导出功能设置（关键代码）
		exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据
		showExport: false,  //是否显示导出按钮
		buttonsAlign:"right",  //按钮位置
		exportTypes:['excel'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'
//      pageList:[10],
        columns:[

		{field: 'num', title: '序号', sortable: true, width: 280,formatter: function (value, row, index) {  return index+1; }},
		{field: 'student_name', title: '姓名', sortable: true, width: 280 },
		{field: 'student_id', title: '学号', sortable: true, width: 280 },
		{field: 'department', title: '院系', sortable: true, width: 280 },
		{field: 'major', title: '专业', sortable: true, width: 280 },
		{field: 'class_name', title: '班级', sortable: true, width: 280 },
		{field: 'join_time', title: '加入时间', sortable: true, width: 280 },
		{field: 'attenceState', title: '操作', sortable: true, width: 280,events: operateEvents,formatter: operateFormatter },
		]	
	});

	$('#addStudent_table').bootstrapTable({
		url:"./test2.json",
		uniqueId: "student_id",
		locale: "zh-CN",            // 语言
		toolbar: "#toolbar",        // 工具栏
		search: true,               // 显示搜索
		height: 350,				//根据页面高度定义表格高度
		showColumns: false,         //隐藏列
		striped: true,              // 是否显示行间隔色
		showRefresh: false,         // 是否显示刷新按钮
		clickToSelect: true,        // 是否启用点击选中行
		showToggle: false,          // 是否显示详细视图和列表视图的切换按钮
		cardView: false,            // 是否显示详细视图javascript:void(0)
		sortable: false,            // 是否启用排序
		sortOrder: "asc",           // 排序方式
		pagination: false,          // 是否显示分页
		sidePagination: "client",   // 分页方式：client客户端分页，server服务端分页

		//导出功能设置（关键代码）
		exportDataType:'all',//'basic':当前页的数据, 'all':全部的数据, 'selected':选中的数据
		showExport: false,  //是否显示导出按钮
		buttonsAlign:"right",  //按钮位置
		exportTypes:['excel'],  //导出文件类型，[ 'csv', 'txt', 'sql', 'doc', 'excel', 'xlsx', 'pdf'
//      pageList:[5],
		columns:[
			{checkbox: true,visible: true},
			{field: 'num', title: '序号', sortable: true, width: 280,formatter: function (value, row, index) {  return index+1; }},
			{field: 'student_name', title: '姓名', sortable: true, width: 280 },
			{field: 'student_id', title: '学号', sortable: true, width: 280 },

//		{field: 'attenceState', title: '操作', sortable: true, width: 280,events: operateEvents,formatter: operateFormatter },
		]
	});


//	var urlr = "../test2.json";
//	var aa = "#aaa";
//	$(aa).click(function(){
//		$("#td").bootstrapTable('refresh',{url:urlr});
//	});
});
        function operateFormatter(value, row, index) {
            return [
                '<button type="button" class="btn btn-default" id="rowDel">删除</button>'
            ].join('');
        };
        window.operateEvents = {
            'click #rowDel': function (e, value, row, index) {
            	var uid=$(this).parent().parent().attr("data-uniqueid");
                $('#td').bootstrapTable('removeByUniqueId', uid);
                
            	$.ajax({
            		type:"get",
            		url:"",
					// 删除数据
            		async:true,
					data:{"student_id":uid}
            	});
                
            }
        };
        $("#btn3").click(function () {
        	$("#addStudent_table input:checkbox").each(function () {
				if ($(this).attr("checked")==true){
				 var  sid=$(this).parent().parent().attr("data-uniqueid");
				}
				$.ajax({
					type:"get",
					url:"http://192.168.0.4:8899/check_by_face_controller/teacher/addStudentToClassOfCourse.do",
					data:{"student_id":sid}
				});
			})

		})

    $("#btnExport").click(function(){
        $('#td').tableExport({
        type:'excel',
        escape:'false',
        fileName: 'UV_statistic_table'
        });
    });



//  $("#btn1").click(function(){
////  	$("#classNameInput").val();
//		if ($("#classNameInput").val() != "") {
//			$("#classList_ul").append("<li>"+$("#classNameInput").val()+"</li>");
//			$("#addCourse").hide();
//		}
//		else{
//			alert("密码不能为空。");
//		}
//  	
//  	
//  })

