<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="content-order">
	<table class="tab_list mb20">
		<thead>
			<tr>
				<th width="10%">编号</th>
				<th width="10%">行业类别</th>
				<th width="10%">创建时间</th>
				<th width="10%">操作</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="item" items="${paginate.data}" varStatus="s">
				<tr>
					<td>${item.code}</td>
					<td>${item.name}</td>
					<td>
						<f:formatDate value="${item.createdDate}" pattern="yyyy-MM-dd HH:mm:ss " />
					</td>
					<td>
						<a onclick="detail(${item.id})">编辑</a>
						|
						<a onclick="remove_(this)" data-id="${item.id}">删除</a>
						|
						<a onclick="pause_(this)" data-id="${item.id}">暂停</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
<script type="text/javascript">
</script>