var GuideOrderController = function() {
	var _dataTable = null;

	var showTable = function() {
		
		var orderNumber = $('#orderNumber').val();

		var orderStatus = $('#orderStatus').val();
		
		_dataTable = $("#table").DataTable({
			language : {
				url : "/js/lib/datatables/Chinese.json"
			},
			lengthChange : false,
			searching : false,
			ordering : true,
			processing : true,
			serverSide : true,
			ajax : "/guideorder/search?orderNumber=" + orderNumber+"&orderStatus="+orderStatus,
			columns : [ {
				data :"id",
			     visible:false
		    } ,{
				data : null,
				orderable : false
			}, {
				data : null,
				orderable : false
			}, {
				data : "startDate"
			}, {
				data : "endDate"
			} ],
			columnDefs : [{
				render : function(data, type, row) {
					var orderStatus = data.orderStatus;
					var link="";
					if(orderStatus==3){
					    link = "<a href='/guideorder/detail?orderId=" + data.id + "' style='color:blue'>"+data.orderNumber+"</a>"; 
					 }
					else{
						link = data.orderNumber;
						} 
					return link;
				},
				targets : 1
			}, {
				render : function(data, type, row) {
					var orderStatus = data.orderStatus;
					var link;
					if (orderStatus == 0) {
						link = "未提交";
					}
					else if (orderStatus == 1) {
						link = "已提交";
					}
					else if (orderStatus == 2) {
						link = "已规划";
					}
					else if (orderStatus == 3) {
						link = "已结算";
					}
					else if (orderStatus == 4) {
						link = "已过期";
					}
					else if (orderStatus == 5) {
						link = "已完成";
					}
					else if (orderStatus == 6) {
						link = "重新规划";
					}
					return link;
				},
				targets : 2
			} ]
		});
	};
	return {
		init : function() {
			showTable();
		},
		search: function () {
			_dataTable.destroy();
			showTable();
		}
	};
}();

/*
 * $(function () { GuideOrderController.init(); });
 */