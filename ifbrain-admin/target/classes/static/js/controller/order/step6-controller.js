var Step6Controller = function() {
	var _tablePlan = null;
	var showTableInsurancePlan = function() {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/specialplancar/findByOrder?orderId=" + orderId;
		$.get(url, function (specialcarplan) {
			_tablePlan = $("#specialcarplan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : false,
				paging : false,
				processing : false,
				data : JSOG.decode(specialcarplan),
				scrollX : true,
				columns : [ {
					data : "specialCarPlan.cartype",
				},
				{
					data : "specialCarPlan.carprice"
				},
				{
					data : "specialCarPlan.specialcar.supplier.cnName"
				},
				{
					data : null,
					orderable : false
				} ],
				columnDefs : [ 
				               {
					render : function(data, type, row) {
						var id = row.id;
						var link = "<a href='" + Web.contextPath + "/specialplancar/update?id=" + id + "&orderId="+orderId+"'>修改</a>";
						link += "&nbsp;<a href='javascript: Step6Controller.deleteInsurancePlan(" + id + ")'>删除</a>";
						return link;
					},
					targets : 3
				} ]
			});
		});
	};
	return {
		init : function() {
			showTableInsurancePlan();

		},
		deleteInsurancePlan: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url:Web.contextPath +"/specialplancar/" + id,
				success: function () {
					// 刷新表格
				window.location.reload();
				}
			});
		},
	};
}();

$(function() {
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	QuickSidebar.init(); // init quick sidebar
	Demo.init(); // init demo features
	Step6Controller.init();
});
