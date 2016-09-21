var CitySchoolController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/cityschool/search";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: true,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    { data: "cityName" },
						    { data: "createTime" },
						    { data: null }
						],
				columnDefs : [{
					
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "&nbsp;<a href='/cityschool/update/" + id + "'>修改</a>" ;
	                	   return link;
	                   },
	                   targets: 2
	               }]
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},

		deleteCityActive: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url: "/cityactive/" + id,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		}
	};
}();

/*$(function () {
	CityActiveController.init();
});*/
