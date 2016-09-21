var EntryGoodsController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/entrygoods/search";
		
		$.get(url,function(data) {
			console.log(JSOG.decode(data))
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: true,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    { data: "commodityName"},
						    { data: "commodityMall.commodityType" },
						    { data: "demandLevel.demandName"},
						    { data: "price"},
						    { data: "commodityQuantity"},
						    { data: null }
						],
				columnDefs : [{
					
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "&nbsp;<a href='/entrygoods/update/" + id + "'>修改</a>" ;
	                	   return link;
	                   },
	                   targets: 5
	               }]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		}
	};
}();

