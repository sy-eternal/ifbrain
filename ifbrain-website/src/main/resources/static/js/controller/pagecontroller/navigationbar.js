$(function(){
	if($("#navigationbartrype").val()==1){
		
		$(".tracking3").addClass("trackings");
		
		
	}
	if($("#navigationbartrype").val()==2){

		$(".tracking5").addClass("trackings");
		
	}
	$(".navigationbarModule").each(function(){
		
		
		$(this).find(".content").html($(this).find('.navigationbarModulecontent').attr('value'));
		
		/*$(this).click(function(){
			var type =$(this).find('.navigationbarModuletype').attr('value');
			var navigationbartrype =$("#navigationbartrype").val();
			if(navigationbartrype==1){
				 //财脑课程介绍
				 if(type==0){
						window.location.href="/course/ifbrainindexintroductionhome?navigationbartrype="+navigationbartrype+"&type="+type;
				 }
				 //线上课程
				 if(type==1){
					
							window.location.href = "/moviematerial/offlinecourse";
					
				 }
				 //线下课程
				 if(type==2){
					
						window.location.href="/course/ifbrainindexintroductionhome?navigationbartrype="+navigationbartrype+"&type="+type;
					 }
				 //家长课程
				 if(type==3){
						window.location.href="/course/familycourse";
				 }
				//收入职业商业创业
				 if(type==4){
					
					 if ($("#sessionuser").val() == "") {
			        		window.location.href = "/ifbraintask/taskcourse";
			        	} else{
			        	window.location.href="/ifbraintask/taskcourse";
			        	}
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
				 
				//信用债务和贷款
				 if(type==4){
					 
				 }
				//风险管理和贷款
				 if(type==5){
					
				 }
				
				 
			 }
			
		})*/
	})

	
});