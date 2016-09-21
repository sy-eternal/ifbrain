var MemberController = function() {
	var _dataTable = null;

	var showTable = function() {
		_dataTable = $("#table")
				.DataTable(
						{
							language : {
								url : Web.contextPath + "/js/lib/datatables/Chinese.json"
							},
							lengthChange : false,
							searching : true,
							ordering : true,
							processing : true,
							serverSide : true,
							ajax : Web.contextPath + "/member/search",
							columns : [ {
								data : "id",
								visible : false
							}, {
								data : "firstName"
							}, {
								data : "lastName"
							}, {
								data : "roles",
								orderable : false
							}, {
								data : "telephone"
							}, {
								data : "email"
							}, {
								data : "age"
							}, {
								data : null,
								orderable : false
							} ],
							columnDefs : [
									{
										render : function(data, type, row) {
											var roleNames = "";
											for (var i = 0; i < data.length; ++i) {
												var role = data[i];
												roleNames += ","
														+ role.roleName;
											}
											if (roleNames != "") {
												roleNames = roleNames
														.substring(1);
											}
											return roleNames;
										},
										targets : 3
									},
									{
										render : function(data, type, row) {
											var memberId = row.id;
											var link = "<a href='"
													+ Web.contextPath
													+ "/member/update/"
													+ memberId + "'>修改</a>";
											link += "&nbsp;<a href='javascript: MemberController.deleteMember("
													+ memberId + ")'>删除</a>";
											link += "&nbsp;<a href='"
													+ Web.contextPath
													+ "/member/rolesmenu/"
													+ memberId + "'>配置角色</a>"
											link += "&nbsp;<a href='"
													+ Web.contextPath
													+ "/member/details/"
													+ memberId + "'>详情</a>";
											return link;
										},
										targets : 7
									} ]
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
				url : Web.contextPath + "/member/" + id,
				success : function() {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();
$(function() {
	MemberController.init();
});