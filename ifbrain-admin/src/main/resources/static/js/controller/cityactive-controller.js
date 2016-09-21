var CityActiveController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/cityactive/search";
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
						    { data: "country" },
						    { data: null },
						    { data: "enName" },
						    { data: "airportCode" },
						    { data: null }
						],
				columnDefs : [{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.name;
					},
					targets : 0
					
				},/*{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.filePath;
					},
					targets : 2
					
				},*/{
					
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var link = "&nbsp;<a href='/cityactive/update/" + id + "'>修改</a>" ;
	                	  /* link +="&nbsp;<a href='/cityactive/airport/" + id + "'>机场代码管理</a>"*/
	                	   return link;
	                   },
	                   targets: 4
	               },{
					
	                   render: function (data, type, row) {
	                	   var cityName = row.cityName;
	                	   var id=row.id;
	                	  var link = "<a href='/cityactive/detail/" + id + "'>"+cityName+"</a>";
	                	  return link;
	                   },
	                   targets: 1
	               }]
			});
		})
	
		
		
		
		
		
		
		/*_dataTable = $("#table").DataTable({
			language: {
                url: Web.contextPath + "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax: Web.contextPath + "/cityactive/search",
			columns: [
			    { data: "id",visible: false },
			    { data: "supplieractive.country" },
			    { data: "cityname" },
			    { data: "image.filePath" },
			    { data: null }
			],
			columnDefs: [
			              {
				
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var counryt=row.supplieractive.country;
			                	  var link = "&nbsp;<a href='javascript: CityActiveController.deleteCityActive(" + id + ")'>删除</a>";
			                	 alert(counryt);
			                	  return link;
			                   },
			                   targets: 4
			               },
			              
			           ]
		});*/
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
