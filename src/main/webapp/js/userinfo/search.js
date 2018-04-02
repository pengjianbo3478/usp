OB.CI.UI.USERINFO.SEARCH = (function(h) {
	var o = {
		init : function() {
			ftiaoPageUtils.toPage(1);
			$("#addBtn").on("click", function() {
				o.add();
			});
			$("#userInfo").keypress(function(e) {
				if (e.which == 13) {
					return false;
				}
			});
		},
		merge : function(obj) {
			window.location.href = h("#_MERGE").val() + "?id=" + $(obj).attr("data-id");
		}
		
	};
	return {
		init : o.init,
		merge : o.merge
	};
})($);
$(function() {
	OB.CI.UI.USERINFO.SEARCH.init();
});