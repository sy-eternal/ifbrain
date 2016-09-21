    //获取内容
/*$('input:radio:checked').val();
$("input[type='radio']:checked").val();
$("input[name='rd']:checked").val();*/
    function submitbefore(){
              var content=CKEDITOR.instances['ckeditor'].getData();
        	 var content=$("#content").val(content);
        	 var content1=CKEDITOR.instances['ckeditor1'].getData();
        	 var content1=$("#content1").val(content1);
        	 var answer=$('input:radio:checked').val();
        	 if(content==''&&content1==''){
        	 alert("请填写题目内容!");
        	 return false;
        	 }
        	 else if(answer==''){
        	 alert("请设置试题答案");
        	 return false;
        	 }
        	 else{
             $("#answer").val(answer);
        	 $("#mainFormsubmit").submit();
        	 }
    }