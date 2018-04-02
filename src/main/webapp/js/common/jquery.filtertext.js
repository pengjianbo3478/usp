/**
 * @Title: jquery.filtertext.js
 * @Description: 文本框过滤插件（可扩展）,Example:$("input").filterNumber();
 * @author Alvin.zengqi
 * @date 2010-6-4 下午12:07:33
 * @version V1.0
 * @Copyright Copyright (c) 2011
 */
(function($) {
	
	$.extend({
		
		filterText : new function(){
			this.defaults = {
				exp	: /[^\*]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterText.defaults, settings);
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
						   .bind("keydown",function(){$.inputfilter(this, config);})
						   .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},
		
		//只能输数字（包含换行，空格） 
		filterNumber : new function(){
			this.defaults = {
				exp	: /[^\d\r\n]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterNumber.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					/*if($.browser.msie && parseInt($.browser.version) < 6){
						$(this).bind("propertychange",function(){$.inputfilter(this, config);});
					}else{
						$(this).bind("keyup",function(){$.inputfilter(this, config);})
							   .bind("keydown",function(){$.inputfilter(this, config);});
					}*/
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
					       .bind("keydown",function(){$.inputfilter(this, config);})
					       .bind("input",function(){$.inputfilter(this, config);});
				});
			};
			
			
		},
		
		//只能输字母（包含换行，空格） 
		filterLetter : new function(){
			this.defaults = {
				exp	: /[^\w]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterLetter.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
						   .bind("keydown",function(){$.inputfilter(this, config);})
						   .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},

		
		//过滤特殊字符（包含换行，空格）
		filterSpecialCharacter : new function(){
			this.defaults = {
				exp	: /[^\u4E00-\u9EA5\w\d\r\n]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterSpecialCharacter.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
					   	   .bind("keydown",function(){$.inputfilter(this, config);})
					   	   .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},
		
		//过滤邮箱（包含换行，空格）
		filterEmail : new function(){
			this.defaults = {
				exp	: /[^\d\w\.\@\r\n]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterEmail.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
					       .bind("keydown",function(){$.inputfilter(this, config);})
					       .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},
		
		//只能输汉字(包含空格) 
		filterCN : new function(){
			this.defaults = {
				exp	: /[^\u4E00-\u9FA5\uF900-\uFA2D\r\n]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterCN.defaults, settings);
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
						   .bind("keydown",function(){$.inputfilter(this, config);})
						   .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},

		//只能输数字跟字母（包含换行，空格） 
		filterNumberLetter : new function(){
			this.defaults = {
				exp	: /[^\d\w\r\n]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterNumberLetter.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
					       .bind("keydown",function(){$.inputfilter(this, config);})
					       .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},
		
		//只能输数字和.（包含换行，空格） IP
		filterNumberPoint : new function(){
			this.defaults = {
				exp	: /[^\d\r\n\.\;]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterNumberPoint.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
					       .bind("keydown",function(){$.inputfilter(this, config);})
					       .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},
		 
		//过滤电话号码
		filterPhone : new function(){
			this.defaults = {
				exp	: /[^\d\r\n\-]/ig //默认表达式
			};
			this.construct = function(settings){
				return this.each(function(){
					var config;
					config = $.extend($.filterPhone.defaults, settings);
					$(this).css({"ime-mode":"Disabled"});
					$(this).bind("keyup",function(){$.inputfilter(this, config);})
					       .bind("keydown",function(){$.inputfilter(this, config);})
					       .bind("input",function(){$.inputfilter(this, config);});
				});
			};
		},
		
		inputfilter : function(obj, config){
			if(config.exp.test(obj.value))obj.value=obj.value.replace(config.exp,'');
		},
		
		/**
		 * 解绑过滤
		 */
		unfilter : new function(){
			this.construct = function(settings){
				return this.each(function(){
					$(this).css({"ime-mode":""});
					$(this).unbind("propertychange").unbind("keyup").unbind("keydown").unbind("input");
				});
			};
		}
		
	});
	
	$.fn.extend({
		//过滤文本（自定义）
		filterText : $.filterText.construct,
		//只能输数字（包含换行，空格）
		filterNumber : $.filterNumber.construct,
		//只能输字母（包含换行，空格） 
		filterLetter : $.filterLetter.construct,
		//过滤特殊字符（包含换行，空格）
		filterSpecialCharacter : $.filterSpecialCharacter.construct,
		//过滤邮箱（包含换行，空格）
		filterEmail : $.filterEmail.construct,
		//只能输汉字(包含空格) 
		filterCN : $.filterCN.construct,
		//只能输数字跟字母（包含换行，空格） 
		filterNumberLetter : $.filterNumberLetter.construct,
		//只能输数字和.（包含换行，空格） IP
		filterNumberPoint : $.filterNumberPoint.construct,
		//过滤电话号码
		filterPhone : $.filterPhone.construct,
		//解绑过滤
		unfilter : $.unfilter.construct
	});
	
})(jQuery);