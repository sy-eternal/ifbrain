var MovieMaterialController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/moviematerial/list";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: true,
				processing: true,
				data : JSOG.decode(data),
				columns: [],
				columnDefs : [{},{}]
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

