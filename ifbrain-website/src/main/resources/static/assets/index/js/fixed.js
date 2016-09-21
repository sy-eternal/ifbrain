
/*!VIPABC | Author by Mike Li*/
/*!v1.0 | 2015-2-27*/
/*!License: vipabc.com*/

//鍏ㄥ眬鍙橀噺
var winTop = $(window).scrollTop();      //榧犳爣婊氬姩鐨勯珮搴�
var Is_show = false;
var Is_show2 = false;

/*----------------- 涓诲鑸� 璺熼殢榧犳爣 婊氬姩 -----------------*/
	 
function nav_fixed(e){
	
	//e = e || window.event;  	
	//椤堕儴鑿滃崟闅忔粴鍔ㄤ竴璧锋诞鍔�
	var winTop = $(window).scrollTop();
	var $Fixed2 = $(".header-fixed");

	if(!$Fixed2.length || !$(".header-float").length) {
		return false;
	}

	var $offset2 = $(".header-float").offset();
	
	
	if (!navigator.userAgent.match(/mobile/i)) {
		//PC涓�
		if( winTop > 30 ){
			if( Is_show2 === false ){
				
				Is_show2 = true;
				$Fixed2.css({
				    "position": "fixed",
				    "_position": "absolute",
				    "top": "0",
				    "left": $offset2.left + "px",
				    "z-index": 2000,
				    "box-shadow": "0 1px 3px rgba(0,0,0,0.4)"

				});
			}
			else{
				$Fixed2.css({
					"top":"0px"
				});	
			}
			
			
		}else{
			$Fixed2.removeAttr("style");
			Is_show2 = false;
			
		}	
	
	}
	
		     
};
/*----------------- 涓诲鑸� 璺熼殢榧犳爣 婊氬姩 end -----------------*/


/*----------------------- 鍙充晶娴姩琛ㄥ崟妗� -------------------*/
function sidebar_fixed() {

	if(!$(".sidebar").length || !$(".container .inside").length || !$(".footer").length) {
		return false;
	}

	var winTop = $(window).scrollTop();
	var $Fixed = $(".sidebar");
    var $offset = $(".container .inside").offset(); //涓嶈兘鐢ㄨ嚜韬殑div锛屼笉鐒舵粴鍔ㄨ捣鏉ヤ細寰堝崱
	var _left = $offset.left + 780;
	
	var _stop = $(".footer").offset().top;

	
	if (!navigator.userAgent.match(/mobile/i)) {
		//PC涓�				
		if( winTop > $offset.top ){
			if( Is_show === false ){
				
				Is_show = true;
				$Fixed.css({
					"position":"fixed",
					"_position":"absolute",
					"top":"116px",
					"left":_left,
					"z-index":4000
				});

			}
			else{
				$Fixed.css({
					"top":"116px"
				});	
			}
			
			
		}else{
			$Fixed.removeAttr("style");
			Is_show = false;
			
		}	
		
		
		/*if( _stop - winTop > 300 ){
			
			$Fixed.css({
				"top":"-133px"
			});	
		}*/
	
	}

}
/*----------------------- 鍙充晶娴姩琛ㄥ崟妗� end -------------------*/


function fixedAll(){
	nav_fixed();
	sidebar_fixed();
}

$(document).ready(function(e) {
   fixedAll(); 
});

$(window).scroll(function(e) {
    fixedAll(); 
});

/*//缁欓〉闈㈢粦瀹氭粦杞粴鍔ㄤ簨浠�  
if (document.addEventListener) {//firefox  
	document.addEventListener('DOMMouseScroll', fixedAll, false);  
}  
//婊氬姩婊戣疆瑙﹀彂scrollFunc鏂规硶  //ie 璋锋瓕  
window.onmousewheel = document.onmousewheel = fixedAll; */
