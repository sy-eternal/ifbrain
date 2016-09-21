var UserController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/ZDemoUser/search",
			columns: [
			    { data: "id", visible: false },
			    { data: "number" },
			    { data: "visitCount" },
			    { data: null, orderable: false }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var userId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/ZDemoUser/update/" + userId + "'>修改</a>";
			                	   link += "&nbsp;<a href='javascript: UserController.deleteUser(" + userId + ")'>删除</a>";
			                	   return link;
			                   },
			                   targets: 3
			               }
			           ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		deleteUser: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/ZDemoUser/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	UserController.init();
});
