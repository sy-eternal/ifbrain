
var detailController = function() {
	var _dataTable = null;
	var showTable = function() {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/datePlan/searchChangeGuide?orderId=" + orderId;
		$.get(url, function (datePlans) {
			console.log(JSOG.decode(datePlans));
		_dataTable = $("#table").DataTable({
			language : {
				url : "/js/lib/datatables/Chinese.json"
			},
			lengthChange : false,
			searching : false,
			processing : true,
			scrollX : true,
			data : JSOG.decode(datePlans),
			columns : [ {
				data : "startDate"
			}, {
				data : "endDate",
			}, {
				data : "cityPlan.fromCity.cityName"
			}, {
				data : "cityPlan.toCity.cityName"
			}, {
				data : "vehiclePlan",
			}, {
				data : "vehiclePlan",
			}, {
				data : "vehiclePlan",
			}, {
				data : "vehiclePlan",
			}, {
				data : "vehiclePlan",
			}, {
				data : null,
				defaultContent: ""
			}, {
				data : null,
				defaultContent: ""
			},{
				data : null,
				defaultContent: ""
			},{
				data : null,
				defaultContent: ""
			}, {
				data : null,
				defaultContent: ""
			}, {
				data : null,
				defaultContent: ""
			},{
				data : null,
				defaultContent: ""
			},{
				data : null,
				defaultContent: ""
			},{
				data : null,
				defaultContent: ""
			}],
			columnDefs : [{
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
					if (data == null) {
						return "";
					}
					return data.rank;
				},
				targets : 5
			}, {
				render : function(data, type, row) {
					if (data == null) {
						return "";
					}
						return data.departureTime;
				},
				targets : 6
			}, {
				render : function(data, type, row) {
					if (data == null) {
						return "";
					}
						return data.arrivalTime;
				},
				targets :7
			}, {
				render : function(data, type, row) {
					if (data == null) {
						return "";
					}
						return data.personCount;
				},
				targets : 8
			},{
				render : function(data, type, row) {
					var dataPlanId=row.id;
					
					var returnMember ="";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findFirstNameAndLastName",
						data : {
							dataPlanId : dataPlanId
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return  returnMember;
					
				},
				targets : 9
			},{
				render : function(data, type, row) {
					
					var startDate=data.startDate;
					var fromCityId=data.cityPlan.fromCity.id;
					var returnMember ="";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findOptionFirstNameAndLastName",
						data : {
							fromCityId : fromCityId,
							startDate:startDate
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return returnMember;
				},
				targets : 10
			},{
				render : function(data, type, row) {
				
					var dataPlanId=row.id;
					var returnMember ="";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findToFirstNameAndLastName",
						data : {
							dataPlanId : dataPlanId
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return returnMember;
				},
				targets : 11
			},{
				render : function(data, type, row) {
					var endDate=data.endDate;
					var toCityId=data.cityPlan.toCity.id;
					var returnMember ="";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findToOptionFirstNameAndLastName",
						data : {
							toCityId : toCityId,
							endDate:endDate
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return returnMember;
				},
				targets : 12
			},{
				render : function(data, type, row) {
					var dataPlanId=row.id;
					var returnMember = "";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findshortFirstNameAndLastName",
						data : {
							dataPlanId : dataPlanId
						},
						success : function(result) {
							returnMember = result;
						}
					});
					
					return returnMember;
				},
				targets : 13
			},{
				render : function(data, type, row) {
					var startDate=data.startDate;
					var fromCityId=data.cityPlan.fromCity.id;
					var returnMember ="";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findshortOptionFirstNameAndLastName",
						data : {
							fromCityId : fromCityId,
							startDate:startDate
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return returnMember;
				},
				targets : 14
			},{
				render : function(data, type, row) {
					var dataPlanId=row.id;
					var returnMember = "";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findshortToFirstNameAndLastName",
						data : {
							dataPlanId : dataPlanId
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return returnMember;
				},
				targets : 15
			},{
				render : function(data, type, row) {
					var endDate=data.endDate;
					var toCityId=data.cityPlan.toCity.id;
					var returnMember ="";
					$.ajax({
						async : false, // 进行同步调用
						type : "GET",
						url : "/guideorder/findshortToOptionFirstNameAndLastName",
						data : {
							toCityId : toCityId,
							endDate:endDate
						},
						success : function(result) {
							returnMember = result;
						}
					});
					return returnMember;
				},
				targets : 16
			},{
				render : function(data, type, row) {
					var startDate=data.startDate;
					var fromCityId=data.cityPlan.fromCity.id;
					var endDate=data.endDate;
					var toCityId=data.cityPlan.toCity.id;
					var id=row.id;
					var link = "<a href='/guideorder/detailupdate?orderId=" + orderId + "&dataPlanId="+id+"&startDate="+startDate+"&fromCityId="+fromCityId+"&endDate="+endDate+"&toCityId="+toCityId+"' style='color:blue'>更换导游</a>";
					return link;
				},
				targets : 17
			}
			]
		});
		})
	};

	return {
		// main function to initiate the module
		init : function() {
			showTable();
		}
	};
}();
