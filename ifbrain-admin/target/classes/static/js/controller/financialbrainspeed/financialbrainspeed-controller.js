var FinancialbrainspeedController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/financialbrainspeed/search";
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
						    	data: null
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: FinancialbrainspeedController.updatematerial(" + id + ")'>"+data.materialName+"</a>";
	                	   return link;
	                   },
	                   targets: 1
	               },{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "&nbsp;<a href='javascript: FinancialbrainspeedController.deletematerial(" + id + ")'>删除</a>";
	                	   return link;
	                   },
	                   targets: 2
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
			window.location.href="/financialbrainspeed/initupdate?id="+id;
		}
       ,
		deletematerial: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/financialbrainspeed/delete?id=" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

