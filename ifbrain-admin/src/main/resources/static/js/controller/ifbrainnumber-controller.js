var IfbrainNumberinforController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/ifbrainnumber/search";
		
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
						    { data: "id", visible: false },
						    { data: "child.name" },
						    { data: "brainnumber"},
						    { data: null }
						],
				columnDefs : [{
					
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "&nbsp;<a href='/ifbrainnumber/update/" + id + "'>修改</a>" ;
	                	   return link;
	                   },
	                   targets: 3
	               }]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
	};
}();

