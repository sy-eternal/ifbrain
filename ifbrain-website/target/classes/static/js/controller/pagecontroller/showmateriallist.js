
$(function(){
	$(".nav-list").eq(0).css("background-color","#0161A1")
	$(".nav-list").eq(1).css("background-color","#6C8DDA")
	$(".nav-list").eq(2).css("background-color","#4DBA79")
	$(".nav-list").eq(3).css("background-color","#88BC3F")
	$(".nav-list").eq(4).css("background-color","#E67D96")
	$(".nav-list").eq(5).css("background-color","#4AACC6")
/*	var typenames=$(".nav-list").eq(0).find(".materialName").val()*/
	/*$.ajax({
			type : "get",
			async : false, 
			url : "/activity/materiallist?typename="+typenames,
			success:function(dates){
				$("#item-content").html(dates);//要刷新的div
			}
		});*/
	/*$(".nav-list").each(function(){*/
		$(".nav-list").click(function(){
			var typename=$(this).find(".materialName").val();
			$.ajax({
				type : "get",
				async : false, 
				url : "/activity/materiallist?typename="+typename,
				success:function(dates){
					$("#item-content").html(dates);//要刷新的div
				},
				error: function() {
	                alert("失败，请稍后再试！");
	            }
			});
		})
	
/*		
	})*/
		
		/* $(".materialdetaillist").each(function() {*/
			 $(".materialdetaillist").click(function(){
    				var id =$(this).find('.materialid').attr('value');
    				window.location.href="/activity/materialdetail?id="+id
    			})
    	/*	})*/
	
});