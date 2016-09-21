    //获取内容
/*$('input:radio:checked').val();
$("input[type='radio']:checked").val();
$("input[name='rd']:checked").val();*/
  var examName=$("#examName").val();
  var examTime=$("#examTime").val();
  var creditsRequired=$("#creditsRequired").val();
    function submitbefore(){
        	 if(examName=='')
        	 alert("请填写试卷名称");
        	 else if(examTime=='')
        		 alert("请填写考试时长");
        	 else if(creditsRequired=='')
        		 alert("请填写学分要求");
        	 else
        	 $("#mainFormsubmit").submit();
    	
    }