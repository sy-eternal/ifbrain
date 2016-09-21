/* 代码整理：大头网 www.datouwang.com */
$(function(){

	//语音通知手风琴效果
	$(".voice_2 ul li").each(function(){
		var fold = $(this).find(".fold");
		var unfold = $(this).find(".unfold");
		if(fold.is(":hidden")){
			$(this).width(250);
		}else{
			$(this).width(80);
		}
	})

	$(".voice_2 ul li").click(function(){
		var li_index = $(this).index();
		$(this).animate({width:250},200);
		$(this).find(".unfold").show();
		$(this).find(".fold").hide();
		$(this).siblings().animate({width:50},200);
		$(this).siblings().find(".unfold").hide();
		$(this).siblings().find(".fold").show();
	})

})
