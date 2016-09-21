
/*!VIPABC | Author by Mike Li*/
/*!v1.0 | 2015-2-27*/
/*!License: vipabc.com*/

$(document).ready(function () {

    /*--------------------  鑷畾涔夊崟閫夋寜閽� --------------------*/
    //var $radio_sex = $('input[name="sex"]'); //閫夊彇 name = sex 鐨勫崟閫夋寜閽粍
    //radio_define($('input[name="sex"]'));

    function radio_define($radio_sex) {
        $radio_sex.wrap('<span class="radio_wrap"></span>'); //灏嗛€夊彇鐨勫崟閫夋寜閽寘瑁筪iv
        $radio_sex.filter(":radio[checked]").parent().addClass("radio_on_wrap"); //榛樿閫変腑鐨勬寜閽坊鍔犳柊鐨刢lass="radio_on_wrap"

        $radio_sex.click(function () {

            $radio_sex.removeAttr("checked");
            $(this).prop("checked", "true");

            $radio_sex.parent(".radio_wrap").removeClass("radio_on_wrap");
            $(this).parent(".radio_wrap").addClass("radio_on_wrap");
        });
    }

    /*--------------------  鑷畾涔夊崟閫夋寜閽� end --------------------*/


    /*--------------------  鑷畾涔変笅鎷夎彍鍗� --------------------*/
    selectric($(".getWhere"));
    selectric($(".selectric-sex"));
    selectric($(".selectric-age"));
    selectric($(".age_area"));
    selectric($(".age_area_sidebar"));
    selectric($(".selectrics-sidebar"));
    selectric($(".queation_area"));


    //鍒涘缓wrap
    function selectric($select_age) {

        if ($select_age.parent(".select_wrap").length > 0) {
            return false;
        }

        $select_age.wrap('<span class="select_wrap"></span>');
        var $parent = $select_age.parent(".select_wrap");
        $('<span class="select_current"></span>' + '<span class="select_list"><dl></dl></span>' + '<span class="select_btn"></span>').insertBefore($select_age);

        var $list = $parent.find(".select_list");

        if ($parent.find("select optgroup").length != 0) {
            $parent.find("select optgroup").each(function () {
                $list.find("dl").append('<dt>' + $(this).attr("label") + '</dt>');

                $(this).find("option").each(function () {
                    $list.find("dl").append('<dd>' + $(this).text() + '</dd>');
                });

            });
        } else {
            $parent.find("option").each(function () {
                $list.find("dl").append('<dd>' + $(this).text() + '</dd>');
            });
        }




        var $list_li = $list.find("dl dd");
        var $current = $parent.find(".select_current");
        var $btn = $parent.find(".select_btn");
        var $cur_btn = $parent.find(".select_current, .select_btn");

        var $selected = $parent.find("select option:selected");
        var _index = $selected.index();

        $current.text($selected.text());

        var cd_Value = $selected.text();


        $cur_btn.on("click", function (e) {

            if (!$select_age.hasClass("selectrics-sidebar3")) {
                $(".getWhere-wrap .select_wrap").css("z-index", "188");
            } else {
                $(".getWhere-wrap .select_wrap").css("z-index", "200");
            }


            e.stopPropagation();
            var $parent = $(this).parent(".select_wrap");
            var $list = $parent.find(".select_list");
            var $btn = $parent.find(".select_btn");
            var $current = $parent.find(".select_current");
            $current.css("border", "");
            var bh = $(window).height() - $parent.offset().top;
            //console.log(bh);

            var h = $list.height();
            var _top = $current.height();

            if (h >= 350) {
                h = -350;
            }
            else {
                h = -h;
            }
            if ($parent.offset().top - $(window).scrollTop() < 350) {
                $list.css({
                    "top": _top
                });
            }
            else {
                $list.css({
                    "top": h
                });
            }

            $list.toggle();
            $btn.toggleClass("select_btn_on");

        });

        //for PC
        $list_li.each(function (_index) {

            $(this).on("click", function (e) {
                e.stopPropagation();
                var li_index = _index;
                var $parent = $(this).parents(".select_wrap");
                var $current = $parent.find(".select_current");
                var $list = $parent.find(".select_list");
                var $btn = $parent.find(".select_btn");

                $current.text($(this).text()).css("border", "");
                $(this).addClass("selected").siblings().removeClass("selected");

                $list.removeAttr("style");
                $btn.removeClass("select_btn_on");
                if ($select_age.hasClass("selectric-age")) {
                    $parent.find("select").get(0).selectedIndex = li_index + 1;
                } else {
                    $parent.find("select").get(0).selectedIndex = li_index;
                }

                //console.log($parent.find("select").get(0).selectedIndex);

                $parent.find(".msg-box").empty();

                //console.log($(this).text());    
                if ($(this).text() != cd_Value) {
                    $current.css("color", "#000");
                    $parent.css("border-color", "#6ac451");
                }
                else {
                    $current.removeAttr("style");
                    $parent.css("border-color", "#f65662");
                }

            });

        });


        if (navigator.userAgent.match(/mobile/i)) {

            //$("p").show();
            $list_li.unbind("click").hide();
            $cur_btn.unbind("click");
            $parent.find("select").show().animate({
                "opacity": 0,
                "z-index": 300
            }).change(function () {
                var sel_val = $(this).find("option:selected").text();
                $current.text(sel_val);

                if (sel_val != cd_Value) {
                    $current.css("color", "#000");
                }
                else {
                    $current.removeAttr("style");
                }

            });

        }

        $select_age.find("option:selected").val("");  //娓呯┖鍘熺敓select鐨勭涓€涓€変腑鐨勯」




        $(document).click(function (e) {
            //e.preventDefault();
            if ($list.is(":visible")) {
                $list.hide();
                $btn.removeClass("select_btn_on");
            }
        });


    }



    /*--------------------  鑷畾涔変笅鎷夎彍鍗� end --------------------*/






});