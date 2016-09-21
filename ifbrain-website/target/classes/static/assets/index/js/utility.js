function wrapUrl(url) {
    if (url) {
        url = $.trim(url);
    }

    var result = url;
    var host = location.host;
    var junior = "junior";


    var path = host + "/" + junior;

    if (location.href.indexOf(path) >= 0) {
        if (url && url.indexOf("/") == 0) {
            result = "/junior" + url;
        }
        else {
            result = "/junior/" + url;
        }
    }

    return result;
}