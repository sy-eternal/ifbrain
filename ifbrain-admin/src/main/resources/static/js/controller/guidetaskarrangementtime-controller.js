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
                { data:"id",visible: false},   
			    { data:null, orderable: false},
			    { data:null, orderable: false}
			],
			columnDefs: [{
                render: function (data, type, row) {
             	   var excursionBookDate = row.excursionGuide.excursionBookDate;
             	  var standardBookDate=row.standardGuide.standardBookDate;
             	   var link =excursionBookDate+","+standardBookDate;
             	   return link;
                },
                targets: 1
            },{
                render: function (data, type, row) {
              	   var excursionBookDate = row.excursionGuide.excursionBookDate;
              	  var standardBookDate=row.standardGuide.standardBookDate;
              	   var link =excursionBookDate+","+standardBookDate;
              	   return link;
                 },
                 targets:2
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
