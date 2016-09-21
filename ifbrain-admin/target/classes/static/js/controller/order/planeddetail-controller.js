var PlanedDetailController = function() {
	var _insurancePlan = null;
	var _marginPlan = null;
	var showTableInsurancePlan = function() {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/insurancePlan/findByOrder?orderId=" + orderId;
		$.get(url, function (insurancePlans) {
			console.log(JSOG.decode(insurancePlans));
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
				}]
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
				}],
				columnDefs : [{
					render: function (data) {
						if (!data) {
							return "";
						}
						return "$" + data;
					},
					targets: [0]
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
	PlanedDetailController.init();
});
