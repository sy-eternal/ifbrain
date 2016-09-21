$(function(){
	
		//初始化child头像
	/*var flagvalue=$("#flagaa").val();
	if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
			 }
		 });
	}*/
	//点击课程
	$(".todownloadtask").each(function() {
			 $(this).click(function () {
				 var todownloadtaskid=$(this).find('.todownloadtaskid').attr('value');
					var childidtask=$(this).find('.childidtask').attr('value');
					if(childidtask!=""){
						 window.location.href="/ifbraintask/uploadordownloadtask?todownloadtaskid="+todownloadtaskid+"&childidtask="+childidtask;
					}else{
						var childidtask=0;
						 window.location.href="/ifbraintask/uploadordownloadtask?todownloadtaskid="+todownloadtaskid+"&childidtask="+childidtask;
					}
				
			 })
	});

	});
	