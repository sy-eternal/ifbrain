/*!VIPABC | Author by Mike Li*/
/*!v1.3 | 2015-1-16*/
/*!License: vipabc.com*/


/*var shareTo = shareTo || {
	
	init:function(){
		
	}
	
	
	
};*/


$(document).ready(function (e) {

    // functions
    //鍒嗕韩
    function shareBtn() {

        var title = document.title;
        var url = window.location.href;
        var pic = '';
        var share;
        var con = "    " + $("#description").attr("content");
        var e = encodeURIComponent;
        var windowName = '鍒嗕韩鍒�';
        var param = getParamsOfShareWindow(700, 450);

        //鍒嗕韩tips
        var tips1 = "分享到新浪微博";
        var tips2 = "分享到QQ空间";
        var tips3 = "分享到豆瓣";
        var tips4 = "分享到人人";
        var tips5 = "分享到微信";


        $(".share-wrap ul li").addClass("tips"); //澧炲姞鎻愮ず


        //鍒嗕韩鍒版柊娴井鍗�
        $(".sinaWeibo").click(function () {
            share = 'http://service.weibo.com/share/share.php?title=' + e(title + con) + '&url=' + e(url);
            window.open(share, windowName, param);
        }).attr("title", tips1);

        //鍒嗕韩鍒癚Q绌洪棿
        $(".tenWeibo").click(function () {
            share = 'http://share.v.t.qq.com/index.php?c=share&a=index&title=' + e(title + con) + '&url' + e(url);
            window.open(share, windowName, param);
        }).attr("title", tips2);

        //鍒嗕韩鍒拌眴鐡�
        $(".douban").click(function () {
            share = 'http://shuo.douban.com/!service/share?href=' + e(url) + '&text=' + e(title + con);
            window.open(share, windowName, param);
        }).attr("title", tips3);

        //鍒嗕韩鍒颁汉浜�
        $(".renren").click(function () {
            share = 'http://widget.renren.com/dialog/share?resourceUrl=' + e(url) + '&srcUrl=' + e(url) + '&title=' + e(title) + '&description=' + e(con);
            window.open(share, windowName, param);
        }).attr("title", tips4);


        //鍒嗕韩鍒� 寰俊

        $(function () {
            var _width = 200;
            var _height = 200;
            var $weixin = $(".weixin");


            if ((navigator.userAgent.indexOf("MSIE 6.0") > 0) || (navigator.userAgent.indexOf("MSIE 7.0") > 0) || (navigator.userAgent.indexOf("MSIE 8.0") > 0)) {

                //SimplePop.alert("褰撳墠娴忚鍣ㄧ増鏈繃浣庯紝鏃犳硶鐢熸垚浜岀淮鐮侊紝璇峰崌绾ф祻瑙堝櫒鑾峰緱鏇村ソ鐨勭敤鎴蜂綋楠�.");
                $weixin.hide(); //闅愯棌寰俊鍥炬爣
                $(".share-wrap").css({ "width": "260px" });
            }


            $weixin.click(function () {

                $("body").append("<div class='qr-code-wrap' style='display:none;'></div><div id='qr-code'><span title='鍏抽棴'></span><p>扫描二维码,分享给好友</p></div>");

                $("#qr-code").qrcode({

                    width: 200,
                    height: 200,
                    text: url
                });

                $(".qr-code-wrap").fadeIn(600, function () {

                    $("#qr-code").animate({
                        top: "30%",
                        opacity: 1

                    }, 600);

                });

                $(".qr-code-wrap, #qr-code span").on("click", function () {

                    $(".qr-code-wrap").fadeOut(500, function () {
                        $(this).remove();
                    });
                    $("#qr-code").remove();
                });

            }).attr("title", tips5);
        });

    }


    shareBtn();

    if (navigator.userAgent.match(/mobile/i) && !navigator.userAgent.match(/ipad/i)) {

        $(".share-wrap").hide();

    }
    else {
        $(".share-wrap").show();

    }

});




function getParamsOfShareWindow(width, height) {
    return ['toolbar=0,status=0,resizable=1,width=' + width + ',height=' + height + ',left=', (screen.width - width) / 2, ',top=', (screen.height - height) / 2].join('');
}



$(document).ready(function() {
	
	var _x = 30;
	var _y = 40;
	if (!navigator.userAgent.match(/mobile/i)) {
		$(".tips").mouseover(function(e){
				//alert(e.pageX);
				if(typeof($(this).attr('title'))!='undefined'){ // 鍒ゆ柇鏍囩涓槸鍚︽湁瀹氫箟title灞炴€�
					this.my_tit=this.title;
					this.title='';
					var tooltip="<div class='tooltip'>"+this.my_tit+ "<span></span>" +"</div>";
					$("body").append(tooltip);
					
					
					//var _x = $(this).width() / 2.0 ;
					//var _y = $tooltip.height();
					$(".tooltip").css({
						display:"block",
						left: e.pageX -  _x,
						top: e.pageY -  _y
						
					});
					
				}
			}).mouseout(function(){
					if(typeof($(this).attr('title'))!='undefined'){
						this.title=this.my_tit;
						$(".tooltip").remove();
					}
			}).mousemove(function(e){// 榧犳爣绉诲姩鏃惰窡闅�
					$(".tooltip").css({
						left: e.pageX - _x,
						top: e.pageY - _y
					});
			});
	}
});




	
/*	$("#sub_btn").click(function(){
		$("#code").empty();
		var str = toUtf8($("#mytxt").val());
		
		$("#code").qrcode({
			render: "table", //table娓叉煋,榛樿涓篶anvas
			width: 200,
			height:200,
			text: str
		});
	});*/
	

//杞腑鏂囧瓧绗︾殑鍑芥暟
/*function toUtf8(str) {   
    var out, i, len, c;   
    out = "";   
    len = str.length;   
    for(i = 0; i < len; i++) {   
    	c = str.charCodeAt(i);   
    	if ((c >= 0x0001) && (c <= 0x007F)) {   
        	out += str.charAt(i);   
    	} else if (c > 0x07FF) {   
        	out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));   
        	out += String.fromCharCode(0x80 | ((c >>  6) & 0x3F));   
        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
    	} else {   
        	out += String.fromCharCode(0xC0 | ((c >>  6) & 0x1F));   
        	out += String.fromCharCode(0x80 | ((c >>  0) & 0x3F));   
    	}   
    }   
    return out;   
}  */


