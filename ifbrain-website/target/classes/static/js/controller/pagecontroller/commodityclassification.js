$(function(){
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
	
		//点击商品种类
		$(".classification").each(function() {
			 $(this).click(function () {
				 var flagvalue=$("#flagaa").val();
				 var classification=$(this).text();
				 var classificationfindtype = $(this).find('.classificationfindtype').attr('value');
				 $(this).css('box-shadow','0 0 10px rgb(250,0,0)');
				 $(this).siblings(".classification").css('box-shadow','none');
				 $(this).parent().siblings().find(".classification").css('box-shadow','none');
				 if(flagvalue==""){
					 window.location.href="/demandcityshopping/shoppingtogo?classification="+classificationfindtype+"&childid="+"";
				 }else{
					 window.location.href="/demandcityshopping/shoppingtogo?classification="+classificationfindtype+"&childid="+flagvalue;
				 }
				
				});
		});
	
	
	//根据孩子id获得相应孩子
	$(".portlet-body.mypanel img").each(function() {
		 $(this).click(function () {
			 var id=$(this).parent().find('input').attr('value');
			window.location.href="/demandcityshopping/switchbychildid?childId="+id;
			});
	});
	
});
	