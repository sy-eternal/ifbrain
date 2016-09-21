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
			ajax: Web.contextPath + "/guidecarpass/search",
			columns: [
                { data: "createTime"}, 
			    { data: null, orderable: false },
			    { data: "carType"},
			    { data: "guideCarManage"}, 
			    { data: null, orderable: false }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var userRegister = row.user;
			                	   var firstName = userRegister.firstName;
			                	   var lastName = userRegister.lastName; 
			                	   return firstName + lastName;
			                   },
			                   targets: 1
			               },
			               {
			                   render: function (data, type, row) {
			                   if(!data){
			                	   return "";
			                   }
			                     return  data.classes;
			                   },
			                   targets: 3
			               },
			               {
			                   render: function (data, type, row) {
			                	   var carId = row.id;
			                	   var link = "<a href='" + Web.contextPath + "/guidecarpass/manage/" + carId + "'>管理</a>"; 
			                	   return link;
			                   },
			                   targets: 4
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
