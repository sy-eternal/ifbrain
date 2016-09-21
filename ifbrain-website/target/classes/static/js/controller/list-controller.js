var ListController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/list/search",
			columns: [
			    { data: "id", visible: false },
			   	 { data: "customer" },
			    { data: "orderNumber" },
			    { data: "orderPrice" },
			    { data: "createTime" },
			    { data: null, orderable: false }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var orderId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/list/update/" + listId + "'>修改</a>";
			                	   link += "&nbsp;<a href='javascript:ListController.deleteList(" + listId + ")'>删除</a>";
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
				url: Web.contextPath + "/list/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	ListController.init();
});
