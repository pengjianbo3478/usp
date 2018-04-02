<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri='http://java.sun.com/jstl/core_rt' prefix='c'%>
<%@taglib prefix='security' uri='http://www.springframework.org/security/tags'%>
<security:authentication property="principal" var="authentication" />
<div class="logo">
	<img width="420" height="50" src="${static_ftiao_cn}/images/9h.jpg" alt="logo" />
</div>
<div class="userSet">
	<a class="userName" href="javascript:void(0)">${authentication.username}</a>
	<a class="logout" href="${ctx}/logout">注销</a>
	<div class="userListCenter">
		<a href="">修改密码</a>
	</div>
</div>
