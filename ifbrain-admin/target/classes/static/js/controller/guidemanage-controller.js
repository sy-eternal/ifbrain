var guidemanageController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		var url = Web.contextPath +"/guidemanage/search";
		$.get(url, function (dateUser) {
			console.log(JSOG.decode(dateUser));
			
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            ordering: true,
			processing: true,
			data :JSOG.decode(dateUser),
			columns: [
                { data:"firstName"}, 
			    { data:"lastName"},
			    { data:"email"},
			    { data:"mobile"}, 
			    { data:"guide.hostCity"}, 
			    { data: null, orderable: false }
			],
			columnDefs: [{
								render : function(data, type, row) {
									if(!data){
										return "";
									}
									return data.cityName;
								},
								targets : 4
							},
			               {
			                   render: function (data, type, row) {
			                	   var guidemanageId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/guidemanage/audit/" + guidemanageId + "' style='color:#0000E3 '>审核</a>";
			                	   return link;
			                   },
			                   targets: 5
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
	guidemanageController.init();
});*/
