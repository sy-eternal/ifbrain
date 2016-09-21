
/*-------------- VIPABC | Author by Mike Li  ---------------*/
/*                   v3.1 | 2014-10-22                       */
/*                  License: vipabc.com                     */
/*----------------------------------------------------------*/


/*--------------------- banner婊氬姩 --------------------*/
function auto_banner() {
    $(".fotorama").fotorama({
        //width: 700,
        autoplay: 7000,
        fit: "cover",
        maxwidth: "100%",
        maxheight: 600,
        ratio: 1919 / 600,
        allowfullscreen: false,
        click: false,
        stopautoplayontouch: false,
        arrows: true,
        direction: "ltr",
        //startindex: 1,
        loop: true,
        swipe:false
        //hash: true
    });

 
    if (WinG.width >= 1000) {

        $(".fotorama").fotorama({
            minheight: 600,
            nav: "thumbs",
	        thumbwidth: 64,
	        thumbheight: 64,
	        thumbmargin: 60
        });
    }
    else {
        $(".fotorama").fotorama({
            minheight: 400,
            allowfullscreen: false,
            arrows: false,
            swipe: true,
            nav: "dots"
        });

        if (WinG.width <= 550) {

            $(".fotorama").fotorama({
                minheight: 150
            });
        }

    }

    function thumbStyle(){
		$(".fotorama__nav-wrap .fotorama__loaded--img img").animate({
			left:"-95px"
    	},300);
    }
	setTimeout(thumbStyle,1000);

	onWindowResize.add(thumbStyle);
}


/*---------------- 棣栭〉浜嬩欢骞垮憡鏉� -----------------------*/
//椤堕儴 骞垮憡鏉′簨浠� 鍑芥暟
function top_adv(){
	
	var adv_over = false; //鍒ゆ柇骞垮憡鏉℃槸鍚﹁鍏抽棴 , 榛� 璁や笉鍏抽棴
	var adv_delay = 5000; //骞垮憡鏄剧ず寤惰繜5000ms
	var $event_open = $("#event-banner-open"); //鎵撳紑骞垮憡鏉℃寜閽�
	var $event_close = $("#event-banner-colse"); //鍏抽棴骞垮憡鏉℃寜閽�
	var $event_wrap = $(".event-banner-wrap"); //骞垮憡鏉″鍣�
	var $nav = $(".nav"); //涓诲睆骞昻av鍐呭閮ㄥ垎瀹瑰櫒
	var Width = $nav.width();
	var left = $nav.offset().left;
	
	//鎸夐挳鏄剧ず浣嶇疆
	$event_close.css({
		"left": Width + left  + 10
	});
	$event_open.css({
		"left": Width + left  + 10
	});

    //寤舵椂5s鑷姩鍏抽棴
	if (gTopDelayTime > 0) {
	    $event_wrap.delay(adv_delay).slideUp(gTopDelayTime, function () {
	     	adv_over = true;
	     	show_open_btn(adv_over);
	     }); 
	}
	//鐐瑰嚮鍏抽棴骞垮憡鏉�
	$event_close.click(function() {
        //$(document).snowfall('clear');
		$event_wrap.stop(true,true).slideUp(400,function(){
			adv_over = true;
			show_open_btn(adv_over);
		});
	});
	//鐐瑰嚮閲嶆柊鎵撳紑骞垮憡鏉�
	$event_open.click(function () {
	    //snow();
	    adv_over = false;
	    //console.log(adv_over);
	    show_open_btn(adv_over);

	});
	
	//鏄剧ず鎵撳紑骞垮憡鐨勬寜閽� 鍑芥暟
	function show_open_btn(adv_over){
		
		if(adv_over){

		    if (WinG.width > 1050) {
				$event_open.fadeIn(500);
			}
			else{
				$event_open.fadeOut(0);
			}
		}
		else{
			$event_open.fadeOut(100);
			$event_wrap.stop(true,true).slideDown(500);
		}	
	}

}
/*---------------- 浜嬩欢骞垮憡鏉� end -----------------------*/


/*---------------------- 鍦ㄧ嚎鍜ㄨ妗� ----------------------*/
function online_contact(){
	
	
    if (WinG.width >= 550) {
		 
	     //鑷姩寮瑰嚭 鍦ㄧ嚎鍜ㄨ妗�
		var delay_time = 6000;  //鍦ㄧ嚎鍜ㄨ妗嗚嚜鍔ㄥ脊鍑虹殑鏃堕棿
	    $("#online-service").delay(delay_time).slideDown(500);
		$("#online-service-close").click(function() {
    		var $os_close = $("#online-service-open");
			var wrap_top = 0;
			var $os_offset = $os_close.offset();
			var _left = $os_offset.left + $os_close.width()/2;
			var _top = wrap_top + $os_close.height()/2 ;

			$("#online-service").animate({
				"left": _left + 240,
				"top":  _top + 60,
				"width": 0,
				"height": 0,
				"opacity": 0
				
			},1000,function(){
				$(this).hide();
			});
			$("#online-service").hide(0);
			return false;
		});
		

		$("#online-service-open").click(function () {
        /*
			$(".online-service").stop(true,true).removeAttr("style").fadeIn(500);
			return false;*/
		}); 
	 }


}

/*---------------------- 鍦ㄧ嚎鍜ㄨ妗� ----------------------*/



function funcHome(){
    top_adv();                      //椤堕儴 骞垮憡鏉′簨浠� 鍑芥暟
	online_contact();             //鍦ㄧ嚎鍜ㄨ寮瑰嚭绐�
    auto_banner();                 //banner鏁堟灉
    $("#fotorama").find("img").each(function () {
        $(this).attr("href", "http://www.vipabc.com");
    })

}

//});

onWindowResize.add(funcHome);

//榛樿杞藉叆鍑芥暟
$(document).ready(function () {
    
    top_adv();                    //椤堕儴 骞垮憡鏉′簨浠� 鍑芥暟
    online_contact();             //鍦ㄧ嚎鍜ㄨ寮瑰嚭绐�
    auto_banner();                //banner鏁堟灉
    

    /*----------------鑱旂郴鎴戜滑-灞曞紑--------------*/

    if (navigator.userAgent.match(/iPad/i)) {
        $(".tel_contactUs a").click(function () {
            if ($(this).find("span").hasClass("open")) {
                $(this).find("span").removeClass("open").hide();
            } else {
                $(this).find("span").addClass("open").show();
            }

        });
    }

});