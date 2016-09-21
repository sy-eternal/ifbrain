var MarginController = function () {
	var _dataTable = null;
	
	var showTable = function () {
		_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/depositactivity/search",
			columns: [
					    { data: "margintype" },
					    { data: "marginprice" },
					    { data: "createTime" },
					    { data: null }
					],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var link = "&nbsp;<a href='/depositactivity/update/" + id + "'>修改</a>" ;
			                	   return link;
			                   },
			                   targets: 3
			               } ]
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
	MarginController.init();
});
