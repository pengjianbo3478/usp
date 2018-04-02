OB.CI.PR.PRODUCTINFO.SEARCH = (function(h) {
	var o = {
		init : function() {
			ftiaoPageUtils.toPage(1);
			$("#addBtn").on("click", function() {
				o.add();
			});
			$("#productInfo").keypress(function(e) {
				if (e.which == 13) {
					return false;
				}
			});
		},
		add : function() {
			window.location.href = h("#_ADD").val();
		},
		merge : function(obj) {
			window.location.href = h("#_MERGE").val() + "?id=" + $(obj).attr("data-id");
		},
		detail : function(obj) {
			var $this = $(obj);
			$("#window_dialog").dialog({
				title : '详情',
				autoOpen : true,
				width : 640,
				height : 460,
				open : function() {
					var url = $("#_CONTEXT_PATH").val() + '/productinfo/detail.do?id=' + $this.attr("data-id");
					$(this).load(url);
				}
			});
		},
		remove : function(obj) {
			var $this = $(obj);
			$("#_DELETE_DIALOG").dialog({
				title : "警告!",
				dialogClass : "alert",
				modal : true,
				close : function(event, ui) {
					$(this).dialog("close");
				},
				open : function() {
					$(this).html("您确定删除吗？");
				},
				buttons : [ {
					text : "确定",
					click : function() {
						$(this).dialog("close");
						$.ajax({
							url : $("#_REMOVE").val(),
							type : "POST",
							data : {
								id : $this.attr("data-id")
							},
							dataType : "json",
							success : function(data) {
								if (data.result == "Y") {
									$this.parents("tr").remove();
									jAlert("删除成功！", "提示", 1000);
								}
							},
							error : function() {
								jAlert("删除失败！", "提示", 1000);
							}
						});
					}
				}, {
					text : "取消",
					click : function() {
						$(this).dialog("close");
					}
				} ]
			});
		}
	};
	return {
		init : o.init,
		add : o.add,
		merge : o.merge,
		detail : o.detail,
		remove : o.remove
	};
})($);
$(function() {
	OB.CI.PR.PRODUCTINFO.SEARCH.init();
});