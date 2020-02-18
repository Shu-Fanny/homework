 $(document).ready(function () {
 	
     $('#table').bootstrapTable({
         url:'http://192.168.0.4:8899/check_by_face_controller/teacher/findAttendanceChange.do',
         uniqueId: "xuehao",
         locale: "zh-CN",            // 语言
         toolbar: "#toolbar",        // 工具栏
         search: true,               // 显示搜索
         height: 650,				//根据页面高度定义表格高度
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
    
		//{field: 'yuanxi', title: '院系', sortable: true, width: 280 },
		//{field: 'zhuanye', title: '专业', sortable: true, width: 280 },
        //{field: 'banji',  title: '班级', sortable: true, width: 280 },
        {field: 'student_id',  title: '学号', sortable: true, width: 280 },
        //{field: 'xingming', title: '姓名', sortable: true, width: 280 },
        {field: 'statue_after_change', title: '代更改考勤段', sortable: true, width: 280 },
        {field: 'statue_before_change', title: '原出勤状态', sortable: true, width: 280 },
        {field: 'genggaiwei', title: '更改为', sortable: true, width: 280 },
        {field: 'genggaiyuanyou', title: '更改原由', sortable: true, width: 280 },
        {field: 'support_material', title: '佐证材料', sortable: true, width: 280 },
        //{field: 'zhuangtai', title: '状态', sortable: true, width: 280 },
        {field: 'statue_if_agree', title: '操作', sortable: true, width: 700 ,events: operateEvents,formatter: operateFormatter },
     　　	]
     });

     
	
//	var aa = "#yes";
	$("#yes").click(function(){
		$("#table").bootstrapTable('refresh',{url:"http://192.168.0.4:8899/check_by_face_controller/teacher/findAttendanceChange.do"});
		$("#table").bootstrapTable('hideColumn', 'caozuo');
	}); 
	$("#no").click(function(){
		$("#table").bootstrapTable('refresh',{url:"../test6.json"});
		$("#table").bootstrapTable('showColumn', 'caozuo');
	});
	
          
     
     
 });
    	function operateFormatter(value, row, index) {
            return [
                '<button type="button" class="btn btn-sm btn-default" id="agree">同意</button>&nbsp' ,
                '<button type="button" class="btn btn-sm btn-default" id="disagree">不同意</button>',
            ].join('');
        };
        window.operateEvents = {
            'click #agree': function (e, value, row, index) {
                var uid=$(this).parent().parent().attr("data-uniqueid");
                $('#table').bootstrapTable('removeByUniqueId', uid);
                
                $.ajax({
                	type:"get",
                	url:"",
                	async:true,
                	data:{"caozuo":1,"xuehao":uid}
                });
            }
        };
        window.operateEvents = {
            'click #disagree': function (e, value, row, index) {
                var uid=$(this).parent().parent().attr("data-uniqueid");
                $('#table').bootstrapTable('removeByUniqueId', uid);
                
                $.ajax({
                	type:"get",
                	url:"",
                	async:true,
                	data:{"caozuo":2,"xuehao":uid}
                });
            }
        };
   