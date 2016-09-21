var AirPortController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		var cityactiveId = $("#cityactiveId").val();
		var cityactiveCityName = $("#cityactiveCityName").val();
		var cityactiveEnName = $("#cityactiveEnName").val();
		_dataTable = $("#table").DataTable({
			language: {
                url:"/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax:"/airport/search?cityactiveId="+cityactiveId,
			columns: [
			    { data: "id" },
			    { data: "cityName" },
			    { data: "enName" },
			    { data: "airportCode" },
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var link = "<a href='/airport/update/" + id + "'>修改</a>" ;
			                	    link += "&nbsp;<a href='javascript: AirPortController.deleteairPort(" + id + ")'>删除</a>";
			                	  
			                	    return link;
			                   },
			                   targets: 4
			               },{
			                   render: function (data, type, row) {
			                  	   var link =cityactiveCityName ;
			                  	    return link;
			                    },
			                    targets: 1
			                },
			                {
			                    render: function (data, type, row) {
			                  	   var link =cityactiveEnName ;
			                  	    return link;
			                    },
			                    targets: 2
			                }
			           ]
		});
	};
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		deleteairPort: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
//				url: Web.contextPath + "/spotstickettype/" + id,
				url:"/airport/"+id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

