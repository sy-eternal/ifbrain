//孩子选择头像
$(function(){
	 //根据flagvalue来判断进入该页面后选中第几个图片
	 var flagvalue=$("#flagaa").val();
	 if(flagvalue!=""){
		 $(".portlet-body.mypanel img").each(function() {
			 var value=$(this).parent().find('input').attr('value');
			 if(flagvalue==value){
				 $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
			 }
		 });
	 }
	 //删除
	 $(".lable").click(function(){
		 var id=$(this).parent().find("input").val();
		 if (!confirm("确认要删除吗？")) {
				return;
			}
		 window.location.href="/wish/delete?id="+id;
	 });
	 
	 
	 
	//根据孩子id获得相应孩子
	$(".portlet-body.mypanel img").each(function() {
		 $(this).click(function () {
			 var id=$(this).parent().find('input').attr('value');
			window.location.href="/wish/wish?childid="+id;
			});
	});
	
	$(".touchspin_listshopping").each(function() {
		$(this).TouchSpin({
			buttondown_class: 'btn ',
	        buttonup_class: 'btn ',
	        min: 1,
	        max: 60,
	        stepinterval: 1,
	        maxboostedstep: 10000000,
	        postfix: ''
		}).on('touchspin.on.startupspin', function() {
			updatePrice($(this).parents().find('.parent').siblings().find('.commditymoney').val(), this.value);
		}).on('touchspin.on.startdownspin',function(){
			updatePrice1($(this).parents().find('.parent').siblings().find('.commditymoney').val(), this.value);
		});
	});

});
    //添加时
   function updatePrice(price,value){
	   var result=accMul(price,value);
	   var result1=$("#result").html()
	   if(result1!=""){
	   var money=result1.substring(result1.indexOf("￥")+1,result1.length);
	   var result2=accAdd(result,money)
	   $("#result").html(result2);
	   }else{
		 $("#result").html(result);  
	   }
    }
   //减少时
   function updatePrice1(price,value){
	   var result=accMul(price,value);
	   var result1=$("#result").html();
	   if(result1!=""){
	   var money=result1.substring(result1.indexOf("￥")+1,result1.length);
      $("#result").html(accSub(money,result));
	   }else{
		 $("#result").html(result);  
	   }
   };
   //相乘
   function accMul(arg1,arg2)
   {
   var m=0,s1=arg1.toString(),s2=arg2.toString();
   try{m+=s1.split(".")[1].length}catch(e){}
   try{m+=s2.split(".")[1].length}catch(e){}
   return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
   }
  //相加
   function accAdd(arg1,arg2){
	   var r1,r2,m;
	   try{r1=arg1.toString().split(".")[1].length}catch(e){r1=0}
	   try{r2=arg2.toString().split(".")[1].length}catch(e){r2=0}
	   m=Math.pow(10,Math.max(r1,r2))
	   return (arg1*m+arg2*m)/m
	   }
   //相减
    function accSub(arg1, arg2) {
	       var r1, r2, m, n;
	        try {
	           r1 = arg1.toString().split(".")[1].length;
	      }
	       catch (e) {
	           r1 = 0;
	       }
	       try {
	           r2 = arg2.toString().split(".")[1].length;
	       }
	       catch (e) {
	           r2 = 0;
	       }
	       m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
	       n = (r1 >= r2) ? r1 : r2;
	      return ((arg1 * m - arg2 * m) / m).toFixed(n);
	   }
//添加心愿清单
function addxinyuan(){
   if ($("#sessionuser").val() == "") {
	window.location.href = "/user/logins?returnUrl="+"/wish/initwish";
   } else{
	   var childid=$("#childid").val();
	   if(childid==""){
		  alert("请创建孩子!"); 
		  return false;
	   }else{
		   var commodityName=$("#commodityName").val();
		   var price=$("#price").val();
		   if(commodityName==""&&price==""){
			   alert("请填写商品名称和价格!");
		   }else if(isNaN(price)){
			  $("#messages").css('display','block');
		   }else{
			   $("#mainForm1").submit();
		   }
		   
	   }
  } 
}
//心愿清单
function wish(){
			window.location.href = "/wish/initwish";
}
function shangcheng(){
	        window.location.href = "/wish/initshangcheng";
}
function mywishlist(){
		var flagvalue=$("#tokenfoot").val();
		window.location.href = "/wish/mywhishlist?childid="+flagvalue;	 
}

function addwishlist(){
	var flagaa=$("#flagaa").val();
	var commdityid=$("#commdityids").val();
	 $.ajax({
			type : "Get",
			url : "/wish/addwishlist?childId="+flagaa+"&commdityid="+commdityid,
			success : function(data) {	
				alert("添加心愿清单成功!");
				window.location.reload();
			}
		});
}