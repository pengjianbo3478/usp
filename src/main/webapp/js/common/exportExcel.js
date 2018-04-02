//Excel 导出
(function($){	
$.fn.exportXls = function(url,data){ 
	$.ajax({
        url : url,
        type : "POST",
        data : data,
        dataType : "json",
        success : function(data) {
            if (data.result == "Y") {
            	if(data.object.fileName != "" && data.object.fileName != null){
            		window.location = $("#_CONTEXT_PATH").val() + "/export/excel.do?fileName=" + data.object.fileName;
            		jAlert("导出成功！", "提示", 1500);
            	} else {
            		jAlert("没有数据可导出！", "提示", 1000);
            	}
            }
        },
        error : function() {
            jAalert("导出失败！", "提示", 1000);
        }
    });
};  
})(jQuery);