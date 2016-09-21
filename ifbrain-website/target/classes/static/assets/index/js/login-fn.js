/*!VIPABC | Author by Michael Han*/
/*!v1.0 | 2015-3-26*/
/*!License: vipabc.com*/


var loginPad = function (wrap, container) {
    this.wrap = wrap;
    this.container = container ? container : $(".login-pad");

    var me = this;

    $(".login", this.container).click(function () {
        if ($(this).data("jump-url")) {
            location.href = $(this).data("jump-url");
            return false;
        }

        if (me.wrap.onDisplaying) {
            me.wrap.onDisplaying();
        }
        var role = $(this).data("role");
        me.wrap.showWrap(role);
    });

    $(".register", this.container).click(function () {
        if (me.wrap.onDismissing) {
            me.wrap.onDismissing();
        }
        var role = $(this).data("role");
        me.wrap.showWrap(role);

    });


};

var loginWrap = function (container) {
    this.container = container ? container : $(".float-lay-bg, .login-register-wrap");



    this.login = $(".login", this.container);
    this.register = $(".register", this.container);

    this.flag = "";

    // instance-wide variable
    var me = this;

    // events
    this.onDisplaying = null;

    this.onDismissing = null;

    // function
    this.validateLogin = function () {
        var parent = $(".LR-login", me.container);
        var isValid = true;


        $("input", parent).each(function (i, o) {
            var val = $.trim($(o).val());

            switch ($(o).prop("type")) {
                case "email":
                    var mailReg = /^([a-zA-Z-z0-9_\.-]+)@([\da-zA-Z\.-]+)\.([a-zA-Z\.]{2,6})$/;
                    if (!mailReg.test(val)) {
                        $(('<span class="meg-error">璇疯緭鍏ユ纭殑鐢靛瓙閭</span>')).appendTo($(o).closest(".LR-wrap-input-wrap"));
                        isValid = false;
                    } else {

                        isValid = true && isValid;
                    }
                    break;
                case "password":

                    if (val.length < 6 || val.length > 14) {
                        $(('<span class="meg-error">璇疯緭鍏�6-14浣嶇殑瀵嗙爜</span>')).appendTo($(o).closest(".LR-wrap-input-wrap"));

                        isValid = false;
                    } else {

                        isValid = true && isValid;
                    }
                    break;
                default:
                    break;
            }


        });

        return isValid;
    };

    this.validateRegister = function () {

        var parent = $(".LR-register", me.container);
        var isValid = true;


        $("input", parent).each(function (i, o) {
            var val = $.trim($(o).val());

            switch ($(o).prop("type")) {
                case "email":
                    var mailReg = /^([a-zA-Z-z0-9_\.-]+)@([\da-zA-Z\.-]+)\.([a-zA-Z\.]{2,6})$/;
                    if (!mailReg.test(val)) {
                        $(('<span class="meg-error">璇疯緭鍏ユ纭殑鐢靛瓙閭</span>')).appendTo($(o).closest(".LR-wrap-input-wrap"));

                        isValid = false;
                    } else {

                        isValid = true && isValid;
                    }
                    break;
                case "password":

                    if ($(o).hasClass("password")) {
                        if (val.length < 6 || val.length > 14) {
                            $(('<span class="meg-error">璇疯緭鍏�6-14浣嶇殑瀵嗙爜</span>')).appendTo($(o).closest(".LR-wrap-input-wrap"));

                            isValid = false;
                        } else {

                            isValid = true && isValid;
                        }
                    }
                    else {

                        if (val != $.trim($(".password", parent).val()) || val.length == 0) {

                            $(('<span class="meg-error">瀵嗙爜杈撳叆涓嶄竴鑷�</span>')).appendTo($(o).closest(".LR-wrap-input-wrap"));
                            isValid = false;
                        } else {

                            isValid = true && isValid;
                        }
                    }

                    break;
                default:
                    break;
            }


        });

        return isValid;
    };

    this.showWrap = function (role) {

        if (this.onDisplaying) {
            this.onDisplaying();
        }

        this.container.show();

        this.show(role);
    };

    this.hideWrap = function () {
        this.clearError();

        if (this.onDismissing) {
            this.onDismissing();
        }
        this.container.fadeOut(500);
    };

    this.show = function (role) {
        var matchClass = "LR-" + role;

        // show content
        $(".LR-content", me.container).each(function (i, o) {
            if ($(o).hasClass(matchClass)) {
                $(o).addClass("LR-content-show");

            }
            else {
                $(o).removeClass("LR-content-show");
            }
        });

        // mark label in tab
        $(".LR-tab-title li", me.container).each(function (i, o) {
            var liRole = $(o).data("role");
            if (liRole == role) {
                $(o).addClass("LR-item-on");
            }
            else {
                $(o).removeClass("LR-item-on");
            }
        });

        $(".LR-tab-title .login-btn", me.container).val("鐧诲綍");
    };

    this.clearError = function () {
        // clear previous check result.
        $(".meg-error", me.container).each(function (i, o) {
            if ($(o).hasClass("LR-error")) {
                $(o).hide();
            }
            else {
                $(o).remove();
            }
        });
    };

    this.getLoginInfo = function () {
        return {
            //mail: $.trim($(".LR-login input.LR-wrap-input[type='email']", me.container).val()),

            mail: $.trim($(".login-email", me.container).val()),
            pwd: $.trim($(".LR-login input.LR-wrap-input[type='password']", me.container).val()),
            rememberMe: $.trim($(".LR-login input[type='checkbox']", me.container).is(":checked"))
        };
    };

    this.getRegisterInfo = function () {
        return {
            mail: $.trim($(".LR-register input.LR-wrap-input[type='email']", me.container).val()),
            pwd: $.trim($(".LR-register input.LR-wrap-input[type='password']", me.container).val())
        };
    };

    // events


    $(".login-btn", this.container).click(function () {

        me.clearError();

        if (me.validateLogin()) {
            var data = me.getLoginInfo();

            $.ajax({
                url: wrapUrl("/Login/Login"),
                method: "POST",
                context: $(this),
                data: {
                    Account: data.mail,
                    Password: data.pwd,
                    RememberMe: data.rememberMe,
                    FromIms: false
                },
                beforeSend: function () {
                    $(this).prop("disabled", true).val("鐧诲綍涓�... 璇风◢鍚庯紒");
                },
                success: function (json) {
                    var result = $.parseJSON(json);

                    if (result.State == "LOGIN_SUCCESS") {
                        // successfual login
                        // jump to member center;  
                        location.href = wrapUrl(result.PlainText);
                    }
                    else {
                        // display error
                        $(("<span class='meg-error'>" + result.PlainText + "</span>")).appendTo(".LR-login .error-panel", me.container);

                    }

                    $(this).prop("disabled", false).val("鐧诲綍");
                },
                always: function () {

                    $(this).prop("disabled", false).val("鐧诲綍");
                },
                fail: function (e) {
                    alert(e.responseText);
                }
            });

            return true;
        }
        else {
            return false;
        }
    });

    $(".register-btn", this.container).click(function () {


        me.clearError();

        if (me.validateRegister()) {
            $(this).closest("form").submit();
        }
        else {
            return false;
        }
    });

    $(".LR-wrap-close", this.container).click(function () {
        me.hideWrap();
    });

    $(".LR-tab-title li", this.container).click(function () {
        var role = $(this).data("role");

        if (role == "login") {
            if ($(this).data("jump-url")) {
                location.href = $(this).data("jump-url");
                return false;
            }
        }
        me.show(role);
    });

    /*----------------- 瀵嗙爜杈撳叆妗嗗垏鎹� --------------------*/
    var _pwd_value;
    $(".uptext", this.container).click(function () {
        var $pwd = $(this).parent().find(".hiddentext");
        $(this).hide();
        $pwd.show().focus();
        _pwd_value = $pwd.val();

    })
        .focus(function () {
            var $pwd = $(this).parent().find(".hiddentext");
            $(this).hide();
            $pwd.show().focus();
            _pwd_value = $pwd.val();
        });

    $(".hiddentext", this.container).blur(function () {
        if ($(this).val() == "") {
            $(this).hide();
            $(this).parent().find(".uptext").show();
        }

    });
};
