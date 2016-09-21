$(document.body).ready(function () {

    $(".number-only").keydown(function (e) {
        // temp disable as it is not working in IE with currvet verion of jQuery.


        //            if ($.inArray(e.which, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
        //            // Allow: Ctrl+A
        //            (e.which == 65 && e.ctrlKey === true) ||
        //            // Allow: home, end, left, right, down, up
        //            (e.which >= 35 && e.which <= 40)) {
        //                // let it happen, don't do anything
        //                return;
        //            }
        //            // Ensure that it is a number and stop the keypress
        //            if ((e.shiftKey || (e.which < 48 || e.which > 57)) && (e.which < 96 || e.which > 105)) {
        //                e.preventDefault();
        //            }

    });

    // set default
    $(".sidebar .validate").each(function (i, o) {
        $(o).data("default", $(o).val());
    });


    // validation
    $(".free-try").click(function () {
        // will wrap error in P tag.
        $(".sidebar .meg-error").remove();

        var isValid = true;

        $(".sidebar .validate").each(function (i, o) {
            isValid = true;
            var val = $.trim($(o).val());
            if (0 == val.length || val == $(o).data("default")) {
                /*var container = $("<span>")
                .appendTo($(o).closest("li"))
                .addClass("meg-error")
                .text($(o).data("err"));*/
                isValid = isValid && false;
            } else {

                if ($(o).attr("name") == "name") {
                    // check Chinese or English name
                    var temp1 = val.match(/^[\u4e00-\u9fa5]{2,8}$/i);
                    var temp2 = val.match(/^([A-Za-z]+\s?)*[A-Za-z]$/);
                    var temp3 = val.match(/^[\u4e00-\u9fa5A-Za-z]{2,20}$/i);
                    isValid = (temp1 || temp2 || temp3) && (val.length <= 20);

                } else if ($(o).attr("name") == "cphone") {
                    var reg = /^(?:13\d|14\d|15\d|17\d|18\d)\d{5}(\d{3}|\*{3})$/;
                    isValid = reg.test(val);

                } else if ($(o).attr("name") == "mail") {
                    var reg = /^([a-zA-Z-z0-9_\.-]+)@([\da-zA-Z\.-]+)\.([a-zA-Z\.]{2,6})$/;
                    isValid = reg.test(val);

                }


            }
            if (!isValid) {
                var container = $("<span>")
                        .appendTo($(o).closest("li"))
                        .addClass("meg-error")
                        .text($(o).data("err"));
            }


        });

        if (isValid) {
            //开发环境
            var postUrl = "http://www.vipabc.com/program/linkage_page/include/index_exe.asp"
            $.ajax({
                url: postUrl,
                type: "POST",
                crossDomain: true, 
                dataType:'jsonp',
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