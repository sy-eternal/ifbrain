$(document).ready(function () {

        //$(window).scroll(function () {
        //    ScrollFunc();
        //});
        $(".showMore").click(function (e) {
            var allshowed = $("#allshowed").val();
            if (allshowed != "true") {
                var page = $("#page").val();
                var categoryID = $("#categoryID").val();

                if ($("#PageNow").length > 0) {
                    page = $("#PageNow").val();
                }

                $.ajax({
                    type: 'POST',
                    async: true,
                    url: '/baike/ShowMore' + '?page=' + page + '&categoryID=' + categoryID,
                    dataType: "html",
                    mode: 'block',
                    beforeSend: function () {
                        LodingDiv();
                    },
                    success: function (data) {

                        if (data.indexOf('END') > 0) {
                            $(".showMore").html("回顶部");

                            $('.showMore').unbind("click");

                            $(".showMore").addClass("baikeGoTop");

                            /*----------------- 置顶功能 -----------------*/
                            $(".baikeGoTop").on('click', function () {
                                fixedAll();
                                $("body,html").animate({
                                    scrollTop: 0
                                },
                                500); //点击回到顶部按钮，缓懂回到顶部,数字越小越快
                                return false;
                            });
                        }
                        else {
                            $(".showMore").html("点击加载更多内容");
                        }

                        $("#blogList").append(data);
                        var num = $("#PageNow").val();
                        num++;
                        $("#PageNow").val(num);

                        $("#waite").remove();
                    }

                });
            }

        });

        //异步加载点赞数目

        $.ajax({
            type: 'POST',
            url: '/baike/GetLoveCount',
            dataType: "json",
            data: { "loveCountList": JSON.stringify([{"sn":304,"count":48},{"sn":303,"count":10},{"sn":302,"count":30},{"sn":301,"count":19},{"sn":300,"count":12},{"sn":299,"count":79},{"sn":4,"count":464},{"sn":192,"count":682},{"sn":191,"count":615},{"sn":3,"count":265},{"sn":5,"count":231},{"sn":1,"count":280}]) },
            success: function (data) {
                if (data.status == "OK")
                {
                    var list = JSON.parse(data.result);
                    for (var i in list)
                    {
                        var item = list[i];
                        $(".like" + item.sn).each(function () {
                            $(this).find("i").text(item.count);
                            $(this).find("i").show();
                        });
                    }
                }
                else
                { }
            }

        });
        
        
        
        
        $(".number-only").keydown(function (e) {
            // temp disable as it is not working in IE with currvet verion of jQuery.
            //                    if ($.inArray(e.which, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            //                    // Allow: Ctrl+A
            //                (e.which == 65 && e.ctrlKey === true) ||
            //                    // Allow: home, end, left, right, down, up
            //                (e.which >= 35 && e.which <= 40)) {
            //                        // let it happen, don't do anything
            //                        return;
            //                    }
            //                    // Ensure that it is a number and stop the keypress
            //                    if ((e.shiftKey || (e.which < 48 || e.which > 57)) && (e.which < 96 || e.which > 105)) {
            //                        e.preventDefault();
            //                    }

        });

        // set default
        $(".write-to-us-container .validate").each(function (i, o) {
            $(o).data("default", $(o).val());
        });

        $(".write-to-us-container .free-try-submit").click(function () {
            var root = $(this).closest(".write-to-us-container");
            // will wrap error in P tag.
            $(".meg-error", root).remove();

            var isValid = true;

            $(".validate", root).each(function (i, o) {
                var val = $.trim($(o).val());
                if (0 == val.length || val == $(o).data("default")) {
                    var container = $("<div>")
                    .appendTo($(o).closest("li"))
                    .addClass("meg-error")
                    .text($(o).data("err"));
                    isValid = isValid && false;
                }


            });

            if (isValid) {
                //开发环境
                var postUrl = "http://lp.vipabc.com/program/linkage_page/include/index_exe.asp"
                $.ajax({
                    url: postUrl,
                    type: "POST",
                    data: $(this).closest("form").serialize(),
                    context: $(this),
                    beforeSend: function () {
                        $(this).prop("disabled", true).val("提交中... 请稍后...");
                    },
                    success: function (data) {
                        location.href = wrapUrl("/Home/DemoSuccess");
                    },
                    error: function (XMLHttpRequest, textStatus, errorThrown) {
                        location.href = wrapUrl("/Home/DemoSuccess");
                    }
                });

            }
        });

    });
    var ScrollFunc = function () {

        var nowPosition = $(window).scrollTop();
        var divheight = $("#scrollHeight").height();

        if (nowPosition + 120 > divheight) {

            $(window).off("scroll");

            var allshowed = $("#allshowed").val();

            if (allshowed != "true") {
                var page = $("#page").val();
                var categoryID = $("#categoryID").val();

                if ($("#PageNow").length > 0) {
                    page = $("#PageNow").val();
                }

                $.ajax({
                    type: 'POST',
                    async: true,
                    url: '/baike/ShowMore' + '?page=' + page + '&categoryID=' + categoryID,
                    dataType: "html",
                    beforeSend: function () {
                        LodingDiv();
                    },
                    success: function (data) {

                        if (data.indexOf('END') > 0) {
                            $(".showMore").html("回顶部");

                            $('.showMore').unbind("click");

                            $(".showMore").addClass("baikeGoTop");

                            /*----------------- 置顶功能 -----------------*/
                            $(".baikeGoTop").on('click', function () {
                                fixedAll();
                                $("body,html").animate({
                                    scrollTop: 0
                                },
                                500); //点击回到顶部按钮，缓懂回到顶部,数字越小越快
                                return false;
                            });
                        }
                        else {
                            $(".showMore").html("点击加载更多内容");
                        }

                        $("#blogList").append(data);
                        var num = $("#PageNow").val();
                        num++;
                        $("#PageNow").val(num);

                        $("#waite").remove();

                        $(window).scroll(function (e) {
                            fixedAll();
                            ScrollFunc();

                        });
                    },
                    mode: 'block',
                    error: function (err) {
                    }
                });
            }
            else {
                $(window).scroll(function (e) {
                    fixedAll();
                    ScrollFunc();

                });
            }
        }
    }
    function LodingDiv() {

        $loadMore = $(".showMore");

        $loadMore.html("&nbsp;");
        $(".load-more").append("<div id='waite' style='position:absolute;z-index:1000;background:#000 url(http://source.vipabc.com/source/images/website/share/loading.gif) no-repeat center center;'>加载中...</div>");

        //css定位弹出层
        $("#waite").css({
            "width": "100%",
            "height": "100%",
            "top": 0,
            "left": 0,
            "color": "#ffffff",
            "line-height": "35px",
            "opacity": 0.5
        });


    }
