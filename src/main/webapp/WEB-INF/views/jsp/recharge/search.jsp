<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<input type="hidden" id="_MENU_L0" value="3" />
<input type="hidden" id="_MENU_L1" value="1" />
<div class="search">
	<form  action="${ctx}/recharge/search.do" method="POST" class="validate">
		<div class="sear_tab mb10">
			<span>开始结束时间：</span>
			<input path="startDate" readonly="true" class="input_txt_l" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-d'})" />
			<span>——</span>
			<input path="endDate" readonly="true" class="input_txt_l" style="width:80px;" onclick="WdatePicker({dateFmt:'yyyy-MM-d'})" />
		<input class="inp_btn" style="margin-left: 20px;" type="button" value="查询" id="queryBtn" />
		</div>
	</form>
</div>
<div class="content-order">
	<table class="tab_list mb20">
		<thead>
			<tr>
				<th width="10%">订单号</th>
				<th width="10%">生成时间</th>
				<th width="10%">充值方式</th>
				<th width="10%">金额</th>
				<th width="10%">状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${paginate.data}" varStatus="s">
				<tr>
					<td>${item.code}</td>
					<td><f:formatDate value="${item.createdDate}" pattern="yyyy年MM月dd日HH时mm分ss秒 "/></td>
					<td>${item.stust}</td>
					<td><f:formatNumber  value="${item.amount}" pattern="￥#,#00.00#"/></td>
					<td>${item.stust}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="_DELETE_DIALOG"></div>
<div id="window_dialog" style="display: none;"></div>
<input type="hidden" class="ctx" value="${ctx}" />
<script type="text/javascript">
</script>
