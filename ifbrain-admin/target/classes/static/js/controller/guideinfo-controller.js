var UserController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/guidetaskarrangementtime/search",
			columns: [
                { data:"userRegister.firstName"},   
			    { data:"userRegister.lastName"},
			    { data:"birthday"},
			    { data:"userRegister.email"}, 
			    { data:"nationality"},
			    { data:"passportCode"},
			    { data:"mobile"},
			    { data:"driveLicenseCode"},   
			    { data:"hostCity.cityName"},
			    { data:"toOtherCity"},
			    { data:"paymentMethod"},   
			    { data:"address"},
			    { data:"payalAccount"},
			    { data: null,orderable: false}
			],
			columnDefs: [{
                render: function (data, type, row) {
                var	guideinfoId=row.id;
                	 var link = "<a href='" + Web.contextPath + "/guideinfo/update/" + guideinfoId + "'>修改</a>"; 
              	   return link;
                },
                targets: 13
            }
        ]
		});
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		}
	};
}();

$(function () {
	UserController.init();
});
