var childInformationController = function () {
	var _app = angular.module("_app", []);
	_app.controller("childInformationController", function($scope, $http) {
		//查询课程级别下面的对应的班级
		$scope.change = function(id) {
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/childinformation/findLessonNameByClassId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data));
			    	$scope.CourseCode=JSOG.decode(data);
			    
			    }
			 });
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/childinformation/findByCourseId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data));
			    	$scope.CourseClass=JSOG.decode(data);
			    }
			 });
		    };
		//查询家长对应的孩子
		$scope.change1=function(id){
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/childinformation/findByUserId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data))
			    	$scope.ChildName=JSOG.decode(data);
			    }
			 });
		};
/*		//查询班级对应的课程
		$scope.getLesson=function(id){
			alert(2);
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/childinformation/findByClassId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode("aaaaaa"+data))
			    	$scope.courseCode=data;
			    }
			 });
		};*/
	//根据学校查询班级
		$scope.changeschool=function(id){
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/schoolclass/findByClassId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data))
			    	$scope.SchoolClass=JSOG.decode(data);
			    }
			 });
			$.ajax({
				async : false,
			    type: "Get",
			    url: "/schoolclass/findByclasschildId?id="+id,
			    success: function (data) {
			    	console.log(JSOG.decode(data))
			    	$scope.Student=JSOG.decode(data);
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
	
	
	

/*	$('#Course').change(function(){
		var id=$(this).val();
		alert(id+"id");
			$.ajax({
			    type: "Get",
			    url: "/childinformation/findByCourseId?id="+id,
			    success: function (data) {
			    	$scope.CourseClass=data;
			    }
			    	
			 });
			    
			});*/
	
	
	return {
		init : function () {

			   
		}
	};
}();

$(function () {
	childInformationController.init();
	//知识财脑指数
	$("#addknowledge").click(function(){
		if($(".appendknowledge").size()<2){ 
	$("#appendknowledge").append("<div style='margin-top:10px;' class=' appendknowledge' name='appendknowledge'><input type='text' id='knowledgename' class='form-control knowledgename' name='knowledgename' style='width: 20% !important;display:inline !important' required='required' value=''/>&nbsp; <input type='text' id='knowledcount' class='form-control knowledcount' name='knowledcount' style='width: 20% !important;display:inline !important'  required='required' />&nbsp;<input type='button'  class='btn btn-primary deleteknowledge' style='width: 10%;' id='deleteknowledge' value='删除' /></div>");
		}else{
			$("#textdanger1").css("display","block");
		}
	});
	 $('#deleteknowledge').live('click', function() {
		 $(this).parents(".appendknowledge").remove();
		
 });
	 //应用财脑指数
	 $("#addapplications").click(function(){  
		 if($(".appendapplications").size()<2){
		$("#appendapplications").append("<div style='margin-top:10px;' class=' appendapplications' name='appendapplications'><input type='text' id='applicationsname' class='form-control applicationsgename' name='applicationsgename'  style='width: 20% !important;display:inline !important' required='required'/>&nbsp; <input type='text' id='applicationscount' class='form-control applicationscount'  name='applicationscount'  style='width: 20% !important;display:inline !important'  required='required' />&nbsp;<input type='button'  class='btn btn-primary deleteapplications' style='width: 10%;' id='deleteapplications' value='删除' /></div>");         
		 }else{
				$("#textdanger2").css("display","block");
			}
		 });
		 $('#deleteapplications').live('click', function() {
			 $(this).parents(".appendapplications").remove();
	 });
		 
	
});
