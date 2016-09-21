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
	$(".ordinalNumberDaibi").each(function() {
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
			 window.location.href="/daibibankassement/showchildifbraindaibi?childId="+childId+"&ordinalNumber="+ordinalNumber+"&courseid="+courseid+"&classid="+classid;
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
		window.location.href="/daibibankassement/showcourseinformations?courseid="+courseid+"&childId="+childId+"&classid="+val;
	   });
   }); 
   
//根据孩子id获得相应孩子的评估信息
$(".portlet-body.mypanel img").each(function() {
	 $(this).click(function () {
		 var id=$(this).parent().find('input').attr('value');
		window.location.href="/daibibankassement/daibibank?childId="+id;
		});
  });
});