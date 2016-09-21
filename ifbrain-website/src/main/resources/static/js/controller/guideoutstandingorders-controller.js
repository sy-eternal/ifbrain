var UserController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: true,
            ordering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/guideoutstandingorders/search",
			columns: [
                 { data: "id",visible:false},  
                { data: null, orderable: false},   
			    { data:"startDate"},
			    { data:"endDate"},
			    { data: null, orderable: false}
			],
			columnDefs: [
										{
										    render: function (data, type, row) {
										    	 var orderNumber = row.orderNumber;
										    	var  id=row.id;
							            // var link = "<a href='" + Web.contextPath + "/guideoutstandingorders" + orderNumber + "'>"+row.orderNumber+"</a>";
							            var  link="<a href='/order/"+id+"/guideOrder'>"+row.orderNumber+"</a>";
							                	 return link;
										    },
										    targets: 1
										}
										             ,{
							                render: function (data, type, row) {
							             	  var firstName = row.firstName;
							           	      var lastName = row.lastName; 
							           	        return firstName + lastName;
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
