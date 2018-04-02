<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>

<div class="search">
	<form  action="${ctx}/monthly/search.do"  method="POST" class="validate">
		<div class="sear_tab mb10">
			<span class="searSub">
				<span>选择月份：</span>
				<input name="endDtae" class="input_txt_l" onclick="WdatePicker({dateFmt:'yyyy-MM'})"  Style="width:60px;"/>

				<input class="inp_btn" style="margin-left: 20px;" type="button" value="搜索" id="queryBtn" />
		</div>
		<input type="hidden" id="_FTIAO_PAGINATE" name="currentPageNum" value="1" form-id="monthly" btn-id="queryBtn" />
	</form>
	<div id="_FTIAO_PAGINATE_CONTENT"></div>
</div>
<div id="_DELETE_DIALOG"></div>
<div id="window_dialog" style="display: none;"></div>
<input type="hidden" id="_MENU_L0" value="3" />
<input type="hidden" id="_MENU_L1" value="0" />
<input type="hidden" class="ctx" value="${ctx}" />
<input type="hidden" id="_ADD" value="${ctx}/monthly/add.do">
<input type="hidden" id="_MERGE" value="${ctx}/monthly/merge.do">
<input type="hidden" id="_REMOVE" value="${ctx}/monthly/remove.do">
<input type="hidden" id="_DETAIL" value="${ctx}/monthly/detail.do">
</script>
<script type="text/javascript">
	$(document).ready(function() {
	});
	
</script>
