//email
function checkEmail(email) {
    return (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(email));
}

function checkCNPhone(phone) {
    return ((/^1[3|4|5|8][0-9]\d{8}$/.test(phone)));
}

function checkAllPhone(phone) {
    return ((/^[0-9+-]+$/g.test(phone)));
}

//is cn character
function checkCNCharacter(val) {
    return (/^[\u0391-\uFFE5]+$/.test(val));
}

function checkage(age) {
    return age >= 1 && age <= 99;
}
//is 字母和数字
function checkAZNum(str)
{
    var jgpattern = /^[A-Za-z0-9]+$/;
    return jgpattern.test(str);
}
//if contions  return true
function checkEmpty(str) {
    return /\s/.test(str);
}

function checkEmailA(value)
{
    if (value.indexOf("@") >= 0) return true;
    return false;
}
function checkEmailB(value)
{
    var reg = new RegExp("@", "g")
    var c = value.match(reg);

    if (c.length > 1) return false;

    var pattern = new RegExp("[ ,\\`,\\~,\\!,\#,\\$,\\%,\\^,\\+,\\*,\\&,\\\\,\\/,\\?,\\|,\\:,\\<,\\>,\\{,\\},\\(,\\),\\',\\;,\\=,\"]");
    //[`~!@#$^&*()=|{}':;',\\[\\]<>/?~！@#￥……&*（）&;|{}【】‘；：”“'。，、？]

    return !pattern.test(value);
}
function checkEmailC(value) {
    return !(/.*[\u4e00-\u9fa5]+.*$/.test(value));
}
function checkEmailD(value) {
    var arr = value.split("@");
    if ($.trim(arr[0]) == "") return false;

    return true;
}
function checkEmailE(value) {
    var arr = value.split("@");
    if ($.trim(arr[1]) == "") return false;

    return true;
}
function checkEmailF(value) {
    if (value.indexOf(".") > 0) return true;
    return false;
}