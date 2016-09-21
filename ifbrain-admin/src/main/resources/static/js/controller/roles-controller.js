var RolesController = function () {
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
			ajax: Web.contextPath + "/roles/search",
			columns: [
			    { data: "roleName" },
			    { data: "description" },
			    { data: null, orderable: false }
			],
				columnDefs: [ 
			                 {
			                   render: function (data, type, row) {
			                	   var rolesId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/roles/update/" + rolesId + "'>修改</a>";
			                	   link += "&nbsp;<a href='javascript: RolesController.deleteRoles(" + rolesId + ")'>删除(无用户配置，才可删除)</a>";
			                	   link += "&nbsp;<a href='" + Web.contextPath + "/roles/menus/" + rolesId + "'>配置菜单</a>"
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
		// 删除
		deleteRoles: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: Web.contextPath + "/roles/" + id,
				success: function () {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	RolesController.init();
});
