<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri='http://java.sun.com/jstl/core_rt' prefix='c'%>
var uploadify_onSelectError = function(file, errorCode, errorMsg) {
	switch(errorCode) {  
	    case SWFUpload.QUEUE_ERROR.QUEUE_LIMIT_EXCEEDED:
	        this.queueData.errorMsg = "上传的文件数量已经超出系统限制的"+this.settings.queueSizeLimit+"个文件！";  
	        break;  
	    case SWFUpload.QUEUE_ERROR.FILE_EXCEEDS_SIZE_LIMIT:  
	        this.queueData.errorMsg = 文件大小超过限制( " + this.settings.fileSizeLimit + " );  
	        break;  
	    case SWFUpload.QUEUE_ERROR.ZERO_BYTE_FILE: 
	        this.queueData.errorMsg = "文件 ["+file.name+"] 为空！";  
	       break;  
	    case SWFUpload.QUEUE_ERROR.INVALID_FILETYPE:   
	        this.queueData.errorMsg = "文件 ["+file.name+"] 类型不正确！";  
	        break;   
	 }
}; 

var uploadify_onUploadError = function(file, errorCode, errorMsg, errorString) {         
	if (errorCode == SWFUpload.UPLOAD_ERROR.FILE_CANCELLED || errorCode == SWFUpload.UPLOAD_ERROR.UPLOAD_STOPPED) {             
		return;         
	}         
	var msgText = "上传失败\n";         
	switch (errorCode) {             
		case SWFUpload.UPLOAD_ERROR.HTTP_ERROR:                 
			msgText += "HTTP 错误\n" + errorMsg;                 
			break;             
		case SWFUpload.UPLOAD_ERROR.MISSING_UPLOAD_URL:                 
			msgText += "上传文件丢失，请重新上传";                 
			break;             
		case SWFUpload.UPLOAD_ERROR.IO_ERROR:                 
			msgText += "IO错误";                 
			break;             
		case SWFUpload.UPLOAD_ERROR.SECURITY_ERROR:                 
			msgText += "安全性错误\n" + errorMsg;                 
			break;             
		case SWFUpload.UPLOAD_ERROR.UPLOAD_LIMIT_EXCEEDED:                 
			msgText += "每次最多上传 " + this.settings.uploadLimit + "个";                 
			break;             
		case SWFUpload.UPLOAD_ERROR.UPLOAD_FAILED:                 
			msgText += errorMsg;                 break;             
		case SWFUpload.UPLOAD_ERROR.SPECIFIED_FILE_ID_NOT_FOUND:                 
			msgText += "找不到指定文件，请重新操作";                 
			break;             
		case SWFUpload.UPLOAD_ERROR.FILE_VALIDATION_FAILED:                 
			msgText += "参数错误";                 
			break;             
		default:                
			msgText += "文件:" + file.name + "\n错误码:" + errorCode + "\n" + errorMsg + "\n" + errorString;        
		 }         
		 alert(msgText);  
		 return parameters; 
}
		 
var FitaoUploadify=function(param){
	$(param.uploadify).uploadify({
		'uploader' : param.uploader,
		'swf' : '<c:url value="/jqueryUploadify/uploadify.swf"/>',
		'queueID' : param.fileQueue,
		'queueSizeLimit' : param.queueSizeLimit,  
		'fileTypeDesc' : param.fileTypeDesc,
		'fileTypeExts' : param.fileTypeExts,
		'auto' : param.auto,
		'multi' : param.multi,
		'height' : 20,
		'formData' : param.formData,
		'buttonText' : '选择文件',
		'removeCompleted' : false,
		'onCancel' : function(file) {},
		'onUploadSuccess' : function(file, data, response) {
			if (response) {
				var cancel = $("#" + file.id + " .cancel a");
				if (cancel) {
					cancel.click(function() {
						$.ajax({
							type : param.ajaxType,
							url : param.ajaxURL,
							data : {
								fileID : data
							},
							dataType : "json",
							success : function(data) {
								
							}

						});
					});
				}
			} else {
				alert('上传失败!');
			}
		},
		'onSelectError' : uploadify_onSelectError,     
		'onUploadError' : uploadify_onUploadError
	});	
};
