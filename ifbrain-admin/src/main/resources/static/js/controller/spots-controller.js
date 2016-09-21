var SpotsController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/spots/search";
		$.get(url,function(data) {
			console.log(JSOG.decode(data));
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
						    { data: "cityid" },
						    { data: "supplierid" },
						    { data: "themeRelates"},
						    { data: null },
						    { data: null }
						],
				columnDefs : [{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.cityName;
					},
					targets : 0
				},{
					render : function(data, type, row) {
						if(!data){
							return "";
						}
						return data.cnName;
					},
					targets : 1
					
				},{
					render : function(data, type, row) {
						if (!data) {
							return "";
						}

						var themeName = "";
						for (var i = 0; i < data.length; i++) {
							var themeItem = data[i];
							themeName += ", " + themeItem.theme.theme;
						}

						if (themeName != "") {
							themeName = themeName.substring(2);
						}
						return themeName;
					},
					targets : 2
					
				},{
	                   render: function (data, type, row) {
	                	   var id = row.id;
	                	   var spotsname=row.spotsname;
	                	   var link = "<a href='javascript: SpotsController.deleteSpotsType(" + id + ")'>删除</a>";
	                	    link += "&nbsp;<a href='/spots/update/" + id + "'>修改</a>" ;
	                	    link += "&nbsp;<a href='/spots/tickettype/"+id+"'>门票类型管理</a>"
	                	    return link;
	                   },
	                   targets: 4
	               },  {
	            	   render: function (data, type, row) {
	                 	   var id = row.id;
	            		   var spotsname=row.spotsname;
	                 	   var link = "&nbsp;<a href='/spots/detail/" + id + "'>"+spotsname+"</a>";
	                 	   return link;
	                    },
	                    targets: 3
	               }]
			});
		})
	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	/*_dataTable = $("#table").DataTable({
			language: {
                url: "/js/lib/datatables/Chinese.json"
            },
            lengthChange: false,
            searching: false,
            demoOrdering: true,
			processing: true,
			serverSide: true,
			ajax:"/spots/search",
			columns: [
			    { data: "cityid.id" },    
			    { data: "cityid.cityname" },
			    { data: "supplierid.cnName" },
			    { data: "theme.theme"},
			    { data: null },
			    { data: null }
			],
			columnDefs: [{
			                   render: function (data, type, row) {
			                	   var id = row.id;
			                	   var spotsname=row.spotsname;
			                	   var link = "<a href='javascript: SpotsController.deleteSpotsType(" + id + ")'>删除</a>";
			                	    link += "&nbsp;<a href='/spots/update/" + id + "'>修改</a>" ;
			                	    link += "&nbsp;<a href='/spots/spotstickettype/"+spotsname+"'>门票类型管理</a>";
			                	    link += "&nbsp;<a href='/spots/tickettype/"+id+"'>门票类型管理</a>"
			                	    return link;
			                   },
			                   targets: 5
			               },
			               {
			            	   render: function (data, type, row) {
			                 	   var id = row.id;
			            		   var spotsname=row.spotsname;
			                 	   var link = "&nbsp;<a href='/spots/detail/" + id + "'>"+spotsname+"</a>";
			                 	   return link;
			                    },
			                    targets: 4
			               }
			           ]
		});*/
	};
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
		deleteSpotsType: function (id) {
			if (!confirm("确认要删除吗？")) {
				return;
			}
			$.ajax({
				type: "DELETE",
				url:Web.contextPath +"/spots/" + id,
				success: function () {
					// 刷新表格
				window.location.reload();
				}
			});
		}
	};
}();

/*$(function () {
	SpotsController.init();
});*/
