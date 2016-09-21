/*!VIPABC | Author by Mike Li*/
/*!v1.1 | 2015-1-12*/
/*!License: vipabc.com*/


// start 寮瑰嚭鎸囧畾澶у皬鍦ㄧ嚎瀹㈡湇绐楀彛
function OpenModalDialog(objhtmltag) {
    
    //var client_sn = getCookie('client%5Fsn');
    var w = 650;
    var h = 570;
    var left = (screen.width / 2) - (w / 2) - 20;
    var top = (screen.height / 2) - (h / 2) - 60;
	var isSuccess = false;
	var memberTypeTemp;
	var onlineServervicePath = "http://junior.vipabc.com/red.html";
	if ((typeof objhtmltag.href != "undefined") && (objhtmltag.href != "")) {
	    onlineServervicePath = objhtmltag.href;
	}
	targetWin = window.open(onlineServervicePath, 'newwin', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
	return;
	$.ajax({
		type: "POST",
		cache: false,
		async: false,
		url: "http://www.vipabc.com/ajax_getOnlineserviceURLstatus.asp",
		/*data: {
		"client_sn": client_sn
		},*/
		success: function (memberType) {
			memberTypeTemp = memberType;
			isSuccess = true;
			//var targetWin = window.open(onlineServervicePath + '?clientSn=' + client_sn + '&memberType=' + memberType, 'newwin', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
		},
		error: function () {
			isSuccess = false;
			//var targetWin = window.open(onlineServervicePath, 'newwin', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
		}
	});

    if ((typeof objhtmltag.href != "undefined")&&(objhtmltag.href!="")) {
        onlineServervicePath = objhtmltag.href;
    }
	if (isSuccess) {
	    targetWin = window.open(onlineServervicePath + '?clientSn=' + client_sn + '&memberType=' + memberTypeTemp, 'newwin', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
	} else {
	    targetWin = window.open(onlineServervicePath, 'newwin', 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
	}
	//$(".online-service").hide();
    //targetWin.focus();
	
}