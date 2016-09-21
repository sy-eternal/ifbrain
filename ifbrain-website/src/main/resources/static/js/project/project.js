
	function show() {

		var childName = $("#select").val();
		$.cookie('childName', childName);
		window.location.reload();

	}
	function getBalance() {
		var childId = $(".childss").val();
		if($(".childss").val()!=null){
			$("#caption1").css("display","none");
			$("#caption").css("display","block");
		}else if($(".childss").val()==null){
			alert("您还没有宝贝，请在我的里面添加宝贝信息！")
			
			$("#caption1").css("display","block");
			$("#caption").css("display","none");	
		}
		  var flagvalue=$("#flags").val();
		  if(flagvalue==""){  
	 $(".portlet-body.mypanel img").eq(0).addClass('mypanelBorder');
		 } 
	 document.getElementById("ids").value=$(".childss").val();
		myselect();
		//addBorder();
	}
	
	function myselect() {
		var orb   = $("#orb");
		var track = $("#track");
		var rail = $("#platform");

		var trackWidth  = track.width();
		var cartWidth   = orb.width();

		$(".button").on("click", function() {
		  var position = $(this).position();
		  var width    = $(this).width();
		  var left     = position.left;
		  var platform = left + width-5; // TODO: Remove magic #
		  $(".button").removeClass("active");
		  $(this).addClass("active");
		  
		  rail.addClass("active");
		  
		  orb.addClass("hover");
		  
		  orb.animate({
		    "left" : platform - cartWidth
		  }, 2000, function() {
		    orb.removeClass("hover");
		    rail.removeClass("active");
		  });
		});
	}
	//完成一次
	function wancheng() {	
					var child = $("#ids").val();
					 document.getElementById("works").value=$(".wangchengc").parent().find('input').attr('id');
					 window.location.href="/project/add/" + $(".wangchengc").parent().find('input').attr('id') + "/" + child;   

	}
	//完成任务结束
	function end() {
		var msg = "您确定结束此任务吗？";
		$(".jieshuc").click(function () {
			if (confirm(msg) == true) { 
				var id = $("#works").val();
			 	window.location.href = "/project/endadd/" + $(this).parent().prev().find('input').attr('id'); 
			 	
		 	} else {
				return false;
			} 
		
	 });
	}
	//分配任务
	function fenpei(){
		
		 var child = $("#ids").val();
		window.location.href = "/myinformation/searchworktasklist/"  +  child
	}
	


//点击input出现下划线
function textboxline(){
	
	$("#textboxline").css("border-bottom","2px solid #0151a6 ");
}


//2秒钟后金币模态框自动关闭
function close(){
	
	$("#money").modal('hide');
};



 $(function(){
	 
	/*  //关闭金币模态框的音乐
	 $('#money').on('hide.bs.modal', function () {
	       var audioEle = document.getElementById("audio");
		    audioEle.pause(); 
	      }); */
	 
	  //计算百分比    
	   function percentNum(num, num2) {
	    	  var percent=Math.round(num / num2 * 10000) / 100.00 + "%";
			return percent; //小数点后两位百分比
		}

		//js计算百分比并赋值,点击加号
		function updatePrice(taskTimes, value, name) {
			var val1 = parseInt(taskTimes);
			var val2 = parseInt(value);
			var percentspanval=percentNum(val2,val1);
			 var obj=$("span[title='percentspan"+name+"']");
			 var p=parseInt(percentspanval.replace("%",""));
			obj.parent().attr("data-percent",p);
			var obj=obj.text(percentspanval);
			obj.parent().data('easyPieChart').update(percentspanval);
		}
		//js计算百分比并赋值,点击减号
		function updatePrice1(taskTimes, value, name) {
			var val1 = parseInt(taskTimes);
			var val2 = parseInt(value);
			var percentspanval=percentNum(val2,val1);
			 var obj=$("span[title='percentspan"+name+"']");
			 var p=parseInt(percentspanval.replace("%",""));
			obj.parent().attr("data-percent",p);
			var obj=obj.text(percentspanval);
			obj.parent().data('easyPieChart').update(percentspanval);
		}
        //遍历圆环
		$(".addordivide").each(function() {
			var name = $(this).attr('name');
			var taskTimes = $("#taskTimes" + name + "").val();
			var times = parseInt(taskTimes);
			$(this).TouchSpin({
				buttondown_class : 'btn  img-circle',
				buttonup_class : 'btn  img-circle',
				min : 1,
				max : times,
				stepinterval : 1,
				maxboostedstep : 10000000,
				postfix : ''
			}).on('touchspin.on.startupspin', function() {
				updatePrice(taskTimes, this.value, name);
			}).on('touchspin.on.startdownspin',function(){
				updatePrice1(taskTimes, this.value, name);
			});
			;
		})
		function onComplete($el,active){
			   objMachine.active(5);
	    	  objMachine.stop(true);
		}
		//点击完成任务
		//$(".unfinish a p").each(
		//		function() {
		//			$(this).click(
		$(".unfinish a p").click(
									function(i, n) {
										
										var id = $(this).siblings().attr('value');
										  //在金币弹出的模态框中，遍历代币数
									    var tokennumbers=$("#tokennumbers"+id+"").val();
									    var number=parseInt(tokennumbers);
									    $('#textMachine').prop('number', 1).animateNumber(
									    		   {
									    		     number: number
									    		   },
									    		   1500
									    		);
									    //老虎机
									    /*for(var i=0;i<number;i++){
										   var n=i+1;
										   
										   $("<div>"+n+"</div>").appendTo("#textMachine");
									   }
									   
									      var objMachine=$("#textMachine").slotMachine({
											active:1,
											delay:300,
											repeat:1
										});	 
									      objMachine.shuffle(1,onComplete(5));*/
									      
									     // objMachine.active(4);
										//弹出播放音乐模态框
										$('#money').modal({
											show : true,
											backdrop : true
										});
										setTimeout('close()', 2000);
										var audioEle = document
												.getElementById("audio");
										audioEle.play(); //播放
										
										
										/*  if (!confirm("确认要完成任务吗？")) {
												return;
											}else{ */
											
										var timesadd = $("input[name='" + id + "']").val();
										$(this).parents(".parent").remove();
										$("#finishid").val("0");
										$.ajax({
											async : false,
											type : "Get",
											url : "/child/finishtask?taskId="
													+ id + "&amp;timesadd="
													+ timesadd
													+ "&amp;childid="
													+ $("#flags").val(),
											success : function(data) {
												if (data == "1") {

												}
											}
										});
										/* } */
									})
				//});
		//点击删除任务
		$(".finish a p").each(function() {
			$(this).click(function(i, n) {

				if (!confirm("确认要删除吗？")) {
					return;
				} else {
					var id = $(this).siblings().attr('value');
					$(this).parents(".parent").remove();
					$("#finishid").val("0");
					$.ajax({
						async : false,
						type : "Get",
						url : "/child/deletefinishtaskById?taskId=" + id,
						success : function(data) {
							if (data == "1") {

							}
						}
					});
				}
			})
		});

		//遍历未完成百分比
		 var strs = new Array();
		var arrNumber;
		var integerrate = $("#integerrate").val();
		if (integerrate != "") {
			strs = integerrate.split(",");
			$(".transactions").each(function(i, n) {
				$(this).attr("data-percent", strs[i]);
			});
		} 
		//遍历已完成任务百分比
		var strs1 = new Array();
		var arrNumber1;
		var finishintegerrate = $("#finishintegerrate").val();
		if (finishintegerrate != "") {
			strs1 = finishintegerrate.split(",");
			$(".finishtask").each(function(i, n) {
				$(this).attr("data-percent", strs1[i]);
			});
		}

		//根据flagvalue来判断进入该页面后选中第几个图片
		var flagvalue = $("#flags").val();
		if (flagvalue != "") {
			$(".portlet-body.mypanel img").each(
					function() {
						var value = $(this).parent().find('input')
								.attr('value');
						if (flagvalue == value) {
							$(this).addClass('mypanelBorder').parent()
									.siblings().find('img').removeClass(
											'mypanelBorder');
						}
					});
		}

		$(".portlet-body.mypanel img")
				.each(
						function() {
							$(this)
									.click(
											function() {
												var id = $(this).parent().find(
														'input').attr('value');
												window.location.href = "/child/findTaskByChildId?childId="
														+ id;
												/* $(this).addClass('mypanelBorder').parent().siblings().find('img').removeClass('mypanelBorder');
												document.getElementById("ids").value=$(this).parent().find('input').attr('id');
												 $.ajax({
													type : "Get",
													url : "/child/findById?childId=" + $(this).parent().find('input').attr('value'),
													success : function(data) {
														childmoney = data.balance;
														$("#balance").val(" x" + childmoney);
													}
												}); 
												$.ajax({
													type : "Get",
													url : "/project/findByChildId?childId=" + $(this).parent().find('input').attr('value'),
													success : function(data) {
														var html = template('child',eval("("+data+")"));
														document.getElementById('voice_2').innerHTML = html;
													}
												}); */
											});
						});

		var childCookie = $.cookie('childName');

		if (childCookie == null) {
			$("#child").val($("#select").val());
		} else {
			$("#select").val(childCookie);
			$("#child").val(childCookie);
		}

		$(".menu_body").hide();
		$(".menu_foot").hide();

		//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked 
		/* $("#firstpane div.menu_head").click(function()
		{ */
		//alert("head!!!!!!!!");
		/* $(this).css({backgroundImage:"url(down.png)"}).next("div.menu_foot ").slideToggle(300).siblings("div.menu_foot").slideUp("slow"); */
		//$(this).next("div.menu_body ").slideToggle(300).siblings("div.menu_body").slideUp("slow");
		//$(this).next("div.menu_body ").next("div.menu_foot ").slideToggle(300).siblings("div.menu_foot").slideUp("slow");
		/* }); */

		//slides the element with class "menu_body" when paragraph with class "menu_head" is clicked   onload="getBalance()"
		/* $("#firstpane div.menu_foot").click(function()
		{
			 $(".menu_body").hide();
			 $(".menu_foot").hide();
		}); */

		$(".ui-btn.ui-btn-icon-right.ui-icon-carat-r").each(
				function() {
					$(this).on(
							"click",
							function() {
								$(this).parents(".menu_head").next(
										"div.menu_body ").slideToggle(300)
										.siblings("div.menu_body").slideUp(
												"slow");
								$(this).parents(".menu_head").next(
										"div.menu_body ")
										.next("div.menu_foot ")
										.slideToggle(300).siblings(
												"div.menu_foot")
										.slideUp("slow");
								//$(".ui-btn.ui-btn-icon-right.ui-icon-carat-r").animate({right: '0px'},0);
								/* $(this).animate({right: '80px'},0);
								$(this).css('border','none');
								$(this).css('box-shadow','none');  */
								var value = $("#finishid").val();
								if (value == "0") {
									$(this).animate({
										right : '120px'
									}, 0);
									$(this).css('box-shadow', 'none');
									$("#finishid").val("1");
								} else {
									$(this).animate({
										right : '-4px'
									}, 0);
									$(this).css('box-shadow', 'none');
									$("#finishid").val("0");
									$(this)
											.css("height", "190px", "border",
													"none", "background-color",
													"white");
								}

								//$(this).prev().parent().find('.behind').css("display","block")
								/* if($(this).prev().parent().find('.behind').attr('id')){
									var flag=0;
									if(flag==1){
										$(this).css('border','none');
										$(this).css('box-shadow','none');
										flag=0;
									}else{
										$(this).animate({right: '120px'},0);
										$(this).css('border','none');
										$(this).css('box-shadow','none');
										flag=1;
									}
								} 	 */

							});
				})

	});

	function hidehistory() {

		if ($("#history").css("display") == "none") {

			$("#history").show();
			$("#hidehistory").val("隐藏已完成任务");

		} else {

			$("#history").hide();
			$("#hidehistory").val("显示已完成任务");

		}

	}
