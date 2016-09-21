var VisaPriceController=function(){
		var _dataTable = null;

		var showTable = function() {
			_dataTable = $("#table")
					.DataTable(
							{
								language : {
									url : Web.contextPath + "/js/lib/datatables/Chinese.json"
								},
								lengthChange : false,
								searching : false,
								ordering : true,
								processing : true,
								serverSide : true,
								bPaginate:false,
								ajax : Web.contextPath + "/visaprice/search",
								columns : [ {
									data : "id",
									visible : false
								}, {
									data : "type"
								}, {
									data : "price"
								}, {
									data : "updatetime"									
								}, {
									data : null,
									orderable : false
								} ],
								columnDefs : [
										{
											render : function(data, type, row) {
												var visaId = row.id;
												var link = "<a href='"
														+ Web.contextPath
														+ "/visaprice/update/"
														+ visaId + "'>修改</a>";
												return link;
											},
											targets : 4
										} ]
							});
		};

		return {
			// main function to initiate the module
			init : function() {
				showTable();
			},
		};
	}();
	$(function() {
		VisaPriceController.init();
	});