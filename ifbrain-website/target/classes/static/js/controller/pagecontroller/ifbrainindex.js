$(function(){
	
		//初始化child头像
	var flagvalue=$("#flagaa").val();
	if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
			 }
		 });
	}
	//点击课程
	$(".ordinalNumberChart").each(function() {
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
				 window.location.href="/ifbrainindexassement/showchildifbrainchart?childId="+childId+"&ordinalNumber="+ordinalNumber+"&courseid="+courseid+"&classid="+classid;
			 })
	});

	//点击级别显示
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
			window.location.href="/ifbrainindexassement/showcourseinformations?courseid="+courseid+"&childId="+childId+"&classid="+val;
		   });
	   }); 
	   
	//根据孩子id获得相应孩子的评估信息
	$(".portlet-body.mypanel img").each(function() {
		 $(this).click(function () {
			 var id=$(this).parent().find('input').attr('value');
			window.location.href="/ifbrainindexassement/ifbrainindex?childId="+id;
			});
	});
	
	
	
	//点击折线图评论
	 $(".ifbrainindexconcilck").click(function () {
		 var childId=$("#flagaa").val();
		 var ifbrainindexconcomment=$("#ifbrainindexconcomment").val();
		 var courseid=$("#coursevalue").val();
		 var classid=$("#listfindbyclassid").val();
		var ordinalNumber=$("#ordinalNumberclick").val();
		if(ordinalNumber==""){
			
		}else{
		 window.location.href="/ifbrainindexassement/addifbrainindexcon?ifbrainindexconcomment="+ifbrainindexconcomment+"&ordinalNumber="+ordinalNumber+"&courseid="+courseid+"&classid="+classid+"&childId="+childId;
		
		} 
	 })
	
	 
	 //点击柱状图评论
	 $(".classscoreclick").click(function () {
		 var childId=$("#flagaa").val();
		 var classscoreccontent=$("#classscoreccontent").val();
		 var courseid=$("#coursevalue").val();
		 var classid=$("#listfindbyclassid").val();
		var ordinalNumber=$("#ordinalNumberclick").val();
			if(ordinalNumber==""){
			
		}else{
		 window.location.href="/ifbrainindexassement/addifbrainindexclassscorec?classscoreccontent="+classscoreccontent+"&ordinalNumber="+ordinalNumber+"&courseid="+courseid+"&classid="+classid+"&childId="+childId;
		
		}
	 })
	 
	 //删除
	 $(".deletecommentlist").each(function() {
		 $(this).click(function () {
			
			 var childId=$("#flagaa").val();
			 var courseid=$("#coursevalue").val();
			 var classid=$("#listfindbyclassid").val();
			var ordinalNumber=$("#ordinalNumberclick").val();
			var deleteid =$(this).attr("id");
				if(ordinalNumber==""){
			}else{
			 window.location.href="/ifbrainindexassement/deletecommentlistindex?ordinalNumber="+ordinalNumber+"&courseid="+courseid+"&classid="+classid+"&childId="+childId+"&deleteid="+deleteid;
			}
		
		 })
	 })
	
	});
	