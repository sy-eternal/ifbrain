/**
 * Created by harry on 16/5/10.
 */

/**
 * common javascript
 */
$(function () {

    /**
     * top 鍥剧墖鏄剧ず闅愯棌
     */
    $(".js-top-show-handle").hover(
        function () {
            $(this).find(".v-jr-img-holder").stop().show();
        }, function () {
            $(this).find(".v-jr-img-holder").stop().hide();
        }
    );

    $(window).on("scroll", function () {
        var topElement = $("#js-slide-box");
        var top = topElement.offset().top - 0;
        var scrollTop = $(window).scrollTop();

        if (scrollTop > 40+top) {
            $("#js-nav").addClass("v-jr-nav-scroll");
            topElement.addClass("v-jr-slide-scroll");
        } else {
            $("#js-nav").removeClass("v-jr-nav-scroll");
            topElement.removeClass("v-jr-slide-scroll");
        }
    });

    $(window).trigger("scroll");

    //! 鐧诲綍鍚�,閫€鍑烘寜閽樉绀�/闅愯棌

    $("#js-nav-login-msg").hover(function () {
        $(this).addClass("active");
    }, function () {
        $(this).removeClass("active");
    });

});


// mobile
$(function () {

    /**
     * mobile 涓璪ar鍔熻兘
     */
    $("#js-slide-bar").on("click tap", function () {
        //! 澶勭悊bar鎵撳紑鍚巉ixed璺戝亸鐨勯棶棰�
        $("body").addClass("_slide-active");
        return false;
    });

    $("#js-slide-box").on("click tap", function () {
        $("body").removeClass("_slide-active");
    });

});