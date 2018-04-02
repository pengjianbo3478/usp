<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<input type="hidden" id="_MENU_L0" value="4" />
<input type="hidden" id="_MENU_L1" value="0" />
<div class="search">
	<form  action="${ctx}/consumer/search.do"  method="POST" class="validate">
		<div class="sear_tab mb10">
			<span class="searSub">
				<span>应用名称：</span>
				<select class="input_txt_l" placeholder="输入应用名称" Style="width:100px" name="name">
					<options items="${name}" itemLabel="value" itemValue="key" />
				</select>
				<span>选择日期：</span>
				<input name="endDtae" class="input_txt_l" onclick="WdatePicker({dateFmt:'yyyy-MM-d'})"  Style="width:60px;"/>
				<input class="inp_btn" style="margin-left: 20px;" type="button" value="查询" id="queryBtn" />
		</div>
		<input type="hidden" id="_FTIAO_PAGINATE" name="currentPageNum" value="1" form-id="monthly" btn-id="queryBtn" />
	</form>
	<div id="_FTIAO_PAGINATE_CONTENT"></div>
</div>
<div class="content-order">
	<table class="tab_list mb20">
		<thead>
			<tr>
				<th width="10%">消费名称</th>
				<th width="10%">时长/次数</th>
				<th width="10%">费用</th>
				<th width="10%">费用名称</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${paginate.data}" varStatus="s">
				<tr>
					<td>短信</td>
					<td>${item.stust}条</td>
					<td>${item.stust}元</td>
					<td>${item.name}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="_DELETE_DIALOG"></div>
<div id="window_dialog" style="display: none;"></div>
<input type="hidden" class="ctx" value="${ctx}" />
</script>
<script type="text/javascript">
	$(document).ready(function() {
		 $("#queryBtn").click();
	});
	
</script>
