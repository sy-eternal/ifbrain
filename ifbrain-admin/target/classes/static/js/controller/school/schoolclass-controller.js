var SchoolClassController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/schoolclass/search";
		
		//var url ="";
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
								data: "school.citySchool.cityName"
							},
							{ 
								data: "school.scName"
							},
						    { 
						    	data: "name"
						    },{ 
						    	data: null
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
	                	   var 	link="&nbsp;<a href='/schoolclass/update/" + id + "'>修改</a>";
	                	   link +="&nbsp;<a href='javascript: SchoolClassController.deleteSchoolClass(" + id + ")'>删除</a>";
	                	   
	                	   return link;
	                   },
	                   targets: 5
	               },
	               {
	                   render: function (data, type, row) {
	                	  
	                	   return data.member.firstName+data.member.lastName;
	                   },
	                   targets: 3
	               }
	            ]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
		deleteSchoolClass: function (id) {
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
