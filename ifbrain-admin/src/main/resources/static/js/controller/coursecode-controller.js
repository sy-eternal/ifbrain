var CourseCodeController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/coursecode/search";
		//var url ="";
		$.get(url,function(data) {
			_dataTable = $("#table").DataTable({
				language : {
					url : "/js/lib/datatables/Chinese.json"
				},
				lengthChange: false,
	            searching: true,
	            ordering: false,
				processing: true,
				data : JSOG.decode(data),
				columns: [
						    { 
			    	         data: "id",visible: false 
			                },
						    { 
						    	data: "courselevel",
						    },
						   { 
						    	data: null,
						    },
						    { 
						    	data: null,
						    },
						    { 
						    	data: "classTime",
						    },
						    { 
						    	data: "courseDescription",
						    }, {
								data : null,
							}
						],
	            columnDefs : [
	                          {
					render : function(data, type,row) {
						var ordinalNumber = data.ordinalNumber;
	                	   return "第"+ordinalNumber+"节课";
					},
					targets : 2
				},{
					render : function(data, type,row) {
	                	   return data.lessonName;
					},
					targets : 3
				},
				{
					render : function(data, type,row) {
						 var id = row.id;
	                	   var link = "&nbsp;<a href='/coursecode/update/" + id + "'>修改</a>" ;
	                	   return link;
					},
					targets : 6
				}
	            ]
			});
		})
	};
	
	return {
		// main function to initiate the module
		init : function () {
			showTable();
		},
	};
}();

