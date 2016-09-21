var AITA = "@";

$(document).ready(function (e) {
    //顶部AD定时收起
    //$('#hometop_ad').delay(8000).slideUp();

    //标签切换
    $(".tabs>ul>li").bind('click', function () {
        $(this).addClass('active').siblings().removeClass('active');
        $('.tabs div.tab_content').eq($('.tab li').index(this)).fadeIn().siblings("div.tab_content").hide();
        return false;
    });

    $('.slideTxtBox .hd ul li').click(function () {
        var len = $(this).index();
        if (len == 0) {
            $(this).css({ background: '#aec6f4' }).siblings().attr({ style: '' });
        }
        if (len == 1) {
            $(this).css({ background: '#c4dfda' }).siblings().attr({ style: '' });
        }
        if (len == 2) {
            $(this).css({ background: '#ffd27f' }).siblings().attr({ style: '' });
        }
        if (len == 3) {
            $(this).css({ background: '#87e5a7' }).siblings().attr({ style: '' });
        }

    });

    $('.people_list>a').click(function () {
        $('.people_lists_box').html($('.people_lists_box').html());
        var index = $(this).index();
        var left = -index * 730;
        $('.people_lists_box>ul').fadeOut(500, function () {
            $(this).css({ left: left + 'px' });
        }).fadeIn(500);
    })

    var width = document.documentElement.clientWidth;
    if (width <= 1280) {
        $('.subnav').css({ right: '-150px' });
        $('.subnav_small').css({ display: 'block' });
        $('.subnav').hover(function () {
            $(this).css({ right: '0px' });
            $('.subnav_small').css({ display: 'none' });
        }, function () {
            $(this).css({ right: '-150px' });
            $('.subnav_small').css({ display: 'block' });
        })
    } else {
        $('.subnav').css({ right: '0px' }).unbind();
        $('.subnav_small').css({ display: 'none' });
    }

    shareBtn();



});

window.onresize = function () {
    var width = document.documentElement.clientWidth;
    if (width <= 1280) {
        $('.subnav').css({ right: '-150px' });
        $('.subnav_small').css({ display: 'block' });
        $('.subnav').hover(function () {
            $(this).css({ right: '0px' });
            $('.subnav_small').css({ display: 'none' });
        }, function () {
            $(this).css({ right: '-150px' });
            $('.subnav_small').css({ display: 'block' });
        })
    } else {
        $('.subnav').css({ right: '0px' }).unbind();
        $('.subnav_small').css({ display: 'none' });
    }
}

function bingsourcelist() {
    //sourcelist
    $(".sourcelist li label").click(function () {
        $(this).prev().trigger("click");
        var rid = $(this).prev().val();
        $("#ReasonId").val(rid);
    });
}

//隐藏层

function boxhide(thisdiv) {

    $(thisdiv).hide();
}

//关闭视频窗口
function videoclose() {
    $('.video_box').hide();
    $("#videoplay").attr("src", "");
    $('.mark').hide();
}

//打开视频窗口
function videoplay() {
    var timeNow = new Date();
    $.ajax({
        type: "GET",
        url: "xml/AutoVocabulary.xml",
        dataType: "xml",
        success: function (xml) {
            var newsVideoUrlName = "XNjc4NTUzMjIw";
            var newImageUrl = "/Images/VedioShort/mem-4.jpg";
            $(xml).find("Item").each(function (i) {
                var startDate = new Date($(this).find("StartTime").text());
                var endDate = new Date($(this).find("EndTime").text());
                if (timeNow >= startDate && timeNow <= endDate) {
                    var newsVideoUrl = $(this).find("Url").text();
                    var newImageUrl = $(this).find("ImageUrl").text();
                    $("#newImg").attr("src", newImageUrl);
                    $("#videoplay").attr("src", newsVideoUrl);
                }
            });
        },
        error: function (xml) {
            //alert('讀取xml錯誤' + xml + timeNow); //當xml讀取失敗
            $("#videoplay").attr("src", "/Video/index?type=1&name=XNjc4NTUzMjIw&url=/Images/VedioShort/mem-4.jpg");
        }

    });
    $('.mark').show();
    //var html = '<iframe height="484" width="828" src="http://player.youku.com/embed/XNjc4NTUzMjIw" frameborder="0" allowfullscreen></iframe>';
    //var html = '<embed src="http://static.youku.com/v/swf/qplayer.swf?winType=adshow&VideoIDS=XNjYyODI3OTU2&isAutoPlay=true&isShowRelatedVideo=false" wmode="transparent" width="828" align="center" border= "0" height="484"></embed>';
    $('.video_box').show();


    /*$('.mark').show();
    $('.video_box').show();*/
}


//cookie

jQuery.cookie = function (name, value, options) {
    if (typeof value != 'undefined') {
        options = options || {};
        if (value === null) {
            value = '';
            options = $.extend({}, options);
            options.expires = -1;
        }
        var expires = '';
        if (options.expires && (typeof options.expires == 'number' || options.expires.toUTCString)) {
            var date;
            if (typeof options.expires == 'number') {
                date = new Date();
                date.setTime(date.getTime() + (options.expires * 24 * 60 * 60 * 1000));
            } else {
                date = options.expires;
            }
            expires = '; expires=' + date.toUTCString();
        }
        var path = options.path ? '; path=' + (options.path) : '';
        var domain = options.domain ? '; domain=' + (options.domain) : '';
        var secure = options.secure ? '; secure' : '';
        document.cookie = [name, '=', encodeURIComponent(value), expires, path, domain, secure].join('');
    } else {
        var cookieValue = null;
        if (document.cookie && document.cookie != '') {
            var cookies = document.cookie.split(';');
            for (var i = 0; i < cookies.length; i++) {
                var cookie = jQuery.trim(cookies[i]);
                if (cookie.substring(0, name.length + 1) == (name + '=')) {
                    cookieValue = decodeURIComponent(cookie.substring(name.length + 1));
                    break;
                }
            }
        }
        return cookieValue;
    }
};

function setCookie(name, value) {
    $.cookie(name, value);
}

//读取cookies
function getCookie(name) {
    var result = $.cookie(name);
    return result == null ? "" : result;
}

//删除cookies
function delCookie(name) {
    $.cookie(name, null);
}

//分享
function shareBtn() {
    $('.share>a').attr('href', 'javascript:void(0);')
    $('.share>a:eq(4)').css({ display: 'none' });
    var title = "VIPABC青少年语言发展专家。";
    var url = window.location.href;
    var pic = '';
    var share;
    var con = '';
    var e = encodeURIComponent;
    var windowName = '分享到';
    var param = getParamsOfShareWindow(700, 450);

    $('.share>a:eq(0)').click(function () {
        share = 'http://service.weibo.com/share/share.php?title=' + e(title + con) + '&url=' + e(url);
        window.open(share, windowName, param);
    })
    $('.share>a:eq(1)').click(function () {
        share = 'http://share.v.t.qq.com/index.php?c=share&a=index&title=' + e(title + con) + '&url' + e(url);
        window.open(share, windowName, param);
    })
    $('.share>a:eq(2)').click(function () {
        share = 'http://shuo.douban.com/!service/share?href=' + e(url) + '&text=' + e(title + con);
        window.open(share, windowName, param);
    })
    $('.share>a:eq(3)').click(function () {
        share = 'http://widget.renren.com/dialog/share?resourceUrl=' + e(url) + '&srcUrl=' + e(url) + '&title=' + e(title) + '&description=' + e(con);
        window.open(share, windowName, param);
    })
}

function getParamsOfShareWindow(width, height) {
    return ['toolbar=0,status=0,resizable=1,width=' + width + ',height=' + height + ',left=', (screen.width - width) / 2, ',top=', (screen.height - height) / 2].join('');
}

function setPhLabel(msgid, inputid) {
    $("#" + msgid).click(function () {
        $("#" + inputid).focus();
    });
    $("#" + inputid).bind("propertychange change input", function () {
        if ($.trim(this.value) != "") {
            $("#" + msgid).hide();
        } else {
            $("#" + msgid).show();
        }
    });
}