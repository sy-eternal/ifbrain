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
			ajax: Web.contextPath + "/notguidecar/search",
			columns: [
                { data: "createTime"}, 
			   { data: "user"},
			    { data: "carType"},
			   { data: null, orderable: false }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var firstName = data.firstName;
			                	   var lastName = data.lastName;
			                	   return firstName+lastName;
			                   },
			                   targets: 1
			               },
			               {
			                   render: function (data, type, row) {
			                	   var carId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/notguidecar/audit/" + carId + "'>审核</a>"; 
			                	   return link;
			                   },
			                   targets: 3
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
