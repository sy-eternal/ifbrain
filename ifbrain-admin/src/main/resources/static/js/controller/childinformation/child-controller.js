var ChildController = function () {
	var _dataTable = null;
	var showTable = function () {
		//var url ="/childinformation/search";
		var url ="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true/*,
				data : JSOG.decode(data),*/
				/*columns: [
						    { 
						    	data: "name",
						    	defaultContent:""
						    },
						   { 
						    	data: "courseClass.className",
						    	defaultContent:""
						    },
						    { 
						    	data: "courseClass.courseLevel",
						    	defaultContent:""
						    },
						    { 
						    	data: null,
						    	defaultContent:""
						    }
						],*/
	            /*columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='/childinformation/update/" + id + "'>修改</a>";
	                	   return link;
	                   },
	                   targets: 3
	               }
	            ]*/
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		/*deleteCityActive: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/cityactive/" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}*/
	};
}();

/*$(function () {
	ChildController.init();
});*/