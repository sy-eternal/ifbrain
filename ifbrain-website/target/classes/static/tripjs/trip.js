var trip = function () {
	//未登录之后进行判断，根据导游接送是否有值来判断跳转页面，如果没有值，就在第一页，如果有值，跳转到锚点为tab5的位置
	 var ts = $("#tourS").val();
	 if(ts=="" || ts==null){
			window.location.hash="#tab1";
	 }else{
		 //用于跳转到锚点为tab5的位置，把之前用户填写的信息传过去
		 	
		 //已确定下来的行程的信息（用户之前填写的）
		 	var addr = new Array();
			var tim = new Array();
			var isServi = new Array();
			var beiz = new Array();
			
			var address=$("#address").val();
			var time=$("#time").val();
			var isService=$("#isService").val();
			var beizhu=$("#beizhu").val();
			
			addr=address.split(",");
			tim=time.split(",");
			isServi=isService.split(",");
			beiz=beizhu.split(",");
			
			var s="";
		    	for(var i=0;i<addr.length-1;i++)
		    	{
				s+="<tr>";
				s+="<th>";
				s+=addr[i];
				s+="<div class='form-control-focus'></div>";
				s+="</th>";
				s+="<th>";
				s+="<div class='input-group' id='defaultrange3'>";
				s+=tim[i];
				s+="</div>";
				s+="</th>";
				s+="<th>";
				if(isServi[i]==1)
				{
					s+="需要";
				}
				else
				{
					s+="不需要";
				}
				s+="</th>";
				s+="<th>";
				s+=beiz[i];
				s+="</th>";
				s+="</tr>";
			}
			$("#sureS").html(s);

			//提交计划
			if($("#tripPlan").val()==0){
				$("#shijian").html($("#startDate").val());
			}
			else
			{
				$("#shijian").html($("#NoDate").val());
				var h = $("#touchspin_1").val();
				$("#confirmplay").html($("#touchspin_1").val());
			}				
			$("#purpose").html($("input[name='tripAim']").val());
			$("#people").html($("input[name='entourage']").val());
			$("#theme").html($("input[name='tripObject']").val());
			$("#country").html($("#select2_4").val());
			$("#num").html($("input[name='peopleNum']").val());
			$("#ages").html($("input[id='more-iten-btnsss']").val());
			var age=$("input[name='memberage']");
			var age=$("#age").val();
			$("#ages").html(age);
			if($("input[name='plan']").val()==0)
			{
				$("#serivce").html("我需要规划全部行程");
			}
			else if($("input[name='plan']").val()==1)
			{
				$("#serivce").html("我已经确定下来部分行程安排 ");
				$("#confirmedPlan").html($("input[name='address']").val());
				$("#confirmedCity").html($("#bCity").val());
			}
			var hh = $("#select2_7").val();
			$("#confirmstartCity").html( $("#select2_7").val());
			$("#backstartCity").html( $("#backCity").val());
			if($("input[name='cityS']").val()==0)
			{
				$("#cityServiceReq").html("我不知道要去哪个城市 ");
			}
			else if($("input[name='cityS']").val()==1)
			{
				$("#cityServiceReq").html("我有一些特别感兴趣的地方  ");
				$("#interestedCity").html($("input[name='cityI']").val());
			}
			if(ts==1){
				$("#innerTrans").html("全程不开车");
			}else if(ts==2){
				$("#innerTrans").html("全程自驾");
			}else if(ts==3){
				$("#innerTrans").html("部分自驾");
			}
			$("#drivingReq").html($("input[name='selfDriver']").val());
			var vehicl=$("select[name='vehicle']").val();
			if(vehicl!=null&&vehicl.length>0){
				var svehicl=vehicl[0];
				for(var i=1;i<vehicl.length;i++)
				{
					svehicl+=","+vehicl[i];
				}
				$("#intraTrans").html(svehicl);
			}
			
			if($("select[name='hourseS']").val()==1)
			{
				$("#hotelPref").html("经济型");
			}
			else if($("select[name='hourseS']").val()==2)
			{
				$("#hotelPref").html("普通型");
			}
			else if($("select[name='hourseS']").val()==3)
			{
				$("#hotelPref").html("高端型");
			}

			if($("select[name='flightS']").val()==1)
			{
				$("#flightPref").html("经济舱");
			}
			else if($("select[name='flightS']").val()==2)
			{
				$("#flightPref").html("公务舱");
			}
			else if($("select[name='flightS']").val()==3)
			{
				$("#flightPref").html("头等舱");
			}

			if($("select[name='rentCar']").val()==1)
			{
				$("#carPref").html("经济性轿车");
			}
			else if($("select[name='rentCar']").val()==2)
			{
				$("#carPref").html("SUV");
			}
			else if($("select[name='rentCar']").val()==3)
			{
				$("#carPref").html("豪华轿车");
			}
			
			//从form-wizard.js中拷贝相关‘去结算-继续-返回的按钮’显示和最上边1-5步的显示效果
			 var form = $('#submit_form');
		     var error = $('.alert-danger', form);
		     var success = $('.alert-success', form);
			
		     //相关按钮的显示控制
			  var displayConfirm = function() {
	                $('#tab5 .form-control-static', form).each(function(){
	                    var input = $('[name="'+$(this).attr("data-display")+'"]', form);
	                    if (input.is(":radio")) {
	                        input = $('[name="'+$(this).attr("data-display")+'"]:checked', form);
	                    }
	                    if (input.is(":text") || input.is("textarea")) {
	                        $(this).html(input.val());
	                    } else if (input.is("select")) {
	                        $(this).html(input.find('option:selected').text());
	                    } else if (input.is(":radio") && input.is(":checked")) {
	                        $(this).html(input.attr("data-title"));
	                    } else if ($(this).attr("data-display") == 'payment[]') {
	                        var payment = [];
	                        $('[name="payment[]"]:checked', form).each(function(){ 
	                            payment.push($(this).attr('data-title'));
	                        });
	                        $(this).html(payment.join("<br>"));
	                    }
	                });
	            }
			  	
			  //相关导航条1-5步的显示控制
	            var handleTitle = function(tab, navigation, index) {
	                var total = navigation.find('li').length;
//	                alert(total+"total");
	                var current = index + 1;
	                // set wizard title
	                $('.step-title', $('#form_wizard_1')).text('Step ' + (index + 1) + ' of ' + total);
	                // set done steps
	                jQuery('li', $('#form_wizard_1')).removeClass("done");
	                var li_list = navigation.find('li');
	                for (var i = 0; i < index; i++) {
	                    jQuery(li_list[i]).addClass("done");
	                }

	                //判断按钮的隐藏与显示
	                if (current == 1) {
	                    $('#form_wizard_1').find('.button-previous').hide();
	                } else {
	                    $('#form_wizard_1').find('.button-previous').show();
	                }

	                if (current == total) {
	                    $('#form_wizard_1').find('.button-next').hide();
	                    $('#form_wizard_1').find('.button-submit').show();
	                    displayConfirm();
	                } else {
	                    $('#form_wizard_1').find('.button-next').show();
	                    $('#form_wizard_1').find('.button-submit').hide();
	                }
	                Metronic.scrollTo($('.page-title'));
	            }
	         
			 $('#form_wizard_1').bootstrapWizard({
	                'nextSelector': '.button-next',
	                'previousSelector': '.button-previous',
	                onTabClick: function (tab, navigation, index, clickedIndex) {
	                    success.hide();
	                    error.hide();
	                    handleTitle(tab, navigation, clickedIndex);
	                    
	                },
	                onNext: function (tab, navigation, index) {
	                    success.hide();
	                    error.hide();
	                    handleTitle(tab, navigation, index);
	                },
	                onPrevious: function (tab, navigation, index) {
	                    success.hide();
	                    error.hide();

	                    handleTitle(tab, navigation, index);
	                },
	                onTabShow: function (tab, navigation, index) {
	                    var total = navigation.find('li').length;
	                    var current = index + 1;
	                    var $percent = (current / total) * 100;
	                    $('#form_wizard_1').find('.progress-bar').css({
	                        width: $percent + '%'
	                    });
	                }
	            });
			 //得到数据之后，跳转到锚点tab5的位置
				window.location.hash="#tab5";
			
	 }
	 
	return {
		//main function to initiate the module
		init: function () {
			
			//时间规划
			$(function(){
				//获得页面上面的ID=tripPlan的值
				var tripPlan = $("#tripPlan").val();
				//判断选中的是哪个
				if (tripPlan == 0) {

					$("input[name='time-arrangement']").eq(0).attr("checked","checked");
					$(".time-sel0").show();
					$("#tripPlan").val(0);
				}else{
					$("input[name='time-arrangement']").eq(1).attr("checked","checked");
					$(".time-sel1").show();
					$("#tripPlan").val(1);
				}
 
			});
			
			
			//主题规划
			$(function(){
				var plan = $("#plan").val();
				if (plan == 0) {

					$("input[name='service-req']").eq(0).attr("checked","checked");
					$("#plan").val(0);
				}else{
					$("input[name='service-req']").eq(1).attr("checked","checked");
					$(".confirmed-iten").show();
					$("#plan").val(1);
				}
 
			});
			
			
			//地点规划
			$(function(){
				var cityS = $("#cityS").val();
				if (cityS == 0) {

					$("input[name='city-service-req']").eq(0).attr("checked","checked");
					$("#cityS").val(0);
				}else{
					$("input[name='city-service-req']").eq(1).attr("checked","checked");
					$(".interested-places").show();
					$("#cityS").val(1);
					
				}
 
			});
			
			//游玩方式
			$(function(){
				var tourS = $("#tourS").val();
				console.log($("input[name='citytype']"))
				if (tourS == 1) {
					$("input[name='citytype']").eq(0).attr("checked","checked");
				}else if(tourS == 2){
					$("input[name='citytype']").eq(1).attr("checked","checked");
				}else if(tourS == 3 ){
					$("input[name='citytype']").eq(2).attr("checked","checked");
					$(".driving-notes").show();
				}
 
			});
			
			//年龄
			$(function(){
				var n = document.forms[0].peopleNum.value;
				  if(isNaN(n)) return false;
				  n = parseInt(n);
				  var obj = document.getElementById("pad");
				
				var age = $("#age").val();
				var ages= new Array();
				ages=age.split(","); 
				
				
				
				  var s = "";
				  for(var i=1;i<=n;i++){
			   s +="<br />第"+i+"个人的年龄为" +"&nbsp;&nbsp;&nbsp;" +"<input type='text'   size='9' id='more-iten-btnsss 'name='memberage' required='required' value='"+ages[i-1]+"'/>&nbsp;岁<br/>";
				  }
				  obj.innerHTML = s;
			});
			
			
			//主题规划中的已经确定下来的行程初始化
			$(function(){
				//获得页面的隐藏的"xxx,xxx,xxx,xxx,xxx,"的值，依次是地址、时间、导游服务和景点安排需要不需要,备注信息
				var address = $("#address").val();
				var time = $("#time").val();
				var isService = $("#isService").val();
				var beizhu = $("#beizhu").val();
				
				//切割后的地址
				var addresss=new Array();
				addresss=address.split(",");
				//切割后的时间
				var times=new Array();
				times=time.split(",");
				//切割后的服务是否需要
				var isServices=new Array();
				isServices=isService.split(",");
				//切割后的备注
				var biezhus=new Array();
				biezhus=beizhu.split(",");
				
				
				//新建一个集合，用于存储时间的值
				var index=new Array();
				
				
				//用于动态生成一个值（itenCount为初始化的初值）
				  var itenCount = 10000;
				  for(var i=0;i<addresss.length-1;i++){
						itenCount ++;
						
						//将存储时间的一个ID值(用于找到时间的ID值)
						index[i]=itenCount+3;
						//地址拼写
						var addresscity="";
						//获取页面的城市名称
						var addresslist=document.getElementsByName("citylist");
						//循环遍历城市，最终将addresscity放入下面的循环中
						for(var j=0;j<addresslist.length;j++){
							if(addresss[i]==addresslist[j].value){
								addresscity += '<option value="'+addresslist[j].value+'" selected="selected">'+ addresslist[j].value +'</option>'; 
							}else{
								addresscity += '<option value="'+addresslist[j].value+'">'+ addresslist[j].value +'</option>"'; 
							}
						}
						if(isServices[i]==1){
						
					 	$('#iten-table').append('<tr><td><select class="form-control" id="select2_8" name="addr">'+ addresscity  +'</select><div class="form-control-focus"></div></td><td><div class="input-group" id="defaultrange' + index[i] +'"><input type="text" class="form-control" name="tim" value="'+times[i]+'" onclick="changetime('+index[i]+');"/><span class="input-group-btn"><button class="btn default date-range-toggle" type="button" onclick="changetime('+index[i]+');"><i class="fa fa-calendar"></i></button></span></div></td><td><input type="checkbox" class="make-switch" checked data-on-color="success" name="isServi" data-off-color="warning" data-on-text="需要" data-off-text="不需要" value="'+isServices[i]+'"></td><td><input type="text" class="form-control" name="beiz" value="'+biezhus[i]+'" style="width:150px;" placeholder="请尽可能详细填写"/></td><td><a href="javascript:;" class="remove-field"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td></tr>');
						}else{
						 	$('#iten-table').append('<tr><td><select class="form-control" id="select2_8" name="addr">'+ addresscity  +'</select><div class="form-control-focus"></div></td><td><div class="input-group" id="defaultrange' + index[i] +'"><input type="text" class="form-control" name="tim" value="'+times[i]+'" onclick="changetime('+index[i]+');"/><span class="input-group-btn"><button class="btn default date-range-toggle" type="button" onclick="changetime('+index[i]+');"><i class="fa fa-calendar"></i></button></span></div></td><td><input type="checkbox" class="make-switch" data-on-color="success" name="isServi" data-off-color="warning" data-on-text="需要" data-off-text="不需要" value="'+isServices[i]+'"></td><td><input type="text" class="form-control" name="beiz" value="'+biezhus[i]+'" style="width:150px;" placeholder="请尽可能详细填写"/></td><td><a href="javascript:;" class="remove-field"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td></tr>');
							
						}
						
						//删除一条已确定下来的行程
						$('#iten-table tr').on('click', '.remove-field', function () {
					        $(this).closest('tr').remove()
					        itenCount--;
						});

					 	$('.make-switch').bootstrapSwitch();
				        $('#defaultrange' + index[i]).daterangepicker({
				            opens: (Metronic.isRTL() ? 'left' : 'right'),
				            format: 'YYYY-MM-DD',
				            separator: ' to ',
				            startDate: moment().add('days', 30),
				            endDate: moment().add('days', 30),
				            minDate: moment().add('days', 5),
				            maxDate: moment().add('years', 2),
				        },
				        	//用于把时间的值放入到#defaultrangeXXX的位置
				            function (start, end) {
					 
				                $('#defaultrange' + index[i] + ' input').val(start.format('YYYY年MMMM月D日') + ' 至 ' + end.format('YYYY年MMMM月D日'));
				            }
				        );  
				  };
			});
			
	
			//DEFAULT 
			
			 
			 
			 
			 
			 
			 
			 
			
			
			

			//出行安排
			$(function(){
				$("input[name='time-arrangement']").click(function(){
					var id=$(this).attr('id');
					$('#tripPlan').val($("#"+id).val());
				});
			});

			//规划方式
			$(function(){
				$("input[name='service-req']").click(function(){
					var id=$(this).attr('id');
					$('#plan').val($("#"+id).val());
				});
			});
			//已确定下来行程
			$("#service-continue").click(function() {
				var addr = $("select[name='addr']");
				var tim = $("input[name='tim']");
				var isServi = $("input[name='isServi']");
				var beiz = $("input[name='beiz']");
				
				
				
				var address="";
				var time="";
				var isService="";
				var beizhu="";
				var s="";
			 
				
				var val=$("input[name='service-req']:checked").val();
//				alert("val"+val);
				if(val==0){
					s="";
//					alert("input[name='plan']"+$("input[name='plan']").val());
				}
				else{
//					alert("gg");
			    	for(var i=0;i<addr.length;i++)
				{
					s+="<tr>";
					s+="<th>";
					s+=addr[i].value;
					s+="<div class='form-control-focus'></div>";
					s+="</th>";
					s+="<th>";
					s+="<div class='input-group' id='defaultrange3'>";
					s+=tim[i].value;
					s+="</div>";
					s+="</th>";
					s+="<th>";
					address+=addr[i].value+",";
					time+=tim[i].value+",";
					if(isServi[i].checked)
					{
						isService+=1+",";
						s+="需要";
					}
					else
					{
						isService+=0+",";
						s+="不需要";
					}
					s+="</th>";
					s+="<th>";
					s+=beiz[i].value;
					beizhu+=beiz[i].value+",";
					s+="</th>";
					s+="</tr>";
				}
			    	
				}
				$("#sureS").html(s);
				
				/*if($("input[name='plan']").val()==1){
					$("#sureS").html(s);
				}*/
				$("#address").val(address);
				$("#time").val(time);
				$("#isService").val(isService);
				$("#beizhu").val(beizhu)
				$("input[name='bCity']").val(address);
				$("#bqCity").val(address);



				//提交计划
				if($("#tripPlan").val()==0){
					$("#shijian").html($("#startDate").val());
				}
				else
				{
					$("#shijian").html($("#NoDate").val());
					var h = $("#touchspin_1").val();
					$("#confirmplay").html($("#touchspin_1").val());
				}				
				$("#purpose").html($("input[name='tripAim']").val());
				$("#people").html($("input[name='entourage']").val());
				$("#theme").html($("input[name='tripObject']").val());
				$("#country").html($("#select2_4").val());
				$("#num").html($("input[name='peopleNum']").val());
				$("#ages").html($("input[id='more-iten-btnsss']").val());
				var age=$("input[name='memberage']");
				var ages="";
					for(var i=0;i<age.length;i++)
					{
						  ages+=age[i].value+"岁,";
					}
					$("#ages").html(ages);
				if($("input[name='plan']").val()==0)
				{
					$("#serivce").html("我需要规划全部行程");
				}
				else if($("input[name='plan']").val()==1)
				{
					$("#serivce").html("我已经确定下来部分行程安排 ");
					$("#confirmedPlan").html($("input[name='address']").val());
					$("#confirmedCity").html($("#bCity").val());
				}
				var hh = $("#select2_7").val();
				$("#confirmstartCity").html( $("#select2_7").val());
				$("#backstartCity").html( $("#backCity").val());
				if($("input[name='cityS']").val()==0)
				{
					$("#cityServiceReq").html("我不知道要去哪个城市 ");
				}
				else if($("input[name='cityS']").val()==1)
				{
					$("#cityServiceReq").html("我有一些特别感兴趣的地方  ");
					$("#interestedCity").html($("input[name='cityI']").val());
				}
//				---------------------------------------------------------
				if($("input[name='citytype']:checked")){
					$("#innerTrans").html($("input[name='citytype']:checked").val());
				}

				
				$("#drivingReq").html($("input[name='selfDriver']").val());
				var vehicl=$("select[name='vehicle']").val();
				if(vehicl!=null&&vehicl.length>0){
					var svehicl=vehicl[0];
					for(var i=1;i<vehicl.length;i++)
					{
						svehicl+=","+vehicl[i];
					}
					$("#intraTrans").html(svehicl);
				}
				if($("select[name='hourseS']").val()==1)
				{
					$("#hotelPref").html("经济型");
				}
				else if($("select[name='hourseS']").val()==2)
				{
					$("#hotelPref").html("普通型");
				}
				else if($("select[name='hourseS']").val()==3)
				{
					$("#hotelPref").html("高端型");
				}

				if($("select[name='flightS']").val()==1)
				{
					$("#flightPref").html("经济舱");
				}
				else if($("select[name='flightS']").val()==2)
				{
					$("#flightPref").html("公务舱");
				}
				else if($("select[name='flightS']").val()==3)
				{
					$("#flightPref").html("头等舱");
				}

				if($("select[name='rentCar']").val()==1)
				{
					$("#carPref").html("经济性轿车");
				}
				else if($("select[name='rentCar']").val()==2)
				{
					$("#carPref").html("SUV");
				}
				else if($("select[name='rentCar']").val()==3)
				{
					$("#carPref").html("豪华轿车");
				}


			});

			//城市选择
			$(function(){
				$("input[name='city-service-req']").click(function(){
					var id=$(this).attr('id');
					$('#cityS').val($("#"+id).val());
				});
			});
			

			//城市交通(是之前的城市内交通)
			$(function(){
				$("#guide-drive-chk").click(function(){
//					alert($("#guide-drive-chk").attr("checked")=="checked");
					if($("#guide-drive-chk").attr("checked")=="checked")
					{
						$(".driving-notes").hide();
						$("#form_control_1").val("");
						$("#tourS").val(1);
					}
//					guide();
				});

				$("#self-drive-chk").click(function(){
//					alert($("#self-drive-chk").attr("checked")=="checked");
					if($("#self-drive-chk").attr("checked")=="checked")
					{
//						alert(2);
						$(".driving-notes").hide();
						$("#form_control_1").val("");
						$("#tourS").val(2);
					
					}

					//guide();
				});
				$("#public-trans-chk").click(function(){
//					alert($("#public-trans-chk").attr("checked")=="checked");
					if($("#public-trans-chk").attr("checked")=="checked")
					{
						$(".driving-notes").show();
						$("#tourS").val(3);
					}
//					guide();
				});

			});






		}
	};

}();


function Verification( ){
	if($("#tripPlan").val()==0) 
	{
		if($("#startDate").val()=="")
		{
			alert("出行日期不能为空！");
			return false;
		}
	}
	else if($("#tripPlan").val()==1)
	{
		if($("#NoDate").val()=="")
		{
			alert("不确定日期不能为空！");
			return false;
		}
		if($("input[name='playDay']").val()=="")
		{
			alert("游玩天数不能为空！");
			return false;
		}
	}
	else
	{
		alert("请选择出行计划");
		return false;
	}

	if($("input[name='tripAim']").val()=="")
	{
		alert("出行目的不能为空！");
		return false;
	}
	if($("input[name='entourage']").val()=="")
	{
		alert("随行人员不能为空！");
		return false;
	}
	if($("input[name='tripObject']").val()=="")
	{
		alert("旅行主题不能为空！");
		return false;
	}
	if($("#select2_4").val()=="")
	{
		alert("目的地国家不能为空！");
		return false;
	}
	if($("input[name='peopleNum']").val()=="")
	{
		alert("出行人数不能为空！");
		return false;
	}
	if($("input[name='chuxing']").val()=="")
	{
		alert("出行人数年龄不能为空！");
		return false;
	}
	if($("input[name='plan']").val()=="")
	{
		alert("规划方式不能为空！");
		return false;
	}
	if($("input[name='memberage']").val()=="")
	{
		alert("年龄不为空！");
		return false;
	}
	if($("#more-iten-btnsss").val()=="")
	{
		alert("年龄不为空！");
		return false;
	}
	if($("#more-iten-btnss").onclick==false){
		alert("请添加出行人数年龄！");
		return false;
	}
	else if($("input[name='plan']").val()==1)
	{
		if($("input[name='address']").val()==",")
		{
			alert("行程地点不能为空");
			return false;
		}
		else if($("input[name='time']").val()==",")
		{
			alert("行程时间不能为空");
			return false;
		}
		else if($("input[name='isService']").val()==",")
		{
			alert("是否需要导游服务选项不能为空");
			return false;
		}
	}
	if($("select[name='startCity']").val()==null)
	{
		alert("出发城市不能为空！");
		return false;
	}
	if($("input[name='cityS']").val()=="")
	{
		alert("城市选择不能为空！");
		return false;
	}
	else if($("input[name='cityS']").val()==1)
	{
		if($("input[name='cityI']").val()=="")
		{
			alert("感兴趣地方不能为空");
			return false;
		}
	}
//	if($("#tourS").val()==3&&$("#driverS").val()==3&&$("#tourAndDriverS").val()==3)
//	{
//		alert("城市交通不能为空！");
//		return false;
//	}
//	else if($("#driverS").val()!=3)
//	{
//		if($("input[name='selfDriver']").val()=="")
//		{
//			alert("自驾说明不能为空！");
//			return false;
//		}
//	}
	
	if($("#tourS").val()=="" || $("#tourS").val()==null){
		alert("城市交通不能为空！");
		return false;
	}else if($("#tourS").val()==3){
		if($("input[name='selfDriver']").val()=="")
		{
			alert("自驾说明不能为空！");
			return false;
		}
	}
	
	if($("select[name='hourseS']").val()==null)
	{
		alert("酒店标准不能为空！");
		return false;
	}
	if($("select[name='flightS']").val()==null)
	{
		alert("航班标准不能为空！");
		return false;
	}
	if($("select[name='rentCar']").val()==null)
	{
		alert("租车喜好不能为空！");
		return false;
	}
	if($("input[name='hourseNum']").val()=="")
	{
		alert("需要酒店房间数不能为空！");
		return false;
	}
	if($("input[name='backCity']").val()==""){
		alert("返回城市不能为空！");
		return false;
	}
	
	if(!($("input[name='service-req']:checked").val()==0)){
	var beizhu = $("input[name='beiz']");
	for(var i=0;i<beizhu.length;i++){
		if(beizhu[i].value==""){
			alert("请在主题规划中的确定行程中填写备注信息！");
			return false;
		}
	}
	}else{
	return true;
	}
}

function doSubmit(){
	
	if (Verification() == false) {
		return;
	}	
	var id = $("#userId").val();
//	if(id==null || id==""){
//		window.location.href="/user/login";
//	}else{
	$.ajax({
		url:"/trips/doCreate",
		async: false,
		data:$("#doCreate").serialize(),
		type: "POST",
		success: function(data){	
//			if(data=="/user/login"){
//				window.location.href=data;
//			}else{
			window.location.href=data; 
//			}
		},
		error: function(e) { 
			alert("看到此错误，请联系网站工作人员"); 
		} 
	});
//	}
};


/**
 * 点击去结算，跳转到去结算页面，将用户填写的信息填入数据库中
 */
function doSettlement(){
	if (Verification() == false) {
		return;
	}
	$.ajax({
		url:"/trips/dosettlement",
		async: false,
		data:$("#doCreate").serialize(),
		type: "POST",
		success: function(data){	
			window.location.href=data; 
		},
		error: function(e) { 
			alert("看到此错误，请联系网站工作人员"); 
		} 
	});
}



function guide() {

}

$(function () {
	trip.init();
});
