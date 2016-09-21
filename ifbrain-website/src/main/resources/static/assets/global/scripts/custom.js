var CustomFormActions = function () {
    return {
        //main function to initiate the module
        init: function () {

        	//$('#days-spinner').spinner({disabled: true});
        	// radio button first page
		    $('input[type=radio][name=time-arrangement]').change(function() {

		        if (this.value == '0') {
		        	$(".time-sel0").show();
		        	$(".time-sel1").hide();
		        }
		        else if (this.value == '1') {
		        	$(".time-sel0").hide();
		        	$(".time-sel1").show();
		        }
		    });


		    $('#num-days-chk').change(function() {
		        if($(this).is(":checked")) {
		        	$('#touchspin_1').prop('disabled', false);
		            //$('#days-spinner').spinner({disabled: true});

		        } else {
		        	$('#touchspin_1').prop('disabled', true);
		            //$('#days-spinner').spinner({disabled: true});
		        }
		            
		    });

		    $('input[type=radio][name=service-req]').change(function() {

		        if (this.value == '0') {
		        	$(".confirmed-iten").hide();
		        	$(".must-go").hide();
		        }
		        else if (this.value == '1') {
		        	$(".confirmed-iten").show();
		        	$(".must-go").show();
		        }
		    });

		    var itenCountS = 1;
			$( "#more-iten-btnS" ).click(function() {
				
				itenCountS ++;
				var personCount=$('#personCount').val();
				if(itenCountS>personCount){
					document.getElementById('more-iten-btnS').disabled=true;
					return false;
				}
			 	$('#iten-tableS').append("" +
			 			"<tr>" +
			 			"<th width='20%'></th>" +
			 			"<th >第"+itenCountS+"人</th>" +
			 					"</tr><tr>" +
			 					"<th>中文姓</th>" +
			 					"<th><input type='text' id='xins' name='xins' size='50' /></th>" +
			 					"</tr><tr><th>中文名</th>" +
			 					"<th><input type='text' id='mings' name='mings' size='50'  /></th>" +
			 					"</tr><tr>" +
			 					"<th>英文姓</th>" +
			 					"<th><input type='text' id='firstNames' name='firstNames' size='50'  />" +
			 					"</th></tr>" +
			 					"<tr>" +
			 					"<th>英文名</th>" +
			 					"<th><input type='text' id='secondName' name='secondName' size='50'   /></th>" +
			 					"</tr><tr>" +
			 					"<th>性别</th>" +
			 					"<th>" +
			 					"<input type='radio' name='genders"+ itenCountS+"' checked='checked' value='1'/>&nbsp;男&nbsp;&nbsp;<input type='radio' name='genders"+ itenCountS+"' value='2'/>&nbsp;女" +
			 					"</th>"+
			 					"</tr><tr>" +
			 					"<th>出生年月</th>" +
			 					"<th><input type='text' id='birthDates' name='birthDates' size='50'  /></th>" +
			 					"</tr><tr>" +
			 					"<th>护照号</th>" +
			 					"<th><input type='text' id='passportNums' name='passportNums' size='50'/></th>" +
			 					"</tr><tr>" +
			 					"<th>国际驾照号</th>" +
			 					"<th><p>如果有自驾行程，请输入您的国际驾照号</p><input type='text' id='driveLicenseCodes' name='driveLicenseCodes' size='50' /></th>" +
			 					"</tr><tr>" +
			 					"<th>手机号（加国家）</th>" +
			 					"<th><input type='text' id='moblieNums' name='moblieNums' size='50'  value='+86'/></th>" +
			 					"</tr>" +
			 					"<tr>" +
			 					"<th>微信号</th>" +
			 					"<th><input type='text' id='weichats' name='weichats' size='50'  /></th>" +
			 					"</tr>"+
			 					"<tr>" +
			 					"<th>邮箱</th>" +
			 					"<th><input type='text' id='emails' name='emails' size='50'  /></th>" +
			 					"</tr>");
			 	return true;
			});			
		    
			
		    
		    
			//主题规划->规划方式->已经确定下来的行程->添加行程按钮
		    var itenCount = 0;
			$( "#more-iten-btn" ).click(function() {
				itenCount ++;
				var index = itenCount +3;
				
			 	$('#iten-table').append('<tr><td><select class="form-control" id="select2_8" name="addr">'+$("#select2_8").html()+'</select><div class="form-control-focus"></div></td><td><div class="input-group" id="defaultrange' + index +'"><input type="text" class="form-control" name="tim" /><span class="input-group-btn"><button class="btn default date-range-toggle" type="button"><i class="fa fa-calendar"></i></button></span></div></td><td><input type="checkbox" class="make-switch" checked data-on-color="success" name="isServi" data-off-color="warning" data-on-text="需要" data-off-text="不需要"></td><td><input type="text" class="form-control" name="beiz"  style="width:150px;" placeholder="请尽可能详细填写"/></td><td><a href="javascript:;" class="remove-field"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td></tr>');

//			 	alert(itenCount);

				$('#iten-table tr').on('click', '.remove-field', function () {
			        $(this).closest('tr').remove()
			        itenCount--;
				});

			 	$('.make-switch').bootstrapSwitch();
		        $('#defaultrange' + index).daterangepicker({
		            opens: (Metronic.isRTL() ? 'left' : 'right'),
		            format: 'YYYY-MM-DD',
		            separator: ' to ',
		            startDate: moment().add('days', 30),
		            endDate: moment().add('days', 30),
		            minDate: moment().add('days', 5),
		            maxDate: moment().add('years', 2),
		        },
		            function (start, end) {
//				 	alert('#defaultrange' + index);

		                $('#defaultrange' + index + ' input').val(start.format('YYYY年MMMM月D日') + ' 至 ' + end.format('YYYY年MMMM月D日'));
		            }
		        );  
			});

			
			$('#touchspin_2').change(function() {
				  var n = document.forms[0].peopleNum.value;
				  if(isNaN(n)) return false;
				  n = parseInt(n);
				  var obj = document.getElementById("pad");
				  var s = "";
				  for(var i=1;i<=n;i++){
			   s +="<br />第"+i+"个人的年龄为" +"&nbsp;&nbsp;&nbsp;" +"<input type='text'   size='9' id='more-iten-btnsss 'name='memberage' required='required' />&nbsp;岁<br/>";
				  }
				  obj.innerHTML = s;
		    });
			
			
			$('input[type=radio][name=city-service-req]').change(function() {
		        if (this.value == '0') {
		        	$(".interested-places").hide();
		        }
		        else if (this.value == '1') {
		        	$(".interested-places").show();
		        }
		    });

		    $('#self-drive-chk').change(function() {
		        if($(this).is(":checked")) {
		        	$(".driving-notes").show();
		            //$('#days-spinner').spinner({disabled: true});

		        } else {
		        	$(".driving-notes").hide();
		            //$('#days-spinner').spinner({disabled: true});   
		        }          
		    });

		 	$('#guide-drive-chk-part').change(function() {
		        if($(this).is(":checked")) {
		        	$(".driving-notes").show();
		            //$('#days-spinner').spinner({disabled: true});
		        } 
		            
		    });
		    $('#self-drive-chk-part').change(function() {
		        if($(this).is(":checked")) {
		        	$(".driving-notes").show();
		            //$('#days-spinner').spinner({disabled: true});
		        } 
		            
		    });
		    $('#public-trans-chk-part').change(function() {
		        if($(this).is(":checked")) {
		        	$(".driving-notes").show();
		            //$('#days-spinner').spinner({disabled: true});
		        } 
		            
		    });
		    

        }
    };

}();






