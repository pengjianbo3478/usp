<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri='http://java.sun.com/jstl/core_rt' prefix='c'%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>登录</title>
<c:set var="static_ftiao_cn" scope="request" value="http://192.168.232.143" />
<c:set var="ctx" scope="request" value="${pageContext.request.scheme}${'://'}${pageContext.request.serverName}${':'}${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<c:if test="${fn:contains(ctx, 'localhost') }">
	<c:set var="static_ftiao_cn" scope="request" value="${ctx}" />
</c:if>
<link rel="shortcut icon" href="favicon.ico" />
</head>
<body id="cas" class="fl-theme-iphone">
	<div class="loginHeader">
		<div class="loinTop">
			<div class="pt10">
				<img src="${static_ftiao_cn}/images/9h.jpg" alt="logo" />
			</div>
		</div>
	</div>
	<div id="content">
		<link rel="stylesheet" type="text/css" href="${static_ftiao_cn}/css/global.css" />
		<link rel="stylesheet" type="text/css" href="${static_ftiao_cn}/css/login.css" />
		<div class="box fl-panel" id="login">
			<form id="fm1" class="fm-v clearfix" action="j_spring_security_check" method="post">
				<!-- ******************************************************************************* -->
				<div class="loginContent">
					<div class="loginCnt clearfix pt40">
						<div class="lgleft">
							<h3>用户登录</h3>
							<div style="color: #F00" id="errorMsg">
								<c:if test="${param.error}">
									用户名或密码有误，请重新输入！
								</c:if>
							</div>
							<ul class="clearfix loginList">
								<li class="user">
									<input id="username" name="j_username" class="required" tabindex="1"  type="text" value="test" size="25" autocomplete="false" />
								</li>
								<li class=" password ml5">
									<input id="password" name="j_password" class="required" tabindex="2" type="password" value="123456" size="25" autocomplete="off" />
								</li>
							</ul>
							<a href="" class="forget-pwd" class="required" class="forget-pwd" target="_blank">忘记登录密码</a>
							<a href="${ctx}/index/index.do" class="register" target="_blank">免费注册</a>
							
							<div class="mt15">
								<input type="hidden" name="_eventId" value="submit" />
								<input class="loginBtn" name="submit" accesskey="l" value="LOGIN" tabindex="4" type="submit" />
								<input class="loginBtn" name="reset" accesskey="c" value="CLEAR" tabindex="5" type="reset" />
							</div>
						</div>
						<div class="lgright">
							<p class="mb20">登录xx帐户，享受xx提供的更多服务。</p>
							<div class="lrtxt">
								<p>轻松管理你的订单信息</p>
								<span>登录后进行订单管理，账户余额一目了然。</span>
							</div>
							<div class="lrtxt mt15" style="display: none;">
								<p>轻松管理你的订单信息</p>
								<span>登录后进行订单管理，账户余额一目了然。</span>
							</div>
						</div>
					</div>
					<p class="logincntshadow"></p>
				</div>
				<!-- ******************************************************************************* -->
			</form>
		</div>
	</div>
	<div id="footer" class="footercss">
		<p>Copyright 2013,版权所有 XXXXXX有限公司ICP/IP证：粤ICP备09091479号-1</p>
	</div>
</body>
</html>
