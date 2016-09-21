$(function(){
	
	//点击级别显示
	$(".clicknavigationbarModule").each(function() {
		 var id=$(this).find("input").attr('id');
		 var navigationbarModulelistid =$("#navigationbarModulelistid").val();
		 
		 if(id==navigationbarModulelistid){
			 $(this).find("input").css("background","#0151a6");
			 $(this).find("input").css("color","white");
		 }
		 $(this).click(function () {
			 var type=$(this).attr('id');
			window.location.href="/footermaterial/index?type="+type;
		   });
	   }); 
	
});