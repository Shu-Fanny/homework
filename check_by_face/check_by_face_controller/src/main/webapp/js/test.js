function AddFunctionAlty (value,row,index) {
	
	return[
	'<button id="TableDeltet" type = "button" class = "btn btn-default" >删除</button>' 
	].join("")
}	
window.operateEvents = {
	"click #TableDeltet":function(e,value,row,index){
		
//		$('#td').bootstrapTable('removeByUniqueId', id);
		$(this).parent().parent().remove();
	}
}
