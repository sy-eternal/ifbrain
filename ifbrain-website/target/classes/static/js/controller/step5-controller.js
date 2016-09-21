var Step4Controller = function() {
	var _orderId = null;
	var _dataTableVehiclePlan = null;
	var _dataTableExcursionGuidePlan = null;
	var _dataTableStandardGuidePlan = null;
	var _dataTableSpotPlan = null;
	var _dataTableHotelPlan = null;
	var showTableVehiclePlan = function() {
		var url = Web.contextPath +"/datePlan/searchVehiclePlan?orderId=" + _orderId;
		$.get(url, function (datePlans) {
			_dataTableVehiclePlan = $("#tableVehiclePlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				paging : false,
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
					data : "vehiclePlan.vehicleTypeCode"
				}, {
					data : "vehiclePlan.supplierPriceRule.supplier.cnName"
				}, {
					data : "vehiclePlan.departureTime"
				}, {
					data : "vehiclePlan.arrivalTime"
				}, {
					data : "vehiclePlan.vehicleNumber"
				}, {
					data : "vehiclePlan.airEquipType"
				}, {
					data : "vehiclePlan.rank"
				}, {
					data : "vehiclePlan.departureTerminalId"
				}, {
					data : "vehiclePlan.startAirport"
				}, {
					data : "vehiclePlan.arrivalTerminalId"
				}, {
					data : "vehiclePlan.arriveAirport"
				}, {
					data : "vehiclePlan.personCount"
				} ],
				columnDefs : [ {
					render : function(data, type, row) {
						if (!data) {
							return "";
						}
						var type = "交通方式";
						var vehicleTypeCode = data;
						var returnCode = null;
						$.ajax({
							async : false, // 进行同步调用
							type : "GET",
							url : "/code/findByTypeAndValue",
							data : {
								type : type,
								value : vehicleTypeCode
							},
							success : function(code) {
								returnCode = code;
							}
						});
						if (!returnCode) {
							return "";
						}

						return returnCode.classs;
					},
					targets : 4
				}, {
					render : function(data, type, row) {
						if (!data) {
							return "";
						}
						return data;
					},
					targets : [ 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 ,15 ]
				} ]
			});
		});
	};

	var showTableExcursionGuidePlan = function() {
	
		var url = Web.contextPath +"/excursionGuideView/search?orderId=" + _orderId;
		$.get(url, function(data) {
			_dataTableExcursionGuidePlan = $("#tableExcursionGuidePlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				paging : false,
				lengthChange : false,
				searching : false,
				ordering : true,
				processing : true,
				data : JSOG.decode(data),
				columns : [  {
					data : "startDate"
				}, {
					data : "endDate"
				}, {
					data : "cnName"
				}, {
					data : "guideCount",
					defaultContent : ""
				}]
			});
		})
	};

	var showTableStandardGuidePlan = function() {
		var url = Web.contextPath + "/standGuideView/search?orderId=" + _orderId;
		$.get(url, function(data) {
			_dataTableStandardGuidePlan = $("#tableStandardGuidePlan").DataTable({
				language : {
					url :Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				paging : false,
				lengthChange : false,
				searching : false,
				ordering : true,
				processing : true,
				data : JSOG.decode(data),
				columns : [  {
					data : "startDate"
				}, {
					data : "endDate"
				}, {
					data : "cnName"
				}, {
					data : "guideCount",
					defaultContent : ""
				} ]
			});
		});
	};

	var showTableSpotPlan = function() {
		var url = Web.contextPath +"/spotPlan/findByDatePlanOrderId?orderId=" + _orderId;
		$.get(url, function (spotPlans) {
			_dataTableSpotPlan = $("#tableSpotPlan").DataTable({
				language : {
					url : Web.contextPath +"/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(spotPlans),
				columns : [ {
					data : "datePlan.startDate"
				}, {
					data : "datePlan.endDate"
				}, {
					data : "datePlan.cityPlan.toCity.cityName"
				}, {
					data : "spot.spotsname"
				}, {
					data : "spotPlanTicket.spotTicketType.type"
				}, {
					data : "spot.address"
				}, {
					data : "spot.tel"
				}, {
					data : "spotPlanTicket.personCount",
					defaultContent : ""
				}]
			});
		});
	};

	var showTableHotelPlan = function() {
		var url =Web.contextPath + "/hotelPlan/findByDatePlanOrderId?orderId=" + _orderId;
		$.get(url, function (hotelPlans) {
			_dataTableHotelPlan = $("#tableHotelPlan").DataTable({
				language : {
					url : Web.contextPath +"/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				processing : true,
				paging : false,
				data : JSOG.decode(hotelPlans),
				columns : [ {
					data : "datePlan.startDate"
				}, {
					data : "datePlan.endDate"
				}, {
					data : "datePlan.cityPlan.toCity.cityName"
				}, {
					data : "hotelName"
				}, {
					data : "hotelAddress"
				}, {
					data : "hotelTel"
				}, {
					data : "hotelRank"
				}, {
					data : "hotelRoomCount"
				} ]
			});
		});
	};

	return {
		// main function to initiate the module
		init : function() {
			_orderId = $("#orderId").val();
			showTableVehiclePlan();
			showTableExcursionGuidePlan();
			showTableStandardGuidePlan();
			showTableSpotPlan();
			showTableHotelPlan();
		}
	};
}();
