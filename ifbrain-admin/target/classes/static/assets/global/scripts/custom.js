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

		    var itenCount = 0;
			$( "#more-iten-btn" ).click(function() {
				itenCount ++;
			 	$('#iten-table').append('<tr><td><input type="text" class="form-control" id="form_control_1" placeholder="在此输入城市"><div class="form-control-focus"></div></td><td><div class="input-group" id="defaultrange' + (3 + itenCount) +'"><input type="text" class="form-control"><span class="input-group-btn"><button class="btn default date-range-toggle" type="button"><i class="fa fa-calendar"></i></button></span></div></td><td><input type="checkbox" class="make-switch" checked data-on-color="success" data-off-color="warning" data-on-text="需要" data-off-text="不需要"></td><td><a href="javascript:;" class="remove-field"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a></td></tr>');


				$('#iten-table tr').on('click', '.remove-field', function () {
			        $(this).closest('tr').remove()
			        itenCount--;
				});

			 	$('.make-switch').bootstrapSwitch();
		        $('#defaultrange' + (3 + itenCount)).daterangepicker({
		            opens: (Metronic.isRTL() ? 'left' : 'right'),
		            format: 'YYYY-MM-DD',
		            separator: ' to ',
		            startDate: moment().add('days', 30),
		            endDate: moment().add('days', 30),
		            minDate: moment().add('days', 5),
		            maxDate: moment().add('years', 2),
		        },
		            function (start, end) {
		                $('#defaultrange' + (3 + itenCount) + ' input').val(start.format('YYYY年MMMM月D日') + ' 至 ' + end.format('YYYY年MMMM月D日'));
		            }
		        );  
			});

			$('input[type=radio][name=city-service-req]').change(function() {
		        if (this.value == '0') {
		        	$(".interested-places").hide();
		        }
		        else if (this.value == '1') {
		        	$(".interested-places").show();
		        }
		    });

			
			$('#touchspin_2').change(function() {
				  var n = document.forms[0].peopleNum.value;
				  if(isNaN(n)) return false;
				  n = parseInt(n);
				  var obj = document.getElementById("pad");
				  var s = "";
				  for(var i=1;i<=n;i++){
			   s +="<br />第"+i+"个人的年龄为" +"&nbsp;&nbsp;&nbsp;" +"<input type='text'   size='9' id='more-iten-btnsss 'name='memberage' />&nbsp;岁<br/>";
				  }
				  obj.innerHTML = s;
		    });
			
			
			
			$('#touchspin_4').change(function() {
				  var n = document.forms[1].peopleNums.value;
				  if(isNaN(n)) return false;
				  n = parseInt(n);
				  var obj = document.getElementById("pads");
				  var s = "";
				  for(var i=1;i<=n;i++){
			   s +="<br />第"+i+"个人的年龄为" +"&nbsp;&nbsp;&nbsp;" +"<input type='text'   size='9' id='more-iten-btnsss 'name='memberage' />&nbsp;岁<br/>";
				  }
				  obj.innerHTML = s;
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
$(function () {
	CustomFormActions.init();
});






