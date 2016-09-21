var VisitorCenterController = function(){
	var _dataTable=null;
	var showTable = function(){
		_dataTable = $('#table').DataTable({
					language:{
						url: "/js/lib/datatables/Chinese.json"
					},
					lengthChange:false,
					searching:true,
					ordering:true,
					processing:true,
					serverSide:true,
					ajax: "/center/search",
					columns : [  
					           {data : "planOrder.planOfferName", defaultContent : ""},
							{data : "orderNumber", defaultContent : ""}, 
							{data : "createTime", defaultContent : ""},
							{data : "startDate", defaultContent : ""},
							{data : "endDate", defaultContent : ""},
							{data : "purposes", defaultContent : ""}, 
							{data : "orderStatus", defaultContent : ""}, 
							{ data: null, orderable: false }
							],
columnDefs:[
           /* {
            	render: function(data,type,row){
            		var planOrder = row.planOrder;
            		alert(planOrder);
            		if(planOrder==null){
            			alert("");
            			link="";
            		}else{
            			link=planOrder.planOfferName;
            		}
            		return link;
            	},
            targets:0
            },*/
            {
            	render: function(data,type,row){
            		var link="";
            		var orderStatus = row.orderStatus;
            		
            		var id = row.id;
            		if (orderStatus == 0) {
            			link="<a href='/order/"+id+"/detail' >"+row.orderNumber+"</a>"
            		}
            		else if (orderStatus == 1) {
            			link="<a href='/order/"+id+"/message' ' >"+row.orderNumber+"</a>"
            		}
            		else if (orderStatus == 2) {
            			link="<a href='/order/orderdetail?orderId="+id+"'>"+row.orderNumber+"</a>";
            		}
            		else if (orderStatus == 3) {
            			link="<a href='/order/"+id+"/plan2'>"+row.orderNumber+"</a>";
            		}
            		else if (orderStatus == 4) {
            			link="<a href='/order/"+id+"/tomessage'>"+row.orderNumber+"</a>";
            		}
            		else if (orderStatus == 5) {
            			link="<a href='/order/"+id+"/plan2'>"+row.orderNumber+"</a>";
            		}
            		else if (orderStatus == 6) {
            			link="<a href='/order/"+id+"/message'>"+row.orderNumber+"</a>";
            		}
            		else if (orderStatus == 8) {
            			link="<a href='/order/"+id+"/message'>"+row.orderNumber+"</a>";
            		}
            		return link;
            	},
            	targets:1
            },
            {
            	render: function (data, type, row) {
            		var orderStatus = "";
            		if (data == 0) {
            			orderStatus = "未结算";
            		}
            		else if (data == 1) {
            			orderStatus = "已提交";
            		}
            		else if (data == 2) {
            			orderStatus = "已规划";
            		}
            		else if (data == 3) {
            			orderStatus = "已结算";
            		}
            		else if (data == 4) {
            			orderStatus = "已过期";
            		}
            		else if (data == 5) {
            			orderStatus = "已完成";
            		}
            		else if (data == 6) {
            			orderStatus = "重新规划";
            		}else if(data == 8){
            			
            			orderStatus = "已预订";
            		}
            		return orderStatus;
            	},
            	targets: 6
            },
            {
            	
            	render: function (data, type, row) {
            		var orderId = row.id;
            		var orderStatus = row.orderStatus;
            		var planOfferName=row.planOrder.planOfferName;
            		
            		if(orderStatus==0){
            			var link = "<a href='javascript:VisitorCenterController.deleteOrder(" + orderId + ")'>删除</a>";
            		}else if(planOfferName=='行程规划+一站预订+导游服务'||planOfferName=='行程规划+一站预定'){
            			var link = "<a href='/center/addTraA?orderId="+orderId+"'>去预定</a>";
            		}	
            		else{
            			var link = "删除";
            		}
            		return link;
            	},
            	targets: 7
            }
            ]
				});
	};
	return {
		init : function() {
			showTable();
		},
//		删除
		deleteOrder : function(id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type : "DELETE",
				url : "/center/" + id,
				success : function() {
					// 刷新表格
					_dataTable.ajax.reload();
				}
			});
		}
	};
}();

$(function () {
	VisitorCenterController.init();
});

/*function toOnclik(){
	var orderStatus = row.orderStatus;
	if(orderStatus == 1 || orderStatus==6){
		alert("您的订单正在规划中");
	}else{
		
	}
}*/
