// jQuery Validation 的方法扩展
$.validator.addMethod("lt", function(value, element, param) {
	// bind to the blur event of the target in order to revalidate whenever the target field is updated
	// TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
	var target = $(param);
	if (this.settings.onfocusout) {
		target.unbind(".validate-lt").bind("blur.validate-lt", function() {
			$(element).valid();
		});
	}
	return this.optional(element) || value < target.val();
}, "应小于");

$.validator.addMethod("le", function(value, element, param) {
	// bind to the blur event of the target in order to revalidate whenever the target field is updated
	// TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
	var target = $(param);
	if (this.settings.onfocusout) {
		target.unbind(".validate-le").bind("blur.validate-le", function() {
			$(element).valid();
		});
	}
	return this.optional(element) || value <= target.val();
}, "应小于等于");

$.validator.addMethod("gt", function(value, element, param) {
	// bind to the blur event of the target in order to revalidate whenever the target field is updated
	// TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
	var target = $(param);
	if (this.settings.onfocusout) {
		target.unbind(".validate-gt").bind("blur.validate-gt", function() {
			$(element).valid();
		});
	}
	return this.optional(element) || value > target.val();
}, "应大于");

$.validator.addMethod("ge", function(value, element, param) {
	// bind to the blur event of the target in order to revalidate whenever the target field is updated
	// TODO find a way to bind the event just once, avoiding the unbind-rebind overhead
	var target = $(param);
	if (this.settings.onfocusout) {
		target.unbind(".validate-ge").bind("blur.validate-ge", function() {
			$(element).valid();
		});
	}
	return this.optional(element) || value >= target.val();
}, "应大于等于");
