<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<input type="hidden" id="_MENU_L0" value="2" />
<input type="hidden" id="_MENU_L1" value="3" />
<div class="search">
	<form  action="${ctx}/template/search.do" method="POST" class="validate">
		<div class="sear_tab mb10">
		<h4>使用规则:</h4>
		<p>1、提交模板时请详情阅读文档：</p>
		<br>
		<p>2、模板通过才可以调用。。。</p>
		<br>
		<input class="inp_btn" style="margin-left: 20px;" type="button" value="新增" id="addBtn" />
		</div>
	</form>
</div>
<div class="content-order">
	<table class="tab_list mb20">
		<thead>
			<tr>
				<th width="10%">应用名称</th>
				<th width="10%">模板ID</th>
				<th width="10%">标题</th>
				<th width="10%">申请时间</th>
				<th width="10%">审核状态</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${paginate.data}" varStatus="s">
				<tr>
					<td>${item.name}</td>
					<td>${item.id}</td>
					<td>${item.code}</td>
					<td>
						<f:formatDate value="${item.createdDate}" pattern="yyyy-MM-dd HH:mm:ss " />
					</td>
					<td>${item.stust}</td>
					<td>
						<a onclick="detail(${item.id})">查看</a>
						|
						<a onclick="remove_(this)" data-id="${item.id}">删除</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="_DELETE_DIALOG"></div>
<div id="window_dialog" style="display: none;"></div>
<input type="hidden" class="ctx" value="${ctx}" />
<input type="hidden" id="_ADD" value="${ctx}/add.do">
<input type="hidden" id="_DETAIL" value="${ctx}/detail.do">
<input type="hidden" id="_REMOVE" value="${ctx}/remove.do">
<script type="text/javascript">
		$(document).ready(function() {
			$("#addBtn").on("click", function() {
				window.location.href = $("#_ADD").val();
			});
		});
</script>
