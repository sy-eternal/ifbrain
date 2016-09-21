
/*!VIPABC | Author by Mike Li*/
/*!v1.0 | 2015-2-27*/
/*!License: vipabc.com*/

$(document).ready(function () {



    /*------------- 榧犳爣鐒︾偣绉昏繘锛屾枃瀛楁秷澶� ---------------*/
    $(".focus-on").focus(function () {
        var check1 = $(this).val();
        if (check1 == this.defaultValue) {
            $(this).val("");
            $(this).css("color", "#000");
        }
    }).blur(function () {
        if ($(this).val() == "") {
            $(this).val(this.defaultValue);
            $(this).css("color", "#bababa");
        }

    });


    /*----------------- 鍏抽棴鍏煎鎬ф彁绀� ----------------*/
    $(".cp-tips-close").click(function (e) {
        $(".cp-tips").slideUp(400);
    });

    /*----------------- 缃《鍔熻兘 -----------------*/
    $("#goTop").click(function () {
        $("body,html").animate({
            scrollTop: 0
        },
		500); //鐐瑰嚮鍥炲埌椤堕儴鎸夐挳锛岀紦鎳傚洖鍒伴《閮�,鏁板瓧瓒婂皬瓒婂揩
        return false;
    });


    /*鎵嬫満鏄剧ず鑿滃崟*/
    $(".mobile-nav").click(function (e) {
        $(".nav-wrapper").slideToggle(200);
    });



    /*瑙嗛鎾斁*/

    $(".show-video").click(function () {

        var _video_id = $(this).attr("data-name");
//        var _src = wrapUrl("/video/source/" + _video_id + ".mp4");
        var _src = wrapUrl(_video_id);

        var $video_wrap = $("<div class='video-special-wrap'><video id='example_video' class='video-js vjs-default-skin' controls preload='none' width='100%' height='100%' data-setup='{}'><source src='" + _src + "' type='video/mp4' /></video><span id='video-special-wrap-close'></span></div>");

        var _width = 600;
        var _height = 400;
        var _left = "50%";
        if (!(!navigator.userAgent.match(/mobile/i) || navigator.userAgent.match(/iPad/i))) {
            var _width = "80%";
            var _height = 200;
            var _left = "5%";
        }
        else {

        }

        $(".wrapper").append($video_wrap);
        $(".float-lay-bg").fadeIn();
        $(".video-special-wrap").css({
            position: "fixed",
            width: _width,
            height: _height,
            left: _left,
            top: "50%",
            "z-index": 10000,
            "margin-left": -(_width / 2),
            "margin-top": -(_height / 2)
        });


        videojs("example_video", { "autoplay": true });

        $("#video-special-wrap-close").css({

            position: "absolute",
            width: "30px",
            height: "30px",
            right: "-30px",
            top: "-30px",
            "background": "#fff url(" + wrapUrl("/Images/couese_close.png") + ") no-repeat center center",
            cursor: "pointer"

        }).on("click", function (e) {
            _video_id = "";
            _src = "";
            $video_wrap.detach();
            $(".float-lay-bg").hide();

        });

        return false;

    });





    /*----------------- 杞欢娴嬭瘯涓撳尯 --------------------*/

    $(".softwareTest-title ul li").click(function (e) {

        var _index = $(this).index();
        $(this).addClass("item-on").siblings().removeClass("item-on");
        $(".softwareTest-content div.item-content").eq(_index).addClass("item-content-on").siblings().removeClass("item-content-on")

    });


    /*----------------- Top_fix --------------------*/
    if (!navigator.userAgent.match(/mobile/i) || navigator.userAgent.match(/iPad/i)) {

        if (navigator.userAgent.match(/iPad/i)) {

            $("#fix-weixin").focus(function () {
                $(".fix-weixin-warp span").show();
            }).blur(function (e) {
                $(".fix-weixin-warp span").hide();
            });

            $("#fix-app").focus(function () {
                $(".fix-app-warp span").show();
            }).blur(function (e) {
                $(".fix-app-warp span").hide();
            });
        }
        else {
            $("#fix-weibo").hover(function (e) {
                $(this).text("+鍏虫敞");
            }, function () {
                $(this).text("寰崥");

            });
            $("#fix-weixin").hover(function () {
                $(".fix-weixin-warp span").show();
            }, function () {
                $(".fix-weixin-warp span").hide();
            });

            $("#fix-app").hover(function () {
                $(".fix-app-warp span").show();
            }, function () {
                $(".fix-app-warp span").hide();
            });
        }


    }


    /*寮瑰嚭灞�-浣撻獙*/
    if (!navigator.userAgent.match(/mobile/i) || navigator.userAgent.match(/iPad/i)) {
        // pc or ipad

        $(".write_contactUs").click(function (e) {

            showWriteToContactUs();

        });


    } else {

        // mobile
        $(".write_contactUs").attr("href", "#yao-form");


    }


});

function showWriteToContactUs() {
    
    if (!navigator.userAgent.match(/mobile/i) || navigator.userAgent.match(/iPad/i)) {
        // pc or ipad
        $(".float-lay-bg").fadeIn();
        var container = $(".invisible .write-to-us-container")
                .clone(true)
                .appendTo(".wrapper").css({
                    position: "fixed",
                    width: "740px",
                    height: "500px",
                    left: "50%",
                    top: "50%",
                    "z-index": 9000,
                    "margin-left": "-370px",
                    "margin-top": "-250px"
                });

        $(".LayoutForm-wrap-close", container).css({

            position: "absolute",
            width: "30px",
            height: "30px",
            right: "-30px",
            top: "-30px",
            "background": "#fff url(" + wrapUrl("/images/couese_close.png") + ") no-repeat center center",
            cursor: "pointer"

        }).on("click", function (e) {

            $(this).closest(".write-to-us-container").remove();
            $(".float-lay-bg").hide();

        });
    }
    else {

        // mobile
        
        $('body,html').animate({
                scrollTop: $("#yao-form").offset().top
            },
            100);

        }
}

