<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<input type="hidden" id="_MENU_L0" value="1" />
<input type="hidden" id="_MENU_L1" value="0" />
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<div class="search">
	<form  action="${ctx}/account/search.do" method="POST" class="validate">
		<div class="sear_tab mb10">
		<input class="inp_btn" style="margin-left: 20px;" type="button" value="修改个人信息" id="addBtn" />
		</div>
	</form>
</div>
<div class="content-order">
		<table class="tab_list mb10" >
		<tr>
			<td>注册邮箱</td>
			<td>${xxx.xxx}</td>
		</tr>
		<tr>
			<td>注册手机号</td>
			<td>${xxx.xxx}</td>
		</tr>
		<tr>
			<td>联系地址</td>
			<td>${xxx.xxx}</td>
		</tr>
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
