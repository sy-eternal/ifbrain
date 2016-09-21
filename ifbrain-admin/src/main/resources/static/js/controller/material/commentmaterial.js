$(function(){
	
	$(".materialtypes").each(function(){
    	$(this).find("#contentshow").html($(this).find("#show").val())
    	$(this).find("#contentshow img").addClass("img-responsive");
    	$(this).find("p").css({"text-align":"left"});
    
    })
    
    
    $(".huifu").each(function(){
    	$(this).click(function(){
    		$(this).parent().find(".textcomment").css("display","block");
    		$(this).parent().find(".submitbutton").css("display","block");
    	})
    	
    });
	//删除用户
	$(".deleteall").each(function(){
		$(this).click(function(){
			if (!confirm("确认要删除吗？")) {
				return;
			}
			var commentdelete = $(this).find(".commentdelete").val();
			$.ajax({
				type: "Post",
				url: "/material/deleteall?commentid="+commentdelete ,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		});
	})
	//删除作者
	$(".deletebymember").each(function(){
		$(this).click(function(){
			if (!confirm("确认要删除吗？")) {
				return;
			}
			var commentmemberdelete = $(this).find(".commentmemberdelete").val();
			$.ajax({
				type: "Post",
				url: "/material/commentmemberdelete?replyid="+commentmemberdelete ,
				success: function () {
					// 刷新表格
					window.location.reload();
				}
			});
		});
	})
	//回复 
	/*$(".submitbutton").each(function(){
		$(this).click(function(){
			commentid = $(this).find(".commentid").val();
			window.location.href="/material/replycomment?commentid="+commentid+"&membercomment="
		});
	})*/
	
});