	//点击商品种类
$(function(){
	$(".classification").each(function() {
		 $(this).click(function () {
			 var flagvalue=$("#flagaa").val();
			 var classification=$(this).text();
			 var classificationfindtype = $(this).find('.classificationfindtype').attr('value');
			 $(this).css('box-shadow','0 0 10px rgb(250,0,0)');
			 $(this).siblings(".classification").css('box-shadow','none');
			 $(this).parent().siblings().find(".classification").css('box-shadow','none');
			 window.location.href="/demandcityshopping/shoppingtogo?classification="+classificationfindtype+"&childid="+flagvalue;
			});
	});
})
	