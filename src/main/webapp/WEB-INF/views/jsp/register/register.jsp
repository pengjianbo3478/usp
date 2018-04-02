<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/checktable/operatoraddcheck.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册</title>
</head>
<body>
<div id="_dtl_area">
	<form action="${ctx}/register/register.do" method="POST"  class="validate">
		<table id="content" class="tab_list mb20">
			<tr>
				<td style="width: 8%">邮箱：</td>
				<td>
					<input name="email" class="input_txt_l {required:true,maxlength:18,email:true}" />
				</td>
			</tr>
			<tr>
				<td style="width: 8%">登录密码 ：</td>
				<td>
					<input name="newPassword" type="password" class="input_txt_l {required:true,minlength:6,maxlength:16}" id="newPwd" />
				</td>
			</tr>
			<tr>
				<td style="width: 8%">确认密码 ：</td>
				<td>
					<input name="confirmNewPassword" type="password" class="input_txt_l {required:true,minlength:6,maxlength:16,equalTo:'#newPwd'}" id="confirmNewPwd" />
				</td>
			</tr>
			<tr>
				<td style="width: 8%">手机号码：</td>
				<td>
					<input name="phone" class="input_txt_l {input_txt_l {required:true,mobile:true,maxlength:11,minlength:11}}" />
				</td>
			</tr>
			<tr>
				<td style="width: 8%">图形验证码：</td>
				<td>
						<input type="text" class="input_txt_l {required:true}" id="newreg-captcha2" autocomplete="off" value="">
						<span class="captchaimg"><img width="100px" height="26px" src=" " class="input_txt_l {required:true}"></span><span class="changecaptcha">换一张</span>
				</td>
			</tr>
			<tr>
				<td style="width: 8%">手机验证码：</td>
				<td>
					<input name="phoneCode" class="input_txt_l {required:true}" />
					<a href="" class="input_txt_l {required:true}">点击获取验证码</a>
				</td>
			</tr>
		</table>
		<input type="submit" value="注册" class="inp_btn">
	</form>
	</div>
</body>
</html>