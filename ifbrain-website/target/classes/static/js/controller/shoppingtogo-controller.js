var shoppingtogoController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
				processing: true
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
	};
}();

