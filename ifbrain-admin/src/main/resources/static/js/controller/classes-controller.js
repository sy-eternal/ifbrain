var ClassesController = function () {
	var _dataTable = null;
	var showTable = function () {
		//var url ="/classes/search";
		var url="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true,
				/*data : JSOG.decode(data),*/
				/*columns: [
				            { data: "id", visible: false },
						    { data: "courseLevel" },
						    { 
						    	data: null,
						    	defaultContent:""
						    },
						    { data: "childNumber" },
						    { data: "startDate" },
						    { data: "endDate" },
						    { data: null }
						],
				
				columnDefs : [
				    {
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var classname=data.className;
	                	   var link = "&nbsp;<a href='/classes/childmanagelist/" + id + "'  style='text-decoration:underline'>"+classname+"</a>" ;
	                	   return link;
	                   },
	                   targets: 1
	               },{
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "&nbsp;<a href='/classes/tokenbanklist/" + id + "'>代币银行</a>" ;
	                	   &nbsp;<a href='/classes/update/" + id + "'>修改</a> 
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

