/**
 * 网站相关的常量。
 */
var Web = function() {
	/**
	 * 获得网站的上下文路径
	 */
	var getContextPath = function() {
		var contextPath = $("#_contextPath").val();
		if (contextPath === "/") {
			contextPath = "";
		}
		return contextPath;
	};
	
	return {
		/**
		 * 网站的上下文路径
		 */
		contextPath : getContextPath()
	};
}();
