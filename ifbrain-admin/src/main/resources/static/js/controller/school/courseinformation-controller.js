var CourseInformationController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/courseinformation/searchs";
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
						    	data: "schoolClass.school.scName"
						    },
						   { 
						    	data: "schoolClass.name"
						    
						    },
						    { 
						    	data: "ordinalNumber"
						    
						    },
						    { 
						    	data: "lessonName"
						    
						    },
						    { 
						    	data: "description"
						    
						    },
						    { 
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var 	   link ="&nbsp;<a href='javascript: CourseInformationController.deletecourse(" + id + ")'>删除</a>";
	                	   link+="&nbsp;<a href='/courseinformation/update/" + id + "'>修改</a>";
	                	   return link;
	                   },
	                   targets: 5
	               }
	            ]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
		deletecourse: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/courseinformation/" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();
