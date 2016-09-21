var Step1Controller = function() {
	var _AircraftPlandataTable = null;
	var _HotelPlandataTable = null;
	var _RentalPlandataTable = null;
	var _SpotPlandataTable = null;
	var startDates =$("#startDates").val();
	var endDates =$("#endDates").val();
	//飞机规划
	var AircraftPlan = function(datePlanId) {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/tripplan/findByDatePlanId?orderId=" + orderId+"&datePlanId="+datePlanId;
		$.get(url, function (data) {
			  console.log(JSOG.decode(data));
			  _AircraftPlandataTable = $("#AircraftPlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(data),
				scrollX : true,
				destroy:true,
				columns : [  {
					data : "flightType"
				}, {
					data : "subtotalAmount"
				}, {
					data : "personalType"
				}, {
					data : "personalCount"
				}, {
					data : "airlineCodes",
				}, {
					data : "datePlan.cityPlan.fromCity.cityName",
				}, {
					data : "datePlan.cityPlan.toCity.cityName",
				}, {
					data : "departureTime",
				}, {
					data : "arrivalDate",
				}, {
					data : "totalFlightTime",
				}, {
					data : null,
					orderable : false
				}, {
					data : "id",
					visible:false
 				}],
				columnDefs : [ {
					render : function(data, type, row) {
						var id = row.id;
						var airlineCodes=row.airlineCodes;
						var url="/tripplan/findById?id="+id;
						var link = "<a href="+url+">"+airlineCodes.airlineCode+"</a>";
						return link;
					},
					targets : 4
				},
				{
					render : function(data, type, row) {
						var personalType = row.personalType;
						var link="";
						if(personalType==0){
							link="小孩";
						}else if(personalType==1){
							link="成人";
						}
						return link;
					},
					targets : 2
				},
          {
					render : function(data, type, row) {
						var link = "<a href=''>修改</a>";
						 link += "&nbsp;&nbsp;<a href=''>删除</a>";
						return link;
					},
					targets : 10
				}
]
			});
		});
	};

	//酒店规划
	var HotelPlan = function(datePlanId) {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/tripplan/hotelFindByDatePlanId?orderId=" + orderId+"&datePlanId="+datePlanId;
		$.get(url, function (data) {
			  console.log(JSOG.decode(data));
			  _HotelPlandataTable = $("#HotelPlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(data),
				scrollX : true,
				destroy:true,
				columns : [{
					data : "hotelRoomType.perNightPrice"
				}, {
					data : "hotelRoomType.roomType"
				}, {
					data : "roomCount"
				}, {
					data : "hotelPlan.city"
				}, {
					data : "hotelPlan.checkInDate",
				}, {
					data : "hotelPlan.checkOutDate",
				}, {
					data : "hotelPlan.hotelEngName",
				}, {
					data : null,
					orderable : false
				}],
				columnDefs : [ 
          {
					render : function(data, type, row) {
						var link = "<a href=''>修改</a>";
						 link += "&nbsp;&nbsp;<a href=''>删除</a>";
						return link;
					},
					targets : 7
				} ]
			});
		});
	};
	
	//租车规划
	var RentalPlan = function(datePlanId) {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/tripplan/rentalFindByDatePlanId?orderId=" + orderId+"&datePlanId="+datePlanId;
		$.get(url, function (data) {
			  console.log(JSOG.decode(data));
			  _RentalPlandataTable = $("#RentalPlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(data),
				scrollX : true,
				destroy:true,
				columns : [  {
					data : "cartype"
				}, {
					data : "carprice"
				}, {
					data : "specialcar.supplier.cnName"
				}, {
					data : null,
					orderable : false
				}],
				columnDefs : [ 
          {
					render : function(data, type, row) {
						var link = "<a href=''>修改</a>";
						 link += "&nbsp;&nbsp;<a href=''>删除</a>";
						return link;
					},
					targets : 3
				}
]
			});
		});
	};
	
	//导游规划
/*	var GuidePlan = function() {
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/tripplan/guideFindByDatePlanId?orderId=" + orderId+"&datePlanId="+datePlanId;
		$.get(url, function (data) {
			  console.log(JSOG.decode(data));
			  _guidePlandataTable = $("#guidePlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(data),
				scrollX : true,
				destroy:true,
				columns : [  {
					data : "cartype"
				}, {
					data : "carprice"
				}, {
					data : "specialcar.supplier.cnName"
				}, {
					data : null,
					orderable : false
				}],
				columnDefs : [ 
          {
					render : function(data, type, row) {
						var datePlanId = row.id;
						var link = "<a href=''>修改</a>";
						 link += "&nbsp;<a href=''>删除</a>";
						return link;
					},
					targets : 3
				}
]
			});
		});
	};*/
	
	//景点
 var SpotPlan= function(datePlanId){
		var orderId = $("#orderId").val();
		var url = Web.contextPath + "/tripplan/SpotFindByDatePlanId?orderId=" + orderId+"&datePlanId="+datePlanId;
		$.get(url, function (data) {
			  console.log(JSOG.decode(data));
			  _SpotPlandataTable = $("#spotPlan").DataTable({
				language : {
					url : Web.contextPath + "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : false,
				ordering : true,
				paging : false,
				processing : true,
				data : JSOG.decode(data),
				scrollX : true,
				destroy:true,
				columns : [{
					data : "spotPlanTicket.salePrice"
				}, {
					data : "spotPlanTicket.spotTicketType.type"
				}, {
					data : "spotPlanTicket.personCount"
				}, {
					data : "datePlan.cityPlan.toCity.cityName"
				}, {
					data : "spot.spotsename",
				},  {
					data : null,
					orderable : false
				}],
				columnDefs : [   {
					render : function(data, type, row) {
						var link = "<a href=''>修改</a>";
						 link += "&nbsp;&nbsp;<a href=''>删除</a>";
						return link;
					},
					targets : 5
				} ]
			});
		});
	
 }
	return {
		init : function() {
			 var inputs = $("input[name='datePlanId']") ;
			    for(var i = 0; i < inputs.length; i++) {
			    	  console.log(inputs);
			    	  var datePlanId =inputs[i].defaultValue;
			        	AircraftPlan(datePlanId);
						RentalPlan(datePlanId);
//						GuidePlan(datePlanId);
						HotelPlan(datePlanId);
						SpotPlan(datePlanId);
			    }
		}
	};
}();

$(function() {
	Metronic.init();
	Layout.init(); 
	QuickSidebar.init(); 
	Demo.init(); 
	Step1Controller.init();
});
