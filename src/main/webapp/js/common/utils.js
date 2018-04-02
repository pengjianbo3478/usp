/**
 * @author alvin.zengqi
 * @description 常用方法
 */
var Utils = {
	
	ajax: function(url,param,successCB,errorCB,postType,dataType,cacheType,beforeText,processData,timeout,async){
		postType = (!postType) ? "POST" : postType;
	    cacheType = (cacheType == null) ? false:cacheType;
	    dataType = (dataType == null)? "json":dataType;
	    processData  = (processData ==null )? true:processData;
	    async = (async == null)?true:async;
	    timeout = (timeout == null) ? 180*1000 : timeout; 
	    $.ajax( {             
	        url : url+'?randomCode='+(Math.random()),
	        data : param,
	        cache : cacheType,
	        type : postType,
	        dataType : dataType,
	        processData : processData ,
	        async : async,
	        timeout : timeout,
	        success : function(data){
	    		if(successCB){
	    			successCB(data);
	    		}
	    	},
	        error : function(errorObj){
	    		if(errorCB){
	    			errorCB(errorObj);
	    		}else{
	    			alert("请求数据失败，请登陆后重试");
	    		}
	    	},
	    	beforeSend : function(jqXHR, settings){
	    		if(beforeText && typeof beforeText==="function"){
	    			beforeText(jqXHR, settings);
	    		}
	    	}
	    });
	},
	
	/**
	 * 滚动分页
	  Utils.scrollPagination({
	  	url: 'http://www.*.com/*.do',
	  	data: params
	  });
	 */
	scrollPagination: function(options){
		//默认参数配置，可自定义
		var defaults = {
			event: 'scroll', 
			container: $('.scrollContainer') || $('#scrollContainer'), //滚动容器
			content: $('.scrollContent') || $('#scrollContent'), //内容区
			url: null, //分页数据请求地址
			data: {}, //分页条件参数
			type: 'POST', 
			dataType: 'html',
			callback: null,
			offset: 0 // 滚动区域偏移量：正负值
		};
		defaults = $.extend(defaults, options);
		defaults.container.scrollTop(0).off(defaults.event).on(defaults.event, function(){
			var s = $(this), isBottom = s.scrollTop() == defaults.content.height() - s.height() + defaults.offset;
			if(isBottom){
				if(typeof defaults.data === 'string'){
					var p = Utils.queryStrings(defaults.data); 
					defaults.data = {};
					for(k in p){
						var c = p[k].split('=');
						defaults.data[c[0]] = c[1]; 
					}
				}
				Utils.ajax(defaults.url, defaults.data, function(data){
					if($.trim(data).length == 0){
						s.off(defaults.event)
					}else{
						defaults.content.append(data);
						s.scrollTop(s.scrollTop() - s.height() / 2);
						defaults.callback && defaults.callback(data);
						defaults.data['currentPageNum'] && (defaults.data['currentPageNum'] = parseInt(defaults.data['currentPageNum']) + 1);
					}
				}, null, defaults.type, defaults.dataType, false);
			}
		});
	},
	
	/**
	 * dest : 装载分页容器 $对象
	 * pageCount : 页总记录数
	 * pageNo : 当前页数  第一页下标为 0 , 以此类推
	 * cb : 分页完成后回调函数
	 * pageBar : TRUE OR FLASE 是否需要指定跳转功能
	 * preNum : 中心页前显示页数
	 * allNum ：总显示页数
	 */
	paginationPage : function(dest , pageCount , pageNo , cb ,pageBar, preNum , allNum){
		if(Utils.paginationSys){
			if(!dest){
				alert("请设置分页装载容器！");
				return false;
			}
			
			if(isNaN(pageCount)){
				alert("请设置分页总数！");
				return false;
			}
			
			if(isNaN(pageNo)){
				alert("请设置当前页数！");
				return false;
			}
			
			if(!cb){
				alert("请设置翻页回调函数！");
				return false;
			}
			if(!pageBar){
				pageBar = false;
			}
			Utils.paginationSys.turnPage(dest , pageCount , pageNo , cb , pageBar, preNum , allNum);
		}
	},
	
	base64encode : function(str){
		return Utils.base64.encode(str);
	},
	base64decode : function(str){
		return Utils.base64.decode(str);
	},
	exist$obj: function(obj){
		return $(obj).length > 0;
	},
	/**
	 * 取出相应的request过来的值,val是设置值的名称
	 */
	queryString : function(val) {
		var uri = window.location.search;
		var re = new RegExp("" + val + "\=([^\&\?]*)", "ig");
		return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1)) : null);
	},
	/**
	 * 获取全部参数，并返回数组
	 */
	queryStrings : function(search) {
		var uri = search || window.location.search;
		var re = /\w*\=([^\&\?]*)/ig;
		var retval = [];
		while ((arr = re.exec(uri)) != null)
			retval.push(arr[0]);
		return retval;
	},
	/**
	 * 如果当前地址栏有此参数，那么将更新此参数，否则返回一个新的地址栏参数字符串。
	 */
	setQuery : function(val1, val2) {
		var a = this.QueryStrings();
		var retval = "";
		var seted = false;
		var re = new RegExp("^" + val1 + "\=([^\&\?]*)$", "ig");
		for ( var i = 0; i < a.length; i++) {
			if (re.test(a[i])) {
				seted = true;
				a[i] = val1 + "=" + val2;
			}
		}
		retval = a.join("&");
		return "?" + retval
				+ (seted ? "" : (retval ? "&" : "") + val1 + "=" + val2);
	},
	/**
	 * 截取字符串
	 */
	interceptString : function(str, length) {
		if (str) {
			if (str.length > length) {
				str = str.substr(0, length) + "...";
			}
			return str;
		}
		return "";
	},
	/**
	 * 截取字符串（按字节）
	 * str ： 原字符串
	 * length : 指定字节长度
	 */
	subStrByCharCode : function(str, length){
		var text = Utils.getStrByCharCode(str),
			retText = text.substring(0,length).replace(/([\u0391-\uffe5])a/ig,'$1');
        if(text.length > length){
            retText += '...';
        }
        return retText;
    },
    
    /**
     * 获取字符串(按字节)
     */
    getStrByCharCode : function(str){
    	var retText = '';
    	if(str && $.trim(str).length > 0){
            retText = str.replace(/([\u0391-\uffe5])/ig,'$1a');
    	}
    	return retText;
    },
    
    /**
	 * 计算字符串长度,中文按2计算
	 */
	getBytesLength : function(str) {
		var len = 0;
		if(str){
			len = str.replace(/[^\x00-\xff]/g,"xx").length;
		}
		return len;
	},
    
    /**
     * 获取字符串长度(按字节)
     */
    getLengthByCharCode : function(str){
    	return Utils.getStrByCharCode(str).length;
    },
    
    /*
     * HTMLEncode方法编码
     */
    HTMLEnCode: function(str){
    	var s = "";
    	if (!str){
    		return "";
    	}
    	s = str.replace(/&/g, "&amp;");
    	s = s.replace(/</g, "&lt;");
    	s = s.replace(/>/g, "&gt;");
    	s = s.replace(/  /g, "&nbsp;");
    	s = s.replace(/\'/g, "&#39;");
    	s = s.replace(/\"/g, "&quot;");
    	s = s.replace(/\n/g, "<br>");
    	return s;
    },

    /*
     * HTMLDeCode方法解码
     */
    HTMLDeCode: function(str){
    	var s = "";
    	if (!str){
    		return "";
    	}
    	s = str.replace(/&amp;/g, "&");
    	s = s.replace(/&lt;/g, "<");
    	s = s.replace(/&gt;/g, ">");
    	s = s.replace(/&nbsp;/g, "  ");
    	s = s.replace(/&#39;/g, "\'");
    	s = s.replace(/&quot;/g, "\"");
    	s = s.replace(/<br>/g, "\n");
    	return s;
    },
	/**
	 * Utils.alert('内容', 'succeed', 1000);
	 */
	alert: function(content, type, time){
		var icon = {
			'succeed': 'yes',
			's': 'yes',
			'failed': 'hit',
			'f': 'hit'
		};
		type = icon[type] || icon['succeed'];
		var b = $('#alert_plugins');
		if(!Utils.exist$obj(b)){
			var a = [];
			a.push('<div class="submitted-show" id="alert_plugins" style="left:450px;">');
			a.push('	<p><img src="/ds/images/icon-' + type + '.png" alt=""><span id="alert_content"></span></p>');
			a.push('</div>');
			b = $(a.join(''));
			$('body').append(b);
		}else{
			b.html('	<p><img src="/ds/images/icon-' + type + '.png" alt=""><span id="alert_content"></span></p>');
		}
		b.find('#alert_content').html(content);
		b.show();
		setTimeout(function(){
			b.fadeOut(500);
		}, time || 1000);
	},
	prompt: function(title, content, yesFun, noFun, yesBtn, noBtn){
		var b = $('#prompt_plugins');
		if(!Utils.exist$obj(b)){
			var a = [];
			a.push('<div class="sigh-m-layer-show" id="prompt_plugins">')
			a.push('   <h2 class="alert-h2" id="prompt_title">' + (title || '提示') + '</h2>')
			a.push('   <div class="student-m-layer-close"><a href="#"><img src="/ds/images/bg-dialog-x.png" id="prompt_close"></a></div>')
			a.push('   <div class="m-layer-content"><img src="/ds/images/icon-sigh.png"><span id="prompt_content"></span></div>')
			a.push('   <button class="student-m-btn-1 ' + (yesBtn && yesBtn.length>4 ? 'm-btn':'m-btn') + '" id="prompt_continue">' + (yesBtn||'确 定') + '</button>')
			a.push('   <button class="student-m-btn-2 ' + (noBtn && noBtn.length>4 ? 'm-btn':'m-btn') + '" id="prompt_cancel">' + (noBtn||'取 消') + '</button>')
			a.push('</div>')
			b = $(a.join(''));
			$('body').append(b);
		}
		b.find('#prompt_cancel').unbind('click').bind('click', function(){
			if(noFun)noFun();
			b.hide();
		});
		b.find('#prompt_continue').unbind('click').bind('click', function(){
			if(yesFun)yesFun();
			b.hide();
		});
		b.find('#prompt_close').unbind('click').bind('click', function(){
			b.hide();
		});
		b.find('#prompt_title').html((title || '提示'));
		b.find('#prompt_content').html(content);
		b.find('#prompt_continue').html((yesBtn||'确 定'));
		b.find('#prompt_cancel').html((noBtn||'取 消'));
		b.show();
	},
	alertDialog : function(msg, title){
		var b = $('#alertDialog_plugins');
		if(!Utils.exist$obj(b)){
			var a = [];
			a.push('<div id="alertDialog_plugins">')
			a.push('	<p>'+msg+'</p>');
			a.push('</div>')
			b = $(a.join(''));
			$('body').append(b);
		}
		b.find("p").text(msg);
		b.dialog({
			title : title || '提示',
			buttons: {
				 "确定": function() {
				 $( this ).dialog( "close" );
				 }
			}
		});
	},
	imgPreview : function(src,title,width){
		var b = $("#img_preview_plugins");
		if(!Utils.exist$obj(b)){
			var a = [];
			a.push('<div id="img_preview_plugins">');
			a.push('	<img width="99%" height="99%">');
			a.push('</div>');
			b = $(a.join(''));
			$("body").append(b);
		}
		b.find("img").attr("src",src);
		b.dialog({
			title : title || '图片预览',
			width : width || '60%'
		});
	}
};
/**
 * 本地存储
 */
OB.Storage = {
	is: function() {
	    return !! window.localStorage
	},
	set: function(a, d, b) {
	    if (!FT.Storage.is()) {
	        if (typeof(d) != "object") {
	        	FT.Cookie.set(a, d)
	        }
	        return
	    }
	    var c = (b ? "session": "local") + "Storage";
	    switch (jQuery.type(d)) {
	    case "object":
	        d = "object:" + JSON.stringify(d);
	        break;
	    case "string":
	        d = "string:" + d;
	        break
	    }
	    try {
	        window[c].setItem(a, d)
	    } catch(f) {
	        
	    }
	},
	get: function(a, b) {
	    if (!FT.Storage.is()) {
	        return FT.Cookie.get(a)
	    }
	    var c = (b ? "session": "local") + "Storage";
	    var d = window[c].getItem(a);
	    if (/^object:/.test(d)) {
	        d = JSON.parse(d.replace(/^object\:/, ""))
	    } else {
	        if (/^string:/.test(d)) {
	            d = d.replace(/^string\:/, "")
	        }
	    }
	    return d
	},
	remove: function(a, b) {
	    if (!FT.Storage.is()) {
	    	FT.Cookie.del(a);
	        return;
	    }
	    var c = (b ? "session": "local") + "Storage";
	    window[c].removeItem(a)
	}
};
/**
 * cookie存储
 */
OB.Cookie = {
	set: function(d, f, e, g, a) {
	    if (a) {
	        var c = new Date();
	        var b = new Date();
	        b.setTime(parseFloat(c.getTime()) + 3600000 * a)
	    }
	    document.cookie = d + "=" + f + "; " + (a ? ("expires=" + b.toUTCString() + "; ") : "") + ("path=" + (g || "/") + "; domain=" + (e || "ds.ftiao.cn") + ";");
	},
	get: function(b) {
	    var c = new RegExp("(?:^|;+|\\s+)" + b + "=([^;]*)");
	    var a = document.cookie.match(c);
	    return (!a ? "": a[1])
	},
	del: function(a, b, c) {
	    if (b === null) {
	        document.cookie = a + "=; expires=Mon, 2 Mar 2009 19:00:00 UTC; path=" + (c || "/")
	    } else {
	        document.cookie = a + "=; expires=Mon, 2 Mar 2009 19:00:00 UTC; path=" + (c || "/") + "; domain=" + (b || "ds.ftiao.cn") + ";"
	    }
	}
};

/**
 * DOM操作
 */
OB.DOM = {
	cache: {},
	set: function(s){
		FT.DOM.del(s) && (FT.DOM.cache[s] = $(s));
		return FT.DOM.cache[s];
	},
	get: function(s, n){
		return (n && FT.DOM.set(s)) || FT.DOM.cache[s] || FT.DOM.set(s);
	},
	del: function(s){
		if(FT.DOM.cache[s]){
			delete FT.DOM.cache[s];
		}
		return s;
	}
};