var ExamController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/exam/search";
		console.log("zhelizhelizhelizhelizheli")
		$.get(url,function(data) {
			
			console.log(JSOG.decode(data))
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    { 
						    	data: "testdifficulty"
						    },
						    { 
						    	data: "examName"
						    }, 
						    
						    { 
						    	data: "createTime"
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "<a href='javascript: ExamController.itemmanagement(" + id + ")'>试题管理</a>";
	                	   if(data.status==0 || data.status ==2){
	                		   link+="&nbsp;<a href='javascript: ExamController.examrelease(" + id + ")'>发布</a>";
	                	   }
	                	   if(data.status==1){
	                		   link+="&nbsp;<a href='javascript: ExamController.revoke(" + id + ")'>撤销</a>";
	                	   }
	                	   link+="&nbsp;<a href='javascript: ExamController.updatematerial(" + id + ")'>修改</a>&nbsp;<a href='javascript: ExamController.deletematerial(" + id + ")'>删除</a>";
	                	   return link;
	                   },
	                   targets: 3
	               }
	            ]
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		updatematerial: function (id) {
			window.location.href="/exam/initupdate?id="+id;
		},
		revoke: function (id) {
			$.ajax({
				type: "GET",
				url: "/exam/revoke?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
		,
		examrelease: function (id) {
			$.ajax({
				type: "GET",
				url: "/exam/examrelease?id=" + id,
				success: function (data) {
					// 刷新表格
					alert(data);
					window.location.reload();
				}
			});
		},
		itemmanagement: function (id) {
			window.location.href="/exam/itemmanagement?id="+id;
		}
       ,
		deletematerial: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/exam/delete?id=" + id,
				success: function (data) {
					// 刷新表格
					alert(data);
					window.location.reload();
				}
			});
		}
	};
}();

