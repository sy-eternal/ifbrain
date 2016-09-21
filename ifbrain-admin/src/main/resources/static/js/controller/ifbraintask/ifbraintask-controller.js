var IfbrainTaskController = function () {
	var _dataTable = null;
	var showTable = function () {
	//	var url ="/ifbraintask/search";
		var url="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: false,
	            ordering: true,
				processing: true,
				/*data : JSOG.decode(data),
				columns: [
						    { data:"courseLevel.courseLevel",
						    	defaultContent:""
						    },
						    { 
						    	data: "courseCode.lessonName",
						    	defaultContent:""
						    },
						    { 
						    	data: "materialType.materialName",
						    	defaultContent:""
						    	
						    },
						    { 
						    	data: "ifbrainTaskName",
						    	defaultContent:""
						    },
						    { 
						    	data: "description",
						    	defaultContent:""
						    },
						    { 
						    			data: null
						    }
						],
				
				columnDefs : [
				   {
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "&nbsp;<a href='javascript: IfbrainTaskController.deleteifbraintask(" + id + ")'>删除</a>";	                
	                	   return link;
	                   },
	                   targets: 5
	               }]*/
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		deleteifbraintask: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/ifbraintask/delete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
		
	};
}();

$(function(){
	//课程级别id
	var Courseid=$("#courseid").val();
	//跳转到下载任务页面
	$(".coursename").click(function(){
		//课程id
		 var value=$(this).siblings('input').val();
		 window.location.href="/ifbraintask/searchtaskbyid?coursecodeid="+value+"&courseid="+Courseid;
	});
	
	$(".delete").click(function(){
		var value=$(this).siblings('input').val();
		var courseid=$("#courseid").val();
		var coursecodeid=$("#coursecodeid").val();
		window.location.href="/ifbraintask/delete?id="+value+"&courseid="+courseid+"&coursecodeid="+coursecodeid;
	});
});

