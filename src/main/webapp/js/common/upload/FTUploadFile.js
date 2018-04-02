/**
 * @author Alvin
 * @date 20150609
 * 文件上传插件
 * 示例如下：
	new FTUploadFile({
		flash_url : "FTUploadFile.swf",
		upload_url : "http://fs.ftiao.cn/upload.html", //上传接口路径
		config_url : "http://fs.ftiao.cn/config", //配置文件接口地址
		post_params : {},
		file_size_limit : 2*1024,
		file_types : "*.jpg;*.gif;*.png;*.jpeg",
		file_types_description : "图片(jpg,gif,png,jpeg)",
		debug : false,
		upload_button : "fileUploadButton", //上传按钮
		upload_panel_id : "upload_panel",
		upload_start_handler : function(s) {},
		upload_progress_handler : function() {},
		upload_complete_handler : function() {},
		upload_success_handler : function() {},
		upload_error_handler : function() {},
		mouseOver : function() {},
		mouseOut : function() {}
	});
 */

if(typeof FTUploadFile === 'undefined') {
	var FTUploadFile = function(_settings) {
		this.initSWF(_settings);
	};
}
FTUploadFile.version = "v1.0 20150609";
if(typeof FTUploadFile.instance === 'undefined'){
	FTUploadFile.instance = [];	
}
FTUploadFile.count = 1;
FTUploadFile.prototype = {
	/**
	 * 初始化swf，传入相应的参数
	 * @param {Object} _settings
	 */
	initSWF : function(_settings) {
		if(typeof SWFObject === 'undefined') {
			throw new Error("此组件需要SWFObject的支持,请检测文件已被加载");
		}
		
		try {
			this.movieName = "FTUploadFile_Instance" + (FTUploadFile.count++);
			this.settings = _settings;
			
			this.initSettings();
			this.initPosition();
			
			FTUploadFile.instance.push(this.flashCall());
			
			this.loadFlash();
		} catch(ex) {
			throw new Error("初始化上传组件失败,请检测");
		}
	},
	initSettings : function() {
		var extend = function(destination, source) {
			for (var property in source) {
				if(typeof(source[property]) === "object"){
					destination[property] = extend(destination[property], source[property] || {});
				}else{
					destination[property] = source[property];
				}
			}
			return destination;
		}
		
		this.settings = extend({
			flash_url : "FTUploadFile.swf", //上传控件
			upload_url : "http://fs.ftiao.cn/upload.html", //上传接口路径
			config_url : "http://fs.ftiao.cn/config", //配置文件接口地址
			post_params : {
				isTmp : true
			}, //请求参数
			file_size_limit : 5*1024, //最大允许上传的文件大小（字节为单位）（默认从服务器配置文件获取，页面可以不用配置）
			file_types : "*.*", //允许上传的文件类型（默认从服务器配置文件获取，页面可以不用配置）
			file_types_description : "All Files", //允许上传文件文字描述（默认从服务器配置文件获取，页面可以不用配置）
			embedWidth : 100, //上传控件宽度
			embedHeight : 40, //上传控件高度
			upload_button : "fileUploadButton", //上传按钮
			debug : false, 
			upload_panel_id : "",
			upload_start_handler : null,
			upload_progress_handler : null,
			upload_complete_handler : null,//文件上传服务器完成（服务器还未对文件进行处理）
			upload_success_handler : null,//上传成功，服务器已对文件进行处理
			upload_error_handler : null,//上传异常
			mouseOver : null, //上传控件鼠标移入事件（用于上传按钮的交互处理）
			mouseOut : null //上传控件鼠标移出事件（用于上传按钮的交互处理）
		},this.settings || {});
	},
	initPosition : function(){
		var opts = this.settings;
		var uploadPanel = this.getById(opts.upload_panel_id),
			uploadButton = this.getById(opts.upload_button);
		opts.embedWidth = uploadButton.clientWidth || opts.embedWidth;
		opts.embedHeight = uploadButton.clientHeight || opts.embedHeight;
		with(uploadPanel.style){
			position = "absolute";
			left = uploadButton.offsetLeft + 'px';
			top = uploadButton.offsetTop + 'px';
			width = uploadButton.clientWidth + 'px';
			height = uploadButton.clientHeight + 'px';
			zIndex = '100';
		}
	},
	getById : function(id){
		return typeof id === 'string' ? document.getElementById(id) : id;
	},
	
	buildParamString : function() {
		var postParams = this.settings.post_params,
			paramStringPairs = [];
		
		if (typeof(postParams) === "object") {
			for (var property in postParams) {
				if (postParams.hasOwnProperty(property)) {
					paramStringPairs.push(encodeURIComponent(property.toString()) + "=" + encodeURIComponent(postParams[property].toString()));
				}
			}
		}
		return paramStringPairs.join("&");
	},
	loadFlash : function() {
		if(!this.settings.upload_panel_id || !this.settings.upload_url) {
			throw new Error("缺少组件必要参数");
		}
		
		var paramString = this.buildParamString(),
			swf = new SWFObject(this.settings.flash_url, this.movieName, this.settings.embedWidth, this.settings.embedHeight, "8", "#ffffff"),
			flashvars = this.getVars();
			swf.addParam("wmode", "transparent"); 
			
			for(k in flashvars){
				swf.addVariable(k, flashvars[k]);
			}
			swf.write(this.settings.upload_panel_id);
	},
	getVars: function(){
		var opts = this.settings,
			paramString = this.buildParamString();
		return {
				callIndex   : FTUploadFile.count-2, // 回调下标
				fileMaxSize : opts.file_size_limit, //最大允许上传的文件大小（字节为单位）
				fileType    : opts.file_types, //允许上传的文件类型
				fileTypeStr : opts.file_types_description, //允许上传文件文字描述
				uploadUrl   : encodeURIComponent(opts.upload_url + '?' + paramString), //文件上传的URL地址
				configUrl   : opts.config_url, //文件配置URL地址
				fid			: opts.post_params.preFileName //文件配置ID
			};
	},
	
	flashCall : function(){
		var self = this;
		
		return {
			uploadBefore : function() {
				
				self.swfObject = document[self.movieName];
				
				function callFlash() {
					self.swfObject.setValue(self.getVars());
				}
				
				if(/firefox/.test(navigator.userAgent.toLowerCase())) {
					setTimeout(function() {//Firefox/3.5.5直接调用失效
						callFlash();
					},0);
				} else {
					callFlash();
				}
			},
			limitError : function(_msg) {
				self.debug(_msg);
				
				if(typeof self.settings.upload_error_handler === 'function') {
					self.settings.upload_error_handler(_msg);
				}
			},
			ioError : function(_msg) {
				this.limitError(_msg.text);
			},
			startUpload : function() {
				self.debug("开始上传");
				
				if(typeof self.settings.upload_start_handler === 'function') {
					self.settings.upload_start_handler();
					//this.settings = this.settings.upload_start_handler(this.settings);
					//this.uploadBefore();
				}
			},
			progress : function(_bytesLoaded, _bytesTotal) {
				var percent = Math.ceil((_bytesLoaded/_bytesTotal) * 100);
				self.debug("上传进度：" + percent + "%");
				if(typeof self.settings.upload_progress_handler === 'function') {
					self.settings.upload_progress_handler(percent);
				}
			},
			uploadComplete : function() {
				self.debug("上传完成");
				
				if(typeof self.settings.upload_complete_handler === 'function') {
					self.settings.upload_complete_handler();
				}
			},
			uploadSuccess : function(_data) {
				self.debug("上传成功");
				
				if(typeof self.settings.upload_success_handler === 'function') {
					self.settings.upload_success_handler(_data);
				}
			},
			mouseOver : function(){
				if(typeof self.settings.mouseOver === 'function'){
					self.settings.mouseOver();
				}
			},
			mouseOut : function(){
				if(typeof self.settings.mouseOut === 'function'){
					self.settings.mouseOut();
				}
			}
		}
	},
	debug : function(_msg) {
		if(!this.settings.debug) {
			return false;
		} else if(!this.console) {
			this.console = document.createElement("textarea");
			with(this.console.style) {
				width = '700px';
				height = '300px';
				overflow = 'auto';
				margin = '5px';
				position = 'absolute';
				right = '10px';
				top = '20px';
				zIndex = '40';
			}
			document.body.appendChild(this.console);
		}
		this.console.value = this.console.value + _msg + "\n";
		this.console.scrollTop = this.console.scrollHeight - this.console.clientHeight;
	}
};

