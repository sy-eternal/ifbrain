    //获取内容
    function submitbefore(){
    
    	/*var value=$("#file").val();
    	var imgArr = ["bmp","jpg","gif","png"];
    	if(value==''){
    		alert("请上传图片!");
    		return false;
    	}else{
             var len = value.length;
             var ext = value.substring(len-3,len).toLowerCase();
             if($.inArray(ext,imgArr) == -1)
                alert("请上传图片的格式!");
             else*/
              var content=CKEDITOR.instances['ckeditor'].getData();
        	 var content=$("#content").val(content);
        	 
        	 if(content=='')
        	 alert("请填写文章内容!");
        	 else
        	 $("#mainFormsubmit").submit();
    	/*}*/
    	
    	
    }