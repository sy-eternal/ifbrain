$(function() {

    function prevent_default(e) {
        e.preventDefault();
    }

    function disable_scroll() {
        $(document).on('touchmove', prevent_default);
    }

    function enable_scroll() {
        $(document).unbind('touchmove', prevent_default)
    }

    var x;
    $('.swipe-delete li > a')
        .on('touchstart', function(e) {
        	
            console.log(e.originalEvent.pageX)
            $('.swipe-delete li > a').css('left', '0px') // close em all
            $(e.currentTarget).addClass('open')
            x = e.originalEvent.targetTouches[0].pageX // anchor point
        })
        .on('touchmove', function(e) {
        
            var change = e.originalEvent.targetTouches[0].pageX - x
            change = Math.min(Math.max(-100, change), 0) // restrict to -100px left, 0px right
            e.currentTarget.style.left = change + 'px'
            if (change < -10) disable_scroll() // disable scroll once we hit 10px horizontal slide
        })
        .on('touchend', function(e) {
            var left = parseInt(e.currentTarget.style.left)
            var new_left;
            if (left < -35) {
                new_left = '-100px'
            } else if (left > 35) {
                new_left = '0px'
            } else {
                new_left = '0px'
            }
            // e.currentTarget.style.left = new_left
            $(e.currentTarget).animate({left: new_left}, 200)
            enable_scroll()
        });

   $('.parent .delete-btn').on('touchend', function(e) {
	   
	   var tokennumbers=$("#tokennumbers"+id+"").val();
	    var number=parseInt(tokennumbers);
	   $('#textMachine').prop('number', 1).animateNumber({
	    		     number: number
	    		   },
	    		   1000
	    		); 
	   //弹出金币模态框播放音乐
	   $('#money').modal({
	        show:true,
	        backdrop:true
	        });
	   var audioEle = document.getElementById("audio");
	    audioEle.play();
	    setTimeout('close()',2000);
        e.preventDefault();
      var taskId=$(this).find("input").val();
      var timesadd=$("input[name='"+taskId+"']").val();
      
      $(this).parents('.parent').slideUp('fast', function() {
          $(this).remove();
      });
      $("#finishid").val("0");
      $.ajax({
		    async : false,
			type : "Get",
			url : "/child/finishtask?taskId=" + taskId+"&amp;timesadd="+timesadd,
			success : function(data) {
				if(data=="1"){
					
				}
			}
		});  
      
    })

  /*  $('li .edit-btn').on('touchend', function(e) {
        e.preventDefault()
        $(this).parents('li').children('a').html('edited')
    })*/

});