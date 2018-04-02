<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<input type="hidden" id="_MENU_L0" value="1" />
<input type="hidden" id="_MENU_L1" value="1" />
<head>
</head>
<div id="_dtl_area">
	<form action="${ctx}/account/passwordmerge.do" method="POST" class="validate">
		<div style="color: red">${msg}</div>
		<table id="content" class="tab_list mb20">
			<tr>
				<td style="width: 8%">当前的登陆密码：</td>
				<td>
					<input name="oldPassword" type="password" class="input_txt_l {required:true,minlength:6,maxlength:16}" id="oldPwd" />
				</td>
			</tr>
			<tr>
				<td style="width: 8%">新的登陆密码：</td>
				<td>
					<input name="newPassword" type="password" class="input_txt_l {required:true,minlength:6,maxlength:16}" id="newPwd" />
				</td>
			</tr>
			<tr>
				<td style="width: 8%">确认新的登陆密码：</td>
				<td>
					<input name="confirmNewPassword" type="password" class="input_txt_l {required:true,minlength:6,maxlength:16,equalTo:'#newPwd'}" id="confirmNewPwd" />
				</td>
			</tr>
		</table>
		<input type="submit" value="提交" class="inp_btn">
		<input type="reset" value="重置" class="inp_btn">
	</form>
</div>
