


//鍏ㄥ眬鍙橀噺锛岃Е鎽稿紑濮嬩綅缃�  
var startX = 0, startY = 0;  
  
//touchstart浜嬩欢  
function touchSatrtFunc(evt) {  

		//evt.preventDefault(); //闃绘瑙︽懜鏃舵祻瑙堝櫒鐨勭缉鏀俱€佹粴鍔ㄦ潯婊氬姩绛�  
		var touch = evt.touches[0]; //鑾峰彇绗竴涓Е鐐�  
		var x = Number(touch.pageX); //椤甸潰瑙︾偣X鍧愭爣  
		var y = Number(touch.pageY); //椤甸潰瑙︾偣Y鍧愭爣  
		//璁板綍瑙︾偣鍒濆浣嶇疆  
		startX = x;  
		startY = y;  

		var text = 'TouchStart浜嬩欢瑙﹀彂锛氾紙' + x + ', ' + y + '锛�';  
		//document.getElementById("result").innerHTML = text;  
}  

//touchmove浜嬩欢锛岃繖涓簨浠舵棤娉曡幏鍙栧潗鏍�  
function touchMoveFunc(evt) {  

	//evt.preventDefault(); //闃绘瑙︽懜鏃舵祻瑙堝櫒鐨勭缉鏀俱€佹粴鍔ㄦ潯婊氬姩绛�  
	var touch = evt.touches[0]; //鑾峰彇绗竴涓Е鐐�  
	var x = Number(touch.pageX); //椤甸潰瑙︾偣X鍧愭爣  
	var y = Number(touch.pageY); //椤甸潰瑙︾偣Y鍧愭爣  

	var text = 'TouchMove浜嬩欢瑙﹀彂锛氾紙' + x + ', ' + y + '锛�';  

	//鍒ゆ柇婊戝姩鏂瑰悜  
	if (x - startX != 0) {  
		text += '<br/>宸﹀彸婊戝姩';  
	}  

	if( y - startY  > 0 ){
		text += '<br/><br/>涓婃粦'; 
/*		$(".header-fixed").css({
			"position":"fixed",
			"_position":"absolute",
			"top":0,
			"left":0,
			"z-index":"5000",
			"box-shadow":"0 1px 3px rgba(0,0,0,0.4)"
		});	*/
			
		
	}else if( y - startY  <= 100 ){
		text += '<br/><br/>涓嬫粦'; 
		
		//$(".header-fixed").removeAttr("style");
		
		
	}

	//document.getElementById("result").innerHTML = text;  

}  

//touchend浜嬩欢  
function touchEndFunc(evt) {  
		
	//evt.preventDefault(); //闃绘瑙︽懜鏃舵祻瑙堝櫒鐨勭缉鏀俱€佹粴鍔ㄦ潯婊氬姩绛�  
	var text = 'TouchEnd浜嬩欢瑙﹀彂';  
	//document.getElementById("result").innerHTML = text;  

}  

//缁戝畾浜嬩欢  
function bindEvent() {  
	document.addEventListener('touchstart', touchSatrtFunc, false);  
	document.addEventListener('touchmove', touchMoveFunc, false);  
	document.addEventListener('touchend', touchEndFunc, false);  
}  

//鍒ゆ柇鏄惁鏀寔瑙︽懜浜嬩欢  
function isTouchDevice() {  
	//document.getElementById("version").innerHTML = navigator.appVersion;  

	try {  
		document.createEvent("TouchEvent");  
		//alert("鏀寔TouchEvent浜嬩欢锛�");  

		bindEvent(); //缁戝畾浜嬩欢  
	}  
	catch (e) {  
		//alert("涓嶆敮鎸乀ouchEvent浜嬩欢锛�" + e.message);  
	}  
}  

window.onload = isTouchDevice;  