var maxtime;
var sumtime
var timer
var clicktrue;
$(function(){
	//初始化child头像
	 sumtime =$("#surtimes").val();
	 clicktrue = $("#clicktrue").val();
	var flagvalue=$("#flagaa").val();
	if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
			 }
			 $(this).click(function () {
				 var id=$(this).parent().find('input').attr('value');
				 window.location.href="/itemmanagement/toexamination?childId="+id;
				});
		 });
	}
	$(".clickbegin").click(function(){
		if(flagvalue==""){
			alert("请先选择宝贝再考试")
		}else if($("#message").val()=="1"){
			alert("您已经考过了！");
			
		}
		else{	
				if($("#examorder").val() == "1"){
					//付过款
					window.location.href="/itemmanagement/beginexamination?childId="+flagvalue+"&ordernumber="+1;
				}else{
					if (!confirm("一共6单元，一次性付清，还未付款，是否要付款考试？")) {
						return;
					}else{
					var examid =$("#examid").val();
					/*var examTotalAmount  = $("#examTotalAmount").val();*/
					window.location.href="/itemmanagement/doSubmit?totalAmount="+0.01+"&childid="+flagvalue;
					}
				}
				//考试进入页面
			
			
		}
	})
	
	     if(window.name==''){ 
			maxtime = sumtime*60;
			}else{
				if(clicktrue=="1"){
					maxtime = sumtime*60;
				}else{
					maxtime = window.name;
				}
			}
	 timer = setInterval("CountDown()",1000);
	 
	 
		
	
});

function CountDown(){
	
	if(maxtime>=0){
		if(clicktrue=="1"){
			maxtime = sumtime*60;
		}
	minutes = Math.floor(maxtime/60);
	seconds = Math.floor(maxtime%60);
	msg = "距离结束还有"+minutes+"分"+seconds+"秒";
	document.all["timer"].innerHTML = msg;
	if(maxtime == 5*60) alert('注意，还有5分钟!');
	--maxtime;
	window.name = maxtime; 
	
	}
	else{
		if(clicktrue!="1"){
			clearInterval(timer);
			alert("时间到，结束!");
			var flagvalue=$("#childid").val();
			var perscore = $(".perscore").val();
			window.location.href="/itemmanagement/submiteexam?childId="+flagvalue+"&perscore="+perscore;
		}
	
	}
}

