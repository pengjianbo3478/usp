<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<input type="hidden" id="_MENU_L0" value="2" />
<input type="hidden" id="_MENU_L1" value="0" />
<div class="search">
	<form action="${ctx}/adhibition/search.do"  method="post" class="validate">
		<div class="sear_tab mb10">
			应用名称：<input path="name" class="input_txt_l" placeholder="输入应用名称"  Style="width:80px;" />
			<input class="inp_btn" style="margin-left: 20px;" type="button" value="查询" id="queryBtn" />
				<input class="inp_btn" style="margin-left: 20px;" type="button" value="新增" id="addBtn" />
		</div>
		<input type="hidden" id="_FTIAO_PAGINATE" name="currentPageNum" value="1" form-id="adhibition" btn-id="queryBtn" />
	</form>
	<div id="_FTIAO_PAGINATE_CONTENT"></div>
</div>
<div id="_DELETE_DIALOG"></div>
<div id="window_dialog" style="display: none;"></div>
<input type="hidden" id="_MENU_L0" value="6" />
<input type="hidden" id="_MENU_L1" value="0" />
<input type="hidden" class="ctx" value="${ctx}" />
<input type="hidden" id="_ADD" value="${ctx}/adhibition/add.do">
<input type="hidden" id="_MERGE" value="${ctx}/adhibition/merge.do">
<input type="hidden" id="_DETAIL" value="${ctx}/adhibition/detail.do">
<input type="hidden" id="_REMOVE" value="${ctx}/adhibition/remove.do">
<script type="text/javascript">
	$(document).ready(function() {
		$("#queryBtn").click();
		$("#addBtn").on("click", function() {
			window.location.href = $("#_ADD").val();
		});
	});
</script>
