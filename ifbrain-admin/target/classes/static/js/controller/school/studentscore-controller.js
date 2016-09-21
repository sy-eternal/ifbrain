var StudentScoreController = function () {
	var _dataTable = null;
	var showTable = function () {
		var url ="/student/searchscore";
		//var url ="";
		$.get(url,function(data) {
			console.log(JSOG.decode(data))
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
						    	data: "student.schoolClass.school.scName"
						    },
						   { 
						    	data: "student.schoolClass.name"
						    
						    },
						   { 
						    	data: "student.studentName"
						    
						    },{ 
						    	data: "exam.examName"
						    },
						   { 
						    	data: "examsumscore.sumScore"
						    
						    }
						],
	            columnDefs : [
	            ]
			});
		})
	};
	
	return {
		init : function () {
			showTable();
		},
	};
}();
