/*
 * Translated default messages for the jQuery validation plugin.
 * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
 */
(function ($) {
	$.extend($.validator.messages, {
		required: "<span style='color:#F00'>必选字段<span>",
		remote: "<span style='color:#F00'>名称已存在<span>",
		email: "<span style='color:#F00'>请输入正确格式的电子邮件<span>",
		url: "<span style='color:#F00'>请输入合法的网址<span>",
		date: "<span style='color:#F00'>请输入合法的日期<span>",
		dateISO: "<span style='color:#F00'>请输入合法的日期 (ISO).<span>",
		number: "<span style='color:#F00'>请输入合法的数字<span>",
		digits: "<span style='color:#F00'>只能输入整数<span>",
		creditcard: "<span style='color:#F00'>请输入合法的信用卡号<span>",
		equalTo: "<span style='color:#F00'>请再次输入相同的值<span>",
		accept: "<span style='color:#F00'>请输入拥有合法后缀名的字符串<span>",
		maxlength: $.validator.format("<span style='color:#F00'>请输入一个长度最多是 {0} 的字符串<span>"),
		minlength: $.validator.format("<span style='color:#F00'>请输入一个长度最少是 {0} 的字符串<span>"),
		rangelength: $.validator.format("<span style='color:#F00'>请输入一个长度介于 {0} 和 {1} 之间的字符串<span>"),
		range: $.validator.format("<span style='color:#F00'>请输入一个介于 {0} 和 {1} 之间的值<span>"),
		max: $.validator.format("<span style='color:#F00'>请输入一个最大为 {0} 的值<span>"),
		min: $.validator.format("<span style='color:#F00'>请输入一个最小为 {0} 的值<span>"),
		mobile: $.validator.format("<span style='color:#F00'>请输入正确的手机号<span>"),
		idcard:$.validator.format("<span style='color:#F00'>请输入正确的身份证<span>")
	});
}(jQuery));