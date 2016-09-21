
     var maxtime;
$(function(){
    //点击题目
    $(".clickname").each(function(){
    	$(this).parent().find(".selectcontent").css("display","none");
    	$(this).click(function(){
    		$(this).parent().find(".selectcontent").css("display","block");
    	})
    })
    //点击题目跳转到相应的题
      $(".selectcontent").each(function(){
    	$(this).html("<input type='hidden' value="+$(this).find('.temManagementQuestionordernumber').val()+" class='temManagementQuestionordernumber'/>"+"&nbsp;&nbsp;&nbsp;&nbsp;"+$(this).find(".questionNameContent").val());
    	
    	$(this).click(function(){
    		var flagvalue=$("#childid").val();
    		var answer = $('input[name="checkradio"]:checked').val();;
    		var questionsize =$("#questionsize").val();
    		var ordernumber =$(this).find(".temManagementQuestionordernumber").val();
    		window.location.href="/itemmanagement/clickbeginexamination?childId="+flagvalue+"&ordernumber="+ordernumber+"&answer="+answer+"&questionsize="+questionsize;
    	})
    	
    })
    //读取列表html
    $(".itemManagementQuestion").each(function(){
					$(this).find($(".examcontent")).html($(this).find($(".contentshow")).val());
					$(this).find($(".questionNameContent")).html($(this).find($(".NameContent")).val());
	 })
	 //第一题样式
		if($("#ordernumber").val()==1){
			
			$(".previousquestion").css("background-color","#a3a3a3");
		}
			//上一题	
	$(".previousquestion").click(function(){
		var flagvalue=$("#childid").val();
		if($("#ordernumber").val()>1){
			var answer = $('input[name="checkradio"]:checked').val();;
			var ordernumber=parseInt($("#ordernumber").val())-parseInt(1);
			var questionsize =$("#questionsize").val();
			window.location.href="/itemmanagement/beginprevexamination?childId="+flagvalue+"&ordernumber="+ordernumber+"&answer="+answer+"&questionsize="+questionsize;
			
		}else{
			$(".previousquestion").css("background-color","#a3a3a3");
			
		}
		
	})
	//下一题
	$(".nextquestion").click(function(){
		var flagvalue=$("#childid").val();
		if($("#questionsize").val()>=$("#ordernumber").val()){
			var ordernumber=parseInt($("#ordernumber").val())+parseInt(1);
			var answer = $('input[name="checkradio"]:checked').val();
			var questionsize =$("#questionsize").val();
			var ordernumbers=$("#ordernumber").val();
			window.location.href="/itemmanagement/beginnextexamination?childId="+flagvalue+"&ordernumber="+ordernumber+"&answer="+answer+"&questionsize="+questionsize;		
		}else{
			$(".nextquestion").css("background-color","#a3a3a3");
			$(".nextquestion").text("下一题");
			
		}
	})
	//最后一题变样式
	if($("#questionsize").val()<$("#ordernumber").val()){
		$(".nextquestion").css("background-color","#a3a3a3");
		$(".nextquestion").text("下一题");
	}
	
	
	//ABCD选中
	if($(".childanswers").val()=="A"){
		$(".checkradio1").attr("checked","true");
	}
	if($(".childanswers").val()=="B"){
		$(".checkradio2").attr("checked","true");
	}
	if($(".childanswers").val()=="C"){
		$(".checkradio3").attr("checked","true");
	}
	if($(".childanswers").val()=="D"){
		$(".checkradio4").attr("checked","true");
	}
	
	//点击提交
	$(".submiteexam").click(function(){
		var flagvalue=$("#childid").val();
		var perscore = $(".perscore").val();
		window.location.href="/itemmanagement/submiteexam?childId="+flagvalue+"&perscore="+perscore;;
	})

 $("input[name=checkradio]").click(function () {
        var ordernumber=$("#ordernumber").val();
       	var flagvalue=$("#childid").val();
        var answer =$(this).val();
        $.ajax({
			type : "get",
			async : false, 
			url : "/itemmanagement/checksaveanswer?childId="+flagvalue+"&answer="+answer+"&ordernumber="+ordernumber,
			success:function(dates){}
		});
        })
})   


  

