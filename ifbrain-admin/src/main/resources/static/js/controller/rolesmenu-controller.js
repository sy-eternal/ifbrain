var RolesMenuController= function(){
	var _dataTable = null;
	var showTable = function() {
		_dataTable = $("#table")
			.DataTable(	{
				language : {
					url : Web.contextPath
							+ "/js/lib/datatables/Chinese.json"
				},
				lengthChange : false,
				searching : true,
				ordering : true,
				processing : true,
				serverSide : true,
				ajax : Web.contextPath + "/member/rolesmenusearch",
				columns : [ 
				         
				            {
					
					data : "roles",
				}, {
					data : null,
					orderable : false
				} ],
				columnDefs : [
								{
									render : function(data, type, row) {
										var roleNames = "";
										for (var i = 0; i < data.length; ++i) {
											var role = data[i];
											roleNames += "," + role.roleName;
										}
										if (roleNames != "") {
											roleNames = roleNames.substring(1);
										}
										return roleNames;
									},
									targets : 0
								} ]
			});
};
return {
	// main function to initiate the module
	init : function() {
		showTable();
	}
};
}();


$(function() {
	RolesMenuController.init();
});
