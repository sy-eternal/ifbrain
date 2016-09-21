var Step3Controller = function() {
	var _dataTable = null;
	var showTable = function() {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/datePlan/findByOrderId?orderId=" + orderId;
		$.get(url, function (datePlans) {
			_dataTable = $("#table").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(datePlans),
				scrollX : true,
				columns : [ {
					data : "endDate"
				}, {
					data : "cityPlan.fromCity.cityName"
				}, {
					data : "cityPlan.toCity.cityName"
				}, {
					data : "hotelPlan",
					orderable : false
				}, {
					data : "hotelPlan",
					orderable : false
				}, {
					data : "hotelPlan",
					orderable : false
				}, {
					data : null,
					orderable : false
				} ],
				columnDefs : [ {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.hotelName;
					},
					targets : 3
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.hotelRank;
					},
					targets : 4
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.hotelRoomCount;
					},
					targets : 5
				}, {
					render : function(data, type, row) {
						var id = data.id;
						var link = "<a href='" + Web.contextPath + "/hotelPlan/edit?dataPlanId=" + id + "'>规划</a>";
						return link;
					},
					targets : 6
				} ]
			});
		});
	};

	return {
		// main function to initiate the module
		init : function() {
			showTable();
		},
		deleteDatePlan : function(id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type : "DELETE",
				url : Web.contextPath + "/datePlan/" + id,
				success : function() {
					// 刷新表格
					_dataTable.ajax.reload();
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

	Step3Controller.init();
});
