var MaterialController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/material/search";
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
						    }/*,
						   { 
						    	data: "author"
						    
						    }*/,
						    { 
						    	data: "createTime"
						    },
						    { 
						    	data: "author"
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: MaterialController.updatematerial(" + id + ")'>"+data.materialName+"</a>";
	                	   return link;
	                   },
	                   targets: 1
	               },{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: MaterialController.deletematerial(" + id + ")'>删除</a>"
	                	   link +="&nbsp;<a href='javascript: MaterialController.commentmaterial(" + id + ")'>用户评论</a>"
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
			window.location.href="/material/initupdate?id="+id;
		}
       ,commentmaterial: function (id) {
			window.location.href="/material/commentmaterial?id="+id;
		},
		deletematerial: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/material/delete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

/*$(function () {
	ChildController.init();
});*/