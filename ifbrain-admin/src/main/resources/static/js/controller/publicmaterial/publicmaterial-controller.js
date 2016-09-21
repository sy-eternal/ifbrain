var PublicmaterialController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/publicmaterial/search";
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
						    	data: "navigationbarmodule.title"
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: PublicmaterialController.updatematerial(" + id + ")'>"+data.publicmaterialName+"</a>";
	                	   return link;
	                   },
	                   targets: 1
	               },{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: PublicmaterialController.deletematerial(" + id + ")'>删除</a>";
	                	   return link;
	                   },
	                   targets: 5
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
			window.location.href="/publicmaterial/initupdate?id="+id;
		}
       ,
		deletematerial: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/publicmaterial/delete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

