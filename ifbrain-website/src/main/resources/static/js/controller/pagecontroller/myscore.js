$(function(){
	//初始化child头像
var flagvalue=$("#flagaa").val();
if(flagvalue!=""){
	 $(".portlet-body.mypanel img").each(function() {
		 var value=$(this).parent().find('input').attr('value');
		 
		 if(flagvalue==value){
			 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
		 }
		 $(this).click(function () {
			 var id=$(this).parent().find('input').attr('value');
			 window.location.href="/itemmanagement/myscore?childId="+id;
			});
	 });
}
	
});