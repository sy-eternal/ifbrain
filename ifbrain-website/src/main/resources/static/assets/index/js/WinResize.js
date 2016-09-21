/*!VIPABC | Author by Mike Li*/
/*!v1.0 | 2015-2-27*/
/*!License: vipabc.com*/



//鍏ㄥ眬鍙橀噺
var WinG = {
    width : 0,
    height: 0
}

//鍑芥暟findDimensions锛氳幏鍙栧睆骞曞昂瀵革紙瀹介珮锛�
function findDimensions() 
{
    //鑾峰彇绐楀彛瀹藉害
    if (window.innerWidth)
        WinG.width = window.innerWidth;
    else if ((document.body) && (document.body.clientWidth))
        WinG.width = document.body.clientWidth;
    //鑾峰彇绐楀彛楂樺害
    if (window.innerHeight)
        WinG.height = window.innerHeight;
    else if ((document.body) && (document.body.clientHeight))
        WinG.height = document.body.clientHeight;
    //閫氳繃娣卞叆Document鍐呴儴瀵筨ody杩涜妫€娴嬶紝鑾峰彇绐楀彛澶у皬
    if (document.documentElement && document.documentElement.clientHeight && document.documentElement.clientWidth) {
        WinG.height = document.documentElement.clientHeight;
        WinG.width = document.documentElement.clientWidth;
    }
}

//鍒濆鍖栧嚱鏁�
findDimensions();


//瀵硅薄onWindowResize锛�
/*
    鐢ㄤ簬瑙ｅ喅 lte ie8 & chrome 鍙婂叾浠栧彲鑳戒細鍑虹幇鐨� 鍘熺敓 window.resize 浜嬩欢澶氭鎵ц鐨� BUG.
    //add: 娣诲姞浜嬩欢鍙ユ焺
    //remove: 鍒犻櫎浜嬩欢鍙ユ焺

*/
var onWindowResize = function(){
    //浜嬩欢闃熷垪
    var queue = [],
 
    indexOf = Array.prototype.indexOf || function(){
        var i = 0, length = this.length;
        for( ; i < length; i++ ){
            if(this[i] === arguments[0]){
                return i;
            }
        }
 
        return -1;
    };
 
    var isResizing = {}, //鏍囪鍙鍖哄煙灏哄鐘舵€侊紝 鐢ㄤ簬娑堥櫎 lte ie8 / chrome 涓� window.onresize 浜嬩欢澶氭鎵ц鐨� bug
    lazy = true, //鎳掓墽琛屾爣璁�
 
    listener = function(e){ //浜嬩欢鐩戝惉鍣�
        var h = window.innerHeight || (document.documentElement && document.documentElement.clientHeight) || document.body.clientHeight,
            w = window.innerWidth || (document.documentElement && document.documentElement.clientWidth) || document.body.clientWidth;
 
        if( h === isResizing.h && w === isResizing.w){
            return;
 
        }else{
            e = e || window.event;
         
            var i = 0, len = queue.length;
            for( ; i < len; i++){
                queue[i].call(this, e);
            }
 
            isResizing.h = h,
            isResizing.w = w;
        }
    }
 
    return {
        add: function(fn){
            if(typeof fn === 'function'){
                if(lazy){ //鎳掓墽琛�
                    if(window.addEventListener){
                        window.addEventListener('resize', listener, false);
                    }else{
                        window.attachEvent('onresize', listener);
                    }
 
                    lazy = false;
                }
 
                queue.push(fn);
            }else{  }
 
            return this;
        },
        remove: function(fn){
            if(typeof fn === 'undefined'){
                queue = [];
            }else if(typeof fn === 'function'){
                var i = indexOf.call(queue, fn);
 
                if(i > -1){
                    queue.splice(i, 1);
                }
            }
 
            return this;
        }
    };
}.call(this);


//缁戝畾window 鐨� resize 浜嬩欢

onWindowResize.add(findDimensions);