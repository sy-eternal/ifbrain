var Step5Controller = function() {
	var _insurancePlan = null;
	var _marginPlan = null;
	var showTableInsurancePlan = function() {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/insurancePlan/findByOrder?orderId=" + orderId;
		$.get(url, function (insurancePlans) {
			_insurancePlan = $("#insurancePlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : false,
				paging : false,
				processing : false,
				data : JSOG.decode(insurancePlans),
				scrollX : true,
				columns : [ {
					data : "insuranceName",
				},
				{
					data : "holderType"
				},
				{
					data : "personCount"
				},
				{
					data : "subTotalAmount"
				},
				{
					data : null,
					orderable : false
				} ],
				columnDefs : [ {
					render : function(data, type, row) {
						var id = row.id;
						var link = "<a href='" + Web.contextPath + "/insurancePlan/update?id=" + id + "&orderId="+orderId+"'>修改</a>";
						link += "&nbsp;<a href='javascript: Step5Controller.deleteInsurancePlan(" + id + ")'>删除</a>";
						return link;
					},
					targets : 4
				} ]
			});
		});
	};

	var showMarginPlan = function() {
		var orderId = $("#orderId").val();
		var url =  Web.contextPath + "/marginplan/search?orderId=" +orderId;
		$.get(url, function(marginPlans) {
			console.log(marginPlans);
			_marginPlan = $("#marginPlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				paging : false,
				lengthChange : false,
				searching : false,
				ordering : true,
				processing : true,
				data : JSOG.decode(marginPlans),
				columns : [ {
					data:"margin.margintype"
				},
				{
					data : "margintotalprice"
				},
				{
					data : null,
				}],
				columnDefs : [{
					render: function (data) {
						if (!data) {
							return "";
						}
						return "$" + data;
					},
					targets: [0]
				},
				{
					render : function(data, type, row) {
						var id = row.id;
						var link = "<a href='" + Web.contextPath + "/order/" +orderId +" /plan?_step6'>修改</a>";
						link += "&nbsp;<a href='javascript: Step5Controller.deleteMarginPlan(" + id + ")'>删除</a>";
						return link;
					},
					targets :2
				}]
			});
		})
	};

	return {
		init : function() {
			showTableInsurancePlan();
			showMarginPlan();

		},
		deleteMarginPlan: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url:Web.contextPath +"/marginplan/" + id,
				success: function () {
					// 刷新表格
				window.location.reload();
				}
			});
		},
		deleteInsurancePlan: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url:Web.contextPath +"/insurancePlan/" + id,
				success: function () {
					// 刷新表格
				window.location.reload();
				}
			});
		}
	};
}();


$(function() {
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	QuickSidebar.init(); // init quick sidebar
	Demo.init(); // init demo features
	Step5Controller.init();
});
