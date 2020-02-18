$(document).ready(function () {
	
    $("#singalAdd").click(function(){
		$("#singalAdd_box").show();
	});
	
	$("#close").click(function(){
		$("#singalAdd_box").hide();
	})

	$("#btn2").click(function(){
		$("#singalAdd_box").hide();
	})
    $("#btn1").click(function(){
        $("#singalAdd_box").hide();
    })
	

$('#btn1').click(function(){
	var student_name = $('#student_name').val();
	var student_id = $('#student_id').val();
	var department = $('#department').val();
	var major = $('#major').val();
	$.ajax({
		type:"post",
		url:"/check_by_face_controller/manager/addStudentsInfo.do",
		async:true,
		data:{"student_name":student_name,"student_id":student_id,"department":department,"major":major},
		success:function(){
            window.location.reload();
		}
	});
})
	
        	
        	
        	
                $('#studentTable').bootstrapTable({
                    url:'http://192.168.0.4:8899/check_by_face_controller/manager/findStudentsInfo.do',
                    uniqueId: "student_id",
                    locale: "zh-CN",            // 语言
                    toolbar: "#toolbar",        // 工具栏
                    search: true,               // 显示搜索
                    height: 580,				//根据页面高度定义表格高度
                    showColumns: false,          //隐藏列
                    striped: true,              // 是否显示行间隔色
                    showRefresh: false,         // 是否显示刷新按钮
                    clickToSelect: true,       // 是否启用点击选中行
                    showToggle: false,          // 是否显示详细视图和列表视图的切换按钮
                    cardView: false,            // 是否显示详细视图javascript:void(0)
                    sortable: false,            // 是否启用排序
                    sortOrder: "asc",           // 排序方式
                    pagination: false,           // 是否显示分页
                    sidePagination: "client",// 分页方式：client客户端分页，server服务端分页

                    columns: [
               
           		{field: 'num', title: '序号', sortable: true, width: 280,formatter: function (value, row, index) {  return index+1; }},           		
				{field: 'student_name', title: '姓名', sortable: true, width: 280 },
                　　 		{field: 'student_id',  title: '学号', sortable: true, width: 280 },
              {field: 'class_name',  title: '班级', sortable: true, width: 280 },
                　　		{field: 'department',  title: '院系', sortable: true, width: 280 },
                {field: 'major', title: '专业', sortable: true, width: 280 },
                {field: 'caozuo', title: '操作', sortable: true, width: 280 ,events: operateEvents,formatter: operateFormatter},
               
                　　			]
                });
            });
            $(function(){
            	$("#exit").click(function(){
            		alert("退出成功");
            		window.location.replace("http://www.baidu.com");
            	})
            })
    function operateFormatter(value, row, index) {
            return [
                '<button type="button" class="btn btn-sm btn-default" id="rowDel">删除</button>' ,
            ].join('');
        };

        window.operateEvents = {
            'click #rowDel': function (e, value, row, index) {
                var uid=$(this).parent().parent().attr("data-uniqueid");
                $('#studentTable ').bootstrapTable('removeByUniqueId', uid);
                $.ajax({
            		type:"get",
            		url:"",
            		async:true,
					data:{"ID":uid}
            	});
            }
        };
        
$('#singalAdd').click()