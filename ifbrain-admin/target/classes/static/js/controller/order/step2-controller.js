var Step2Controller = function() {
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
					data : "startDate"
				}, {
					data : "endDate"
				}, {
					data : "cityPlan.fromCity.cityName"
				}, {
					data : "cityPlan.toCity.cityName"
				}, {
					data : "vehiclePlan"
				}, {
					data : "vehiclePlan"
				}, {
					data : "vehiclePlan"
				}, {
					data : "spotPlan"
				}, {
					data : "spotPlan"
				}, {
					data : "spotPlan"
				}, {
					data : null,
					orderable : false
				} ],
				columnDefs : [ {
					render : function(data, type, row) {
						var type = "交通方式";
						if (data == null) {
							return "";
						}
						var vehicleTypeCode = data.vehicleTypeCode;
						var returnCode = null;
						$.ajax({
							async : false, // 进行同步调用
							type : "GET",
							url : Web.contextPath + "/code/findByTypeAndValue",
							data : {
								type : type,
								value : vehicleTypeCode
							},
							success : function(code) {
								returnCode = code;
							}
						});
	
						return returnCode.classs;
					},
					targets : 4
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.departureTime;
					},
					targets : 5
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.arrivalTime;
					},
					targets : 6
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.spot.spotsname;
					},
					targets : 7
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.spotPlanTicket.spotTicketType.type;
					},
					targets : 8
				}, {
					render : function(data, type, row) {
						if (data == null) {
							return "";
						}
						return data.spotPlanTicket.personCount;
					},
					targets : 9
				}, {
					render : function(data, type, row) {
						var type = "交通方式";
						var id = data.id;
						var link = "<a href='" + Web.contextPath + "/spotPlan/edit?dataPlanId=" + id + "'>规划</a>";
						return link;
					},
					targets : 10
				} ]
			});
		});
	};

	return {
		// main function to initiate the module
		init: function () {
			showTable();
		},

		reload: function () {
			//_dataTable.ajax.reload();
			window.location.reload();
		}
	};
}();


$(function() {
	Metronic.init(); // init metronic core components
	Layout.init(); // init current layout
	QuickSidebar.init(); // init quick sidebar
	Demo.init(); // init demo features

	Step2Controller.init();
});


