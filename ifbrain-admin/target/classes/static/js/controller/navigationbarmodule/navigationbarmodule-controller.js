var NavigationbarmoduleController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/navigationbarmodule/search";
		//var url ="";
		$.get(url,function(data) {
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
			    	         data: "id",visible: false 
			               },
						    { 
						    	data: null
						    },
						   { 
						    	data: "author"
						    },
						    { 
						    	data: "createTime"
						    },
						    { 
						    	data: "navigationBar.name"
						    },
						    { 
						    	data: "type"
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: NavigationbarmoduleController.updatematerial(" + id + ")'>"+data.title+"</a>";
	                	   return link;
	                   },
	                   targets: 1
	               },{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: NavigationbarmoduleController.deletematerial(" + id + ")'>删除</a>";
	                	   return link;
	                   },
	                   targets: 6
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
			window.location.href="/navigationbarmodule/initupdate?id="+id;
		}
       ,
		deletematerial: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/navigationbarmodule/delete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

