var WishController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/wish/search?childid="+$("#childid").val();
		//var url ="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    { 
						    	data: "commodityName"
						    },
						   { 
						    	data: "price"
						    
						    },
						    { 
						    	data: null
						    },
						    { 
						    	data: "createTime"
						    }
						],
			            columnDefs : [{
			                   render: function (data, type, row) {
			                	   var link = "";
			                	   var payStatus = data.payStatus;
			                	   if(payStatus=="0"){
			                		   link="未支付";
			                	   }else{
			                	    link="已支付";
			                	   }
			                	   return link;
			                   },
			                   targets: 2
			               }
			            ]
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		}
	};
}();

/*$(function () {
	ChildController.init();
});*/