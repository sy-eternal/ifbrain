var ItemmanagementController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="";
		$.get(url,function(data) {
			_dataTable = $("#mytable").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true
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

