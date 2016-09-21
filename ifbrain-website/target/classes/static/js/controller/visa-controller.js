var VisaController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		var url ="/visa/search";
		$.get(url,function(instructionfilerelateq) {
			console.log(JSOG.decode(instructionfilerelateq));
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: false,
	            ordering: false,
				processing: false,
				data : JSOG.decode(instructionfilerelateq),
				columns: [
						    { data: "document" },
						  /*  { data: "document"},*/
						    { data: null },
						],
				columnDefs : [{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.fileName;
					},
					targets : 0
					
				},/*{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.updateTime;
					},
					targets : 1
					
				},*/{
					  render: function (data, type, row) {
	                	  var id = row.id;
	                	  var link = "<a href='/visa/download/" + id + "'>下载</a>";
	                	   return link;
	                   },
	                   targets: 1
	               }]
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

