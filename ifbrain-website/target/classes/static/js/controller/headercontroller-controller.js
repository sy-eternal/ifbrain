	$(document).ready(function () {
		var userType=null;
		$.ajax({
			url:"/header/loginInit",
			type: "GET",
			 dataType: "json",
			success: function(){
//				alert("success");
//				alert(userType);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) { 
//				  alert("XMLHttpRequest.status"+XMLHttpRequest.status);
//                  alert("XMLHttpRequest.readyState"+XMLHttpRequest.readyState);
//                  alert("textStatus"+textStatus);
			} 
		});
	});	
	
	
	
	
  /*if(userType==""||userType==null){
		$('#daoyou').css('display','none');
    	$('#youke').css('display','none');
	}else if(userType==1){
		$('#youke').css('display','block');
    	$('#daoyou').css('display','none');
	}else if(userType==2){
		$('#youke').css('display','none');
    	$('#daoyou').css('display','block');
	}*/
