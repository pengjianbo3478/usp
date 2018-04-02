//var uploadify_onSelectError = function(file, errorCode, errorMsg) {
//	switch(errorCode) {  
//	    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
//	        this.queueData.errorMsg = "上传的文件数量已经超出系统限制的["+this.settings.queueSizeLimit+"]个文件！";  
//	        break;  
//	    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
//	        this.queueData.errorMsg = "文件大小超过限制[ " + this.settings.fileSizeLimit + " ]";  
//	        break;  
//	    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE: 
//	        this.queueData.errorMsg = "文件 ["+file.name+"] 为空！";  
//	       break;  
//	    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:   
//	        this.queueData.errorMsg = "文件 ["+file.name+"] 类型不正确！";  
//	        break;  
//	    default:   
//	    	 this.queueData.errorMsg = "其他错误";  
//        break;  
//	 }
//}; 

//var uploadify_onUploadError = function(file, errorCode, errorMsg, errorString) {         
//	if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED || errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {             
//		return;         
//	}         
//	var msgText = "上传失败\n";         
//	switch (errorCode) {             
//		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:                 
//			msgText += "HTTP 错误\n" + errorMsg;                 
//			break;             
//		case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:                 
//			msgText += "上传文件丢失，请重新上传";                 
//			break;             
//		case SWFUpload.UPLOAD_ERROR.IO_ERROR:                 
//			msgText += "IO错误";                 
//			break;             
//		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:                 
//			msgText += "安全性错误\n" + errorMsg;                 
//			break;             
//		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:                 
//			msgText += "每次最多上传 " + this.settings.uploadLimit + "个";                 
//			break;             
//		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:                 
//			msgText += errorMsg;                 break;             
//		case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:                 
//			msgText += "找不到指定文件，请重新操作";                 
//			break;             
//		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:                 
//			msgText += "参数错误";                 
//			break;             
//		default:                
//			msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n" + errorMsg + "\n" + errorString;        
//		 }         
//		 return msgText; 
//};


var fitaoUploadify=function(param){
	$("#"+param.uploadify).uploadify({
		'swf' : $("#"+param.uploadify).attr("data-swf-global-url")+'/js/component/upload/uploadify.swf',
		'uploader' : param.uploadUrl,
		'auto' : param.auto,
		'buttonText' : '选择文件',
//		'buttonCursor' : 'hand',
//		'buttonImage' : param.btnImg,
//		检查文件是否已在队列中,默认是false
//		'checkExisting':false,
		'debug':false,
		'fileSizeLimit':param.fileSizeLimit,
		'fileTypeDesc':param.fileTypeDesc,
		'fileTypeExts' : param.fileTypeExts,
		'formData' : param.formData,
		'height':20,
		'width':60,
		'itemTemplate': 
			 '<div id="${fileID}" class="uploadify-queue-item">\
			<div class="cancel">\
				<a  name="del" href="javascript:$(\'#'+param.uploadify+'\').uploadify(\'cancel\', \'${fileID}\')">删除</a>\
				<a name ="upload" href="javascript:$(\'#'+param.uploadify+'\').uploadify(\'upload\', \'${fileID}\')">上传</a>\
			</div>\
			<span class="fileName">${fileName} (${fileSize})</span><span class="data"></span>\
			<div class="uploadify-progress">\
				<div class="uploadify-progress-bar"><!--Progress Bar--></div>\
			</div>\
		</div>',
		'method':'POST',
		'multi':param.multi,
		//'overrideEvents':param.overrideEvents,
		//'preventCaching':param.preventCaching,
		//百分比还是速度
		//'progressData':param.progressData,
		//上传文件的队列id
		'queueID':param.queueID,
		//队列中上传文件的个数限制
		'queueSizeLimit':param.queueSizeLimit,
		//设置true或FALSE，是否将上传完的文件保持在队列中
		'removeCompleted':false,
		//上传完成后几秒将文件从队列中删除
		'removeTimeout':1,
		//如果设置为TRUE，在上传过程中出错，将自动添加到队列中继续上传
//		'requeueErrors':param.requeueErrors,
		//设置等待服务器上传成功的响应时间，超过时间将被默认为上传成功
//		'successTimeout':param.successTimeout,
		//上传的文件数限制，超过后onUploadError会被调用
//		'uploadLimit':param.uploadLimit,
//		'onInit':function(instance){
//		},
		 'onCancel' : function(file) {
			 $("#"+param.uploadify).uploadify('disable', false);
		 },
		 'onUploadStart':param.onUploadStart,
//		 'onDialogOpen':param.onDialogOpen,
		 'onUploadSuccess' : param.commonOnUploadSuccess, 
		  'onFallback':function(){             //检测FLASH失败调用  
	            alert("您未安装FLASH控件，无法上传文件！请安装FLASH控件后再试。"); 
	        }, 
	        'onSelect' :param.onSelect 
//	        ,
//		 'onSelectError' : uploadify_onSelectError,
//		 'onUploadError' : uploadify_onUploadError
	});	
};


