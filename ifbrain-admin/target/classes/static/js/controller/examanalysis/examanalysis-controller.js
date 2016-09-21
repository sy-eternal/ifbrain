var ExamAnylysisController = function () {
	var _dataTable = null;
	var showTable = function () {
		var courseid=$("#courseid").val();
		var unit=$("#unit").val();
		var url ="/analysisofexamination/search?courseid="+courseid+"&unit="+unit;
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
						    	data: null
						    },
						    { 
						    	data: "createTime"
						    }
						],
	            columnDefs : [{
	                   render: function (data, type, row) {
	                	   var id = data.id;
	                	   var link = "<a href='javascript: ExamAnylysisController.anylysis(" + id + ")'>"+data.examName+"</a>";
	                	   return link;
	                   },
	                   targets: 0
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
		anylysis: function (id) {
			window.location.href="/analysisofexamination/analysis?id="+id;
		}
	};
}();

