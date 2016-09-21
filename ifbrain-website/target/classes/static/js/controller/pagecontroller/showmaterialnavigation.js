
$(function(){
	$(".nav-list").eq(0).css("background-color","#0161A1")
	$(".nav-list").eq(1).css("background-color","#6C8DDA")
	$(".nav-list").eq(2).css("background-color","#4DBA79")
	$(".nav-list").eq(3).css("background-color","#88BC3F")
	$(".nav-list").eq(4).css("background-color","#E67D96")
	$(".nav-list").eq(5).css("background-color","#4AACC6")
	$(".listnav2").eq(0).addClass("marginnav");
	/*var types=$(".nav-list").eq(0).find(".navigationbarModuletype").val();
	var navigationbartrype=$("#navigationbartrype").val();
	if(navigationbartrype==1){
		 //财脑课程介绍
		if(types==0){
			$.ajax({
				type : "get",
				async : false, 
				url : "/course/ifbrainindexintroductionhome?navigationbartrype="+navigationbartrype+"&type="+types,
				success:function(dates){
					$("#item-content").html(dates);//要刷新的div
				}
			});
		}
	}*/
/*	$(".nav-list").each(function(){*/
		
		var navigationbartrype=$("#navigationbartrype").val();
		$(".nav-list").click(function(){
			var type=$(this).find(".navigationbarModuletype").val();
			if(navigationbartrype==1){
				 //财脑课程介绍
				if(type==0){
					$.ajax({
						type : "get",
						async : false, 
						url : "/course/ifbrainindexintroductionhome?navigationbartrype="+navigationbartrype+"&type="+type,
						success:function(dates){
							$("#item-content").html(dates);//要刷新的div
						}
					});
				}
				 //线上课程
				 if(type==1){
					 $.ajax({
							type : "get",
							async : false, 
							url :  "/moviematerial/offlinecourse?navigationbartrype="+navigationbartrype+"&type="+type,
							success:function(dates){
								$("#item-content").html(dates);//要刷新的div
							}
						});
						
				 }
				 //线下课程
				 if(type==2){
					 $.ajax({
							type : "get",
							async : false, 
							url :  "/course/ifbrainindexintroductionhome?navigationbartrype="+navigationbartrype+"&type="+type,
							success:function(dates){
								$("#item-content").html(dates);//要刷新的div
							}
						});
					
				 }
				 //家长课程
				 if(type==3){
					 $.ajax({
						
							type : "get",
							async : false, 
							url :  "/course/familycourse?navigationbartrype="+navigationbartrype+"&type="+type,
							success:function(dates){
								$("#item-content").html(dates);//要刷新的div
							}
						});
					
				 }
					//收入职业商业创业
				 if(type==4){
						 $.ajax({
								type : "get",
								async : false, 
								url :  "/ifbraintask/taskcourse?navigationbartrype="+navigationbartrype+"&type="+type,
								success:function(dates){
									$("#item-content").html(dates);//要刷新的div
								}
							});
				 }
			}
			if(navigationbartrype==2){
				//财脑家庭训练器
				 if(type==0){
					 if ($("#sessionuser").val() == "") {
				      		window.location.href = "/project/li";
				      	} else{
				      		var childid=$("#tokenfoot").val();
				      		if(childid==""){
				      			window.location.href = "/project/li";
				      		}else{
				      			window.location.href = "/project/li/"+$("#tokenfoot").val();
				      		}
				      	} 
				 }
				 if(type==1){
						
				 }
				//马斯洛
				 if(type==2){
					 if ($("#sessionuser").val() == "") {
					      
			      		   window.location.href = "/user/logins?returnUrl="+"/demandcityshopping/maslowdemand";
			        	} else{
			        		window.location.href = "/demandcityshopping/maslowdemand?childid="+$("#tokenfoot").val();
			        	} 
				 }
			}
			
		})
	
		
	/*})*/
		
		
			/*$(".materialtypeclick").click(function(){
				var id =$(this).find('.materialid').attr('value');
				window.location.href="/course/ifbrainindexintroductiondetail?id="+id
			})*/
		
		//点击级别
	$(".clickmaterailtype").each(function() {
		$(this).click(function(){
			  var courseid=$(this).find(".clickmaterailtypecourseid").attr('id');     
			window.location.href="/moviematerial/showmovie?courseid="+courseid;
		})
	})
	
	
});