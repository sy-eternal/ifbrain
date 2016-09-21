var StudentSeletcController = function () {
	var _app = angular.module("_app", []);
	_app.controller("studentselectcontroller", function($scope, $http) {
	//根据学校查询班级
		$scope.changeschool=function(id){
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/schoolclass/findByClassId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data))
			    	$scope.CourseClass=JSOG.decode(data);
			    }
			 });
		};
		
		/*$scope.changeschoolclass=function(id){
			$.ajax({ 
				async : false,
			    type: "Get",
			    url: "/schoolclass/findBychildId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data))
			    	$scope.Student=JSOG.decode(data);
			    }
			 });
		};*/
	});
	return {
		init : function () {
		}
	};
}();
function getstudent(id){
	var student=$("#Student");
	$("#Student").empty();
	$.ajax({ 
		async : false,
	    type: "Get",
	    url: "/schoolclass/findBychildId?id="+id,
	    success: function (data) {
	    	for(var  i=0;i<data.length;i++){
	    		student.append("<option value=" + data[i].id + " selected=\"true\">" + data[i].studentName + "</option>");
	    	 	$("#studentvalue").val(data[i].id)
	    	}
	    	/*alert(studentvalue)*/
	   
	    }
	 });
	
}

function getStudentname(){
	$("#studentvalue").val($("#Student").val())
}
