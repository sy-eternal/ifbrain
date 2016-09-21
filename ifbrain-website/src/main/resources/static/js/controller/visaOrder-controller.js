var VisaOrderController = function(){
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
					ajax: "/visaOrder/search",
					columns : [  
							{data : "orderNumber"},
							{data : "createTime"},
							{data : "orderStatus"}, 
							{ data: null, orderable: false }
							],
columnDefs:[
            {
            	
            	render: function(data,type,row){
            		var link="";
            		var id = row.id;
            		var orderNumber = row.orderNumber;
            		link="<a href='/visaOrder/"+id+"/detail' >"+orderNumber+"</a>"
            		return link;
            	},
            	targets:0
            },
            {
            	render: function (data, type, row) {
            		var orderStatus = "";
            		if (data == 0) {
            			orderStatus = "未支付";
            		}
            		else if (data == 1) {
            			orderStatus = "已支付";
            		}
            		else if (data == 2) {
            			orderStatus = "资料已提交";
            		}
            		else if (data == 3) {
            			orderStatus = "资料审核通过";
            		}
            		else if (data == 4) {
            			orderStatus = "预约成功";
            		}
            		else if (data == 5) {
            			orderStatus = "面试成功";
            		}
            		else if (data == 6) {
            			orderStatus = "签证申请成功";
            		}
            		else if (data == 7) {
            			orderStatus = "支付失败";
            		}
            		else if (data == 8) {
            			orderStatus = "资料审核失败";
            		}
            		else if (data == 9) {
            			orderStatus = "预约失败";
            		}
            		else if (data == 10) {
            			orderStatus = "签证申请失败";
            		}
            		else if (data == 11) {
            			orderStatus = "已邮寄";
            		}
            		return orderStatus;
            	},
            	targets: 2
            },
            {
            	render: function(data,type,row){
            		var link="";
            		var orderStatus = row.orderStatus;
            		var id = row.id;
            		if (orderStatus == 0) {
            			link="";
            		}
            		else if (orderStatus == 1) {
            			link="<a href='/visaOrder/findheadcount?visaorderId="+id+"'>上传材料</a>";
            		}
            		else if (orderStatus == 2) {
            			link="";
            		}
            		else if (orderStatus == 3) {
            			link="";
            		}
            		else if (orderStatus == 4) {
            			link="<a href='/visaOrder/"+id+"/interview' >预约详情</a>"
            		}
            		else if (orderStatus == 5) {
            			link="";
            		}
            		else if (orderStatus == 6) {
            			link="";
            		}
            		else if (orderStatus == 7) {
            			link="";
            		}
            		else if (orderStatus == 8) {
            			link="<a href='/visaOrder/findheadcount?visaorderId="+id+"'>上传材料</a>";
            		}
            		else if (orderStatus == 9) {
            			link="";
            		}
            		else if (orderStatus == 10) {
            			link="";
            		}
            		return link;
            	},
            	targets:3
            }
            ]
				});
	};
	return {
		init : function() {
			showTable();
		}
	};
}();

$(function () {
	VisaOrderController.init();
});