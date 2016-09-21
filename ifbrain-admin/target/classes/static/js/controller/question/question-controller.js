var QuestionController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/question/search";
		//var url ="";
	
		$.get(url,function(data) {
			console.log(JSOG.decode(data));
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
						    	data: "questiontype.questionTypeName"
						    },
						    { 
						    	data: "testdifficulty"
						    }, { 
						    	data: "createTime"
						    }, { 
						    	data: "questionNameContent"
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: QuestionController.updatematerial(" + id + ")'>修改</a>&nbsp;<a href='javascript: QuestionController.deletematerial(" + id + ")'>删除</a>";
	                	   return link;
	                   },
	                   targets: 4
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
			window.location.href="/question/initupdate?id="+id;
		}
       ,
		deletematerial: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/question/delete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

