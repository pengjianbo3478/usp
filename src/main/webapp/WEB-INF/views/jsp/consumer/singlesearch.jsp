<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>

<div class="singlesearch">
	<form  action="${ctx}/consumer/singlesearch.do"  method="POST" class="validate">
		<div class="sear_tab mb10">
			<span class="searSub">
				<span>发送时间：</span>
				<input name="endDtae" class="input_txt_l" onclick="WdatePicker({dateFmt:'yyyy-MM-d'})"  Style="width:60px;"/>
				<span>号码：</span>
				<input path="name" class="input_txt_l" placeholder="输入号码"  style="width:80px;" />
				<span>模板ID：</span>
				<input path="name" class="input_txt_l" placeholder="输入模板ID"  Style="width:80px;" />
				<span>状态：</span>
				<input type="radio" name="sex" id="all" value="all" >
				 <label for="all">全部</label>
				 <input type="radio" name="sex" id="win" value="win" >
				 <label for="win">成功</label> 
				 <input type="radio" name="sex" id="fail" value="fail" >
				 <label for="fail">失败</label>
				<span>失败原因</span>
				<select name="status" Style="width:80px;" class="input_txt_l" >
					<option value=" " label="请选择失败原因" />
					<options items="${xxxx}" itemLabel="name" itemValue="id" />
				</select>
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
				<th width="10%">发送时间</th>
				<th width="10%">接收号码</th>
				<th width="10%">短信内容</th>
				<th width="10%">发送状态</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${paginate.data}" varStatus="s">
				<tr>
					<td><f:formatDate value="${item.createdDate}" pattern="yyyy年MM月dd日HH时mm分ss秒 "/></td>
					<td>${item.number}</td>
					<td>${item.details}</td>
					<td>${item.stust}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<div id="_DELETE_DIALOG"></div>
<div id="window_dialog" style="display: none;"></div>
<input type="hidden" id="_MENU_L0" value="4" />
<input type="hidden" id="_MENU_L1" value="2" />
<input type="hidden" class="ctx" value="${ctx}" />
</script>
<script type="text/javascript">
	$(document).ready(function() {
		 $("#queryBtn").click();
	});
	
</script>
