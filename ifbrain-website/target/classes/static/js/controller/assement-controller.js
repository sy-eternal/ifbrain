var assementController = function () {
	var _app = angular.module("_app", []);
	_app.controller("childInformationController", function($scope, $http) {
		//查询课程级别下面的对应的班级
		
		$(".shipinshowbutton").each(function() {
			 $(this).click(function () {
				 $("#coursevalue").val($(this).attr('id'));
				 var courseid=$(this).attr('id');
				 var childId=$("#flagaa").val();
				 var courseid=$("#coursevalue").val();
				
				/* $.ajax({
						async : false,
						type : "Get",
						url : "/assessment/showcourseinformation?courseid="+courseid+"&amp;childId="+childId,
						success : function(data) {
							$("#classtime").val(data.classTime);
							$("#coursedescription").val(data.courseDescription);
						//	$("#videos").attr("src","/video/play/"+data.id);
							//$("#example_video_1").attr("src","/video/play/"+data.id);
							$("#dataordinal").val("第"+data.ordinalNumber+"节课");
							$("#lessonName").val(data.lessonName); 
						}
					});
				 // 视频
				 $.ajax({
				     async : false,
					type : "Get",
					url : "/assessment/showvideolist? ordinalNumber="+$("#videoids").val()+"&amp;childId="+childId+"&amp;courseid="+courseid,
					success : function(data1) {
						$("#videos").attr("src","/video/play/"+data1.id);
						$("#example_video_1").attr("src","/video/play/"+data1.id);
					}
				});*/
				 //第几节
				 $.ajax({
				     async : false,
					type : "Get",
					url : "/assessment/showcourselistinformationlist?courseid=" + courseid+"&amp;childId="+childId,
					success : function(data1) {
						
						$scope.ordinalNumber=JSOG.decode(data1);
						
						//$("#ordinalNumber").val(data1.ordinalNumber);
					}
				});
			   });
		    });
		
		
		
		//点击集数按钮显示相应的视频信息
		/*$(".ordinalNumber").each(function() {
			 $(this).click(function () {
				 var ordinalNumber=$(this).attr('value');
				 var classId =$("#classId").val();
				 var childId=$("#flagaa").val();
				 var lessonName=$("#lessonName").val();
				 var courseid=$("#coursevalue").val();
				 $("#videoids").val(ordinalNumber);
				 //查询课和lessonname
				  $.ajax({
						async : false,
						type : "Get",
						url : "/assessment/showvideoinformation?ordinalNumber=" + ordinalNumber+"&amp;classId="+classId+"&amp;childId="+childId+"&amp;courseid="+courseid,
						success : function(data) {
							$("#dataordinal").val("第"+data.ordinalNumber+"节课");
							$("#lessonName").val(data.lessonName);
							
						}
					}); 
				 //查询视频
				   $.ajax({
					     async : false,
						type : "Get",
						url : "/assessment/showvideo?classId=" + classId+"&amp;ordinalNumber=" + ordinalNumber+"&amp;childId="+childId+"&amp;courseid="+courseid,
						success : function(data1) {
							$("#videos").attr("src","/video/play/"+data1.id);
							$("#example_video_1").attr("src","/video/play/"+data1.id);
						}
					}); 
				   //查询课程简介和时间
					 $.ajax({
							async : false,
							type : "Get",
							url : "/assessment/showcoursecodeinformation?ordinalNumber="+ $(this).attr('value')+"&amp;childId="+childId+"&amp;courseid="+courseid,
							success : function(data) {
								 alert(data.classTime) 
								$("#classtime").val(data.classTime);
								$("#coursedescription").val(data.courseDescription);
								
							}
						});
				});
			
		});*/
		

	
	
	});
	
	
	


	
	return {
		init : function () {

			   
		}
	};
}();

$(function () {
	assementController.init();
});
