var ApplicationInfoController = function () {

	var _dataTable = null;
	     var visaOrderId=$('#visaOrderId').val();
	    
	var showTable = function () {
		var url ="/visaaudit/search?visaOrderId="+visaOrderId;
		$.get(url,function(applicationInfos) {
			console.log(JSOG.decode(applicationInfos));
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: false,
	            ordering: true,
				processing: true,
				data : JSOG.decode(applicationInfos),
				columns: [
						    { data: "name" },
						    { data: "document"},
						    { data: null },
						],
				columnDefs : [{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.fileName;
					},
					targets : 1
					
				},
				{
					  render: function (data, type, row) {
	                	  var id = row.id;
	                	  var filePath = row.document.filePath;
	                	  
	                	  var link = "<a href='/visaaudit/download/" + id + "'>下载</a>";
	                	   return link;
	                   },
	                   targets: 2
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

