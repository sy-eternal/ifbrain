var ChildShoppingMallController = function () {
	var _dataTable = null;
	var showTable = function () {
		//var url ="/childshoppingmall/search";
		var url ="";
		$.get(url,function(data) {
			console.log(data);
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true/*,
				data : JSOG.decode(data)*//*,
				columns: [
						    { 
						    	data: null,
						    	defaultContent:""
						    }*//*,
						   { 
						    	data: "shoppingmallCommodity.commodityName",
						    	defaultContent:""
						    },
						    { 
						    	data: "shoppingmallCommodity.demandLevel.demandName",
						    	defaultContent:""
						    },
						    { 
						    	data: "shoppingmallCommodity.commodityMall.commodityType",
						    	defaultContent:""
						    },
						    { 
						    	data: "sumPrice",
						    	defaultContent:""
						    }*/
						/*],*/
			           /* columnDefs : [{
		                   render: function (data, type, row) {
		                	   var name = data.name;
		                	   var id=data.id;
		                	   var link = "&nbsp;<a href='/childshoppingmall/detail/" + id + "'  style='text-decoration:underline'>"+name+"</a>";
		                	   return link;
		                   },
		                   targets: 0
		               }
		            ]*/
	           
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

	};
}();

/*$(function () {
	ChildController.init();
});*/