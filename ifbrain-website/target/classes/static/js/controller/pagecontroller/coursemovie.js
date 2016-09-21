
$(function(){
	/*  $(".shipinshowbutton").eq(0).css("background-color","white"); */
	 //根据flagvalue来判断进入该页面后选中第几个图片
	 var flagvalue=$("#flagaa").val();
	 if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
				
			 }
		 });
	 }
	
	
	
	
	//根据孩子id获得相应孩子的评估信息
	$(".portlet-body.mypanel img").each(function() {
		 $(this).click(function () {
			 var id=$(this).parent().find('input').attr('value');
			window.location.href="/coursemovie/coursemovie?childId="+id;
			});
	});
	
	/*//点击级别显示相应的视频信息列表
	 $(".shipinshowbutton").each(function() {
           var val=$(this).attr("id");
		   var flagvalue=$("#coursevalue").val();      
		 if(val==flagvalue){
			 $(this).css('background-color','white');
		 } 
		 $(this).click(function () {
			 var courseid=$(this).attr('id');
			 var childId=$("#flagaa").val();
			 var toogleval=$("#toggleshow").val();
		     
			window.location.href="/coursemovie/showcourseinformations?courseid="+courseid+"&childId="+childId+"&toogleval="+toogleval;
		     
		 });
	    }); */
	
	/*$(".ordinalNumber").click(function(){
		var ordinalNumber=$(this).attr('value');
		var classId =$(this).find('.classId').attr('value');
		 var childId=$("#flagaa").val();
		 var lessonName=$("#lessonName").val();
		 var courseid=$("#coursevalue").val();
		window.location.href="/coursemovie/showvideoinformation?ordinalNumber=" + ordinalNumber+"&classId="+classId+"&childId="+childId+"&courseid="+courseid;
	});
	//点击集数按钮显示相应的视频信息
		$(".ordinalNumber").each(function() {
			var val=$(this).attr('value');
            var flagvalue=$("#ordinalNumberclick").val();
			 if(val==flagvalue){
				 $(this).addClass('shipinshowbutton1');
			 } 
			});*/
	
		$(".ordinalNumber").each(function() {
			var val=$(this).attr('value');
		    var flagvalue=$("#ordinalNumberclick").val();
			 if(val==flagvalue){
				 $(this).css('background-color','#ffd464');
				 $(this).css("color","#000000");
			 } 
				 $(this).click(function () {
					 var childId=$("#flagaa").val();
					 var ordinalNumber=$(this).val();
					 var courseid=$("#coursevalue").val();
					 var classid=$("#listfindbyclassid").val();
					 $("#ordinalNumberclick").val(ordinalNumber);
					 window.location.href="/coursemovie/showvideoinformation?childId="+childId+"&ordinalNumber="+ordinalNumber+"&courseid="+courseid+"&classid="+classid;
				 })
		});
	
		//点击级别显示相应的视频信息列表
		 $(".shipinshowbutton").each(function() {
			 var val=$(this).find('input').attr('id');
			   var flagvalue=$("#listfindbyclassid").val();  
			 if(val==flagvalue){
				 $(this).find('input').css('background-color','white');
			 } 
			 $(this).click(function () {
				 var courseid=$(this).attr('id');
				 var childId=$("#flagaa").val();
				 var val=$(this).find('input').attr('id');
				window.location.href="/coursemovie/showcourseinformations?courseid="+courseid+"&childId="+childId+"&classid="+val;
			   });
		    });
});