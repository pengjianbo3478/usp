<%@taglib uri='http://java.sun.com/jstl/core_rt' prefix='c'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<input type="hidden" id="_CONTEXT_PATH" value="${ctx}">
<ul class="nav">
	<li>
		<a href="${ctx}/index.do">
			<i class="i11"></i>
			首页
		</a>
	</li>
	<li>
		<a href="${ctx}/account/search.do">
			<i class="i1"></i>
			账号管理
		</a>
	</li>
	<li>
		<a href="${ctx}/finance/search.do">
			<i class="i2"></i>
			管理页面
		</a>
	</li>
	<li>
		<a href="${ctx}/monthly/search.do">
			<i class="i3"></i>
			财务管理
		</a>
	</li>
	<li>
		<a href="${ctx}/consumer/search.do">
			<i class="i4"></i>
			消费详单
		</a>
	</li>
	<li>
		<a href="${ctx}/authority/search.do">
			<i class="i5"></i>
			统计总览
		</a>
	</li>
</ul>