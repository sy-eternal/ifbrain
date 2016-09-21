
$(function(){
	
	/*var track = $('#example_video_1');;*/
	//setInterval(alert("Start: " + track.buffered.start(0)+ " End: " + track.buffered.end(0)),9000)
/*		var startBuffer = function() {
		var currentBuffer = video[0].buffered.end(0);
		var maxduration = video[0].duration;
		var perc = 100 * currentBuffer / maxduration;
		$('.bufferBar').css('width',perc+'%');
			
		if(currentBuffer < maxduration) {
			setTimeout(startBuffer, 500);
		}
	};*/	
	/*setInterval(function(){
		 if(track.onprogress){
		var w = 100*(track.buffered.end(0))/track.duration;
		$('.bufferBar').css("width",w+"%");
		 }
		}, 1000);*/
	/* track.onprogress = function(){
		 alert("fff");
	     if(track.buffered.length>0){
	         var w = 100*(track.buffered.end(0))/track.duration;
	         $('.bufferBar').css("width",w+"%");
	     }
	 }*/
	/*if(video.onprogress){ 
		var w2 = 100*(video.buffered.end(0))/video.duration; 
		$('.bufferBar').css("width",w2+"%"); 
		
	}*/
	//video.onprogress=function(){alert("Starting to load video")};
	
	/*setInterval(function(){
		video.onprogress = function(){
			 if (video.readyState === 4){
		    var w = 100*(video.buffered.end(0))/video.duration;
		    $('.bufferBar').css("width",w+"%");
			 }
		 }
		}, 1000);*/
	
	
	//点击级别
	$(".clickmaterailtype").each(function() {
		$(this).click(function(){
			  var courseid=$(this).find(".clickmaterailtypecourseid").attr('id');     
			window.location.href="/moviematerial/showmovie?courseid="+courseid;
		})
	})
	//点击视频
	$(".clickshowvideo").each(function() {
		  var val=$(this).attr("id");
		   var flagvalue=$("#clickval").val();      
		 if(val==flagvalue){
			 $(this).css('background-color','#0151a6');
			 $(this).find('#courseCodelessonName').css('color','#ffffff');
		 } 
		$(this).click(function(){ 
			var coursecodeid =$(this).find('.coursecodeid').attr('value');
			var courselevelid =$(this).find('.courselevelid').attr('value');
			var materialtypeid =$(this).find('.materialtypeid').attr('value');
			window.location.href="/moviematerial/showmovielist?coursecodeid="+coursecodeid+"&courselevelid="+courselevelid+"&materialtypeid="+materialtypeid;
		})
	})
});

 
