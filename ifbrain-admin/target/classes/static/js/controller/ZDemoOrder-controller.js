var OrderController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/ZDemoOrder/search",
			columns: [
			    { data: "id", visible: false },
			    { data: "zDemoUser.number" },
			    { data: "reveiverName" },
			    { data: "mobile" },
			    { data: "address" },
			    { data: "createTime" },
			    { data: null, demoOrderable: false }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var demoOrderId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/ZDemoOrder/update/" + demoOrderId + "'>修改</a>";
			                	   link += "&nbsp;<a href='javascript: OrderController.deleteOrder(" + demoOrderId + ")'>删除</a>";
			                	   return link;
			                   },
			                   targets: 6
			               }
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		deleteOrder: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/ZDemoOrder/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	OrderController.init();
});
