var MenuController = function() {
	var _dataTable = null;
	var showTable = function() {
		_dataTable = $("#table")
				.DataTable(
						{
							language : {
								url : Web.contextPath
										+ "/js/lib/datatables/Chinese.json"
							},
							lengthChange : false,
							searching : true,
							ordering : true,
							order : [[3, "asc"]],
							processing : true,
							serverSide : true,
							ajax : Web.contextPath + "/menu/search",
							columns : [ {
								data : "id",
								visible : false
							}, {
								data : "name"
							}, {
								data : "parentId"
							}, {
								data : "displayOrder"
							}, {
								data : "url"
							}, {
								data : null,
								orderable : false
							} ],
							columnDefs : [ {
								render : function(data, type, row) {
									var menuId = row.id;
									var parentId=row.parentId;
									var link = "<a href='"
											+ Web.contextPath
											+ "/menu/update/" + menuId
											+ "'>修改</a>";
//									link += "&nbsp;<a href='javascript: MenuController.deleteMember("
//									+ menuId + ")'>删除</a>";
									link +="&nbsp;<a href='"
											+ Web.contextPath
											+ "/menu/create/" + menuId
											+ "'>新增</a>";
									return link;
								},
								targets : 5
							}]
						});
	};

	return {
		// main function to initiate the module
		init : function() {
			showTable();
		},
		// 删除
		deleteMember : function(id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type : "DELETE",
				url : Web.contextPath + "/menu/" + id,
				success : function() {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();
$(function() {
	MenuController.init();
});
