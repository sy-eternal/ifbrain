var SpecialCarController = function() {
	var _dataTable = null;
	var showTable = function() {
		var url = "/specialCar/search";
		$.get(url,function(data) {
							console.log(JSOG.decode(data));
							_dataTable = $("#table")
									.DataTable(
											{
												language : {
													url : "/js/lib/datatables/Chinese.json"
												},
												lengthChange : false,
												searching : true,
												ordering : true,
												processing : true,
												data : JSOG.decode(data),
												columns : [ {
													data : "supplier"
												}, {
													data : "cartype"
												}, {
													data : null,
													orderable : false
												} ],
												columnDefs : [
														{
															render : function(
																	data, type,
																	row) {
																if (!data) {
																	return "";
																}
																return data.cnName;
															},
															targets : 0
														},
														{
															render : function(
																	data, type,
																	row) {
																if (!data) {
																	return "";
																}
																return data;
															},
															targets : 1
														},
														{
															render : function(
																	data, type,
																	row) {
																var specialCarId = row.id;
																var link = "<a href= /specialCar/update/"
																		+ specialCarId
																		+ ">修改</a>";
																link += "&nbsp&nbsp<a href=/specialCar/specialCarRate/"
																		+ specialCarId
																		+ ">费率管理</a>";
																return link;
															},
															targets : 2
														} ]
											});
						})
	};
	return {
		init : function() {
			showTable();
		}
	};
}();
