function ftiaoUpload(param){
	if("undefined" == typeof FileUtils){
		$("head").append("<script src='http://dss.ftiao.cn/js/common/fileUtils.js'></script>")
	}
	$("#"+param.id).on("change",function(){
		var filePath = $("#"+param.id).val();
		var fileType = $("#" + param.id).attr("fileType") || "image";
		var fileTypeRegexp = FileUtils[fileType + "TypeRegexp"];
		if(FileUtils.validFileType(filePath,fileTypeRegexp)){
			$.ajaxFileUpload({
				url: param.uploadUrl,
				secureuri: false,
				type: "post",
				fileElementId: param.id,//文件选择框的id属性
				dataType: 'json',
				crossDomain: false,//是否跨域
				data: {
					"classFullName":param.classFullName,
					"jsonStrValue": param.jsonStrValue,
					"preFileName": param.preFileName
				},
				//服务器返回的格式，可以是json
				success: param.commonOnUploadSuccess,
				error: function (data, status, e){
					//Utils.alertDialog("文件上传失败，可能是超出了文件大小限制！");
					jAlert('文件上传失败，可能是超出了文件大小限制！', '提示', 2000);
				}
			});
			//再次绑定change事件
			ftiaoUpload(param);
		}else{
			//Utils.alertDialog("不支持的文件类型");
			jAlert('不支持的文件类型！', '提示', 2000);
		}
	});
	return false;
}