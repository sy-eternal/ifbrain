




$(".namecontent").each(function(){
	$(this).html($(this).siblings(".questionNameContent").val());
	$(this).siblings(".questioncontent").html($(this).siblings(".questionOptionContent").val());
	
});


function getPaystatus(){
	var answer="";
	//支付状态
	var size=$("#size").val();
	alert(size)
	for(i=1;i<=size;i++){
		answer += $("input[name='checkradio"+i+"']:checked").val()+",";
	}
	  $("#checkanswer").val(answer)
$('input[name="status"]:checked').each(function(){ 
       $("#paystatus").val($(this).val()); 
   }); 

}








