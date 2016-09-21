var SupplierPriceRuleActiveController = function() {
	var _dataTable = null;

	var showTable = function() {
		var supplierpriceruleactiveId = $("#supplierpriceruleactiveId").val();
		var supplierpriceruleactiveEnName = $("#supplierpriceruleactiveEnName").val();
		var supplierpriceruleactiveCnName = $("#supplierpriceruleactiveCnName").val();
		_dataTable = $("#table")
				.DataTable(
						{
							language : {
								url : Web.contextPath + "/js/lib/datatables/Chinese.json"
							},
							lengthChange : false,
							searching : false,
							demoOrdering : true,
							processing : true,
							serverSide : true,
							ajax : Web.contextPath + "/supplierpriceruleactive/search?supplierpriceruleactiveId="
									+ supplierpriceruleactiveId,
							columns : [ {
								data : "code.classs"
							}, {
								data : "enName"
							}, {
								data : "cnName"
							}, {
								data : "priceCoefficient"
							}, {
								data : null,
								orderable : false
							} ],
							columnDefs : [
									{
										render : function(data, type, row) {
											var id = row.id;
											var link = "<a href='" + Web.contextPath
													+ "/supplierpriceruleactive/update/" + id + "'>修改</a>";
											link += "&nbsp;<a href='javascript: SupplierPriceRuleActiveController.deletesupplierpriceruleactive("
													+ id + ")'>删除</a>";
											return link;
										},
										targets : 4
									}, {
										render : function(data, type, row) {
											var link = supplierpriceruleactiveEnName;
											return link;
										},
										targets : 1
									}, {
										render : function(data, type, row) {
											var link = supplierpriceruleactiveCnName;
											return link;
										},
										targets : 2
									} ]
						});
	};

	return {
		// main function to initiate the module
		init : function() {
			showTable();
		},
		deletesupplierpriceruleactive : function(id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type : "DELETE",
				url : Web.contextPath + "/supplierpriceruleactive/" + id,
				success : function() {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();
