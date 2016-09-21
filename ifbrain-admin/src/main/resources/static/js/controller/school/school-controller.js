var SchoolController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/school/search";
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
						columns: [{ 
					    	data: "citySchool.cityName"
					    },
						    
						    { 
						    	data: "scName"
						    },
						   { 
						    	data: null
						    
						    },{ 
						    	data: "createTime"
						    
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var 	   link ="&nbsp;<a href='javascript: SchoolController.deleteSchool(" + id + ")'>删除</a>";
	                	   link+="&nbsp;<a href='/school/update/" + id + "'>修改</a>";
	                	   return link;
	                   },
	                   targets: 4
	               },{
	                   render: function (data, type, row) {
	                	   
	                	   return data.member.firstName+data.member.lastName;
	                   },
	                   targets: 2
	               }
	            ]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
		deleteSchool: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/school/" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();
