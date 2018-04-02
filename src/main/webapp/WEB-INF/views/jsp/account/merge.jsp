<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/common/jquery.validate.js?v=1.0.0"></script>
<head>
</head>
<div id="_dtl_area">
	<form  action="${ctx}/account/merge.do" method="POST" cssClass="validate">
		<table id="content" class="tab_list mb20">

			<tr>
				<td style="width: 5%">注册邮箱：</td>
				<td>
					<hidden name="id" />
					<input name="merchantId" class="input_txt_l {required:true,maxlength:18,minlength:1}" />
				</td>
			</tr>
		
			<tr>
				<td style="width: 5%">注册手机号：</td>
				<td>
					<input name="rate" class="input_txt_l {required:true,maxlength:10,minlength:1}" placeholder="请输入注册手机号！" />
				</td>
			</tr>
			
			<tr>
				<td style="width: 5%">联系地址：</td>
				<td>
					<input name="rate" class="input_txt_l {required:true,maxlength:10,minlength:1}" placeholder="请输入联系地址！" />
				</td>
			</tr>

		</table>
		<input type="submit" value="提交" class="inp_btn">
		<input type="reset" value="重置" class="inp_btn">
	</form>
</div>
<input type="hidden" id="_MENU_L0" value="1" />
<input type="hidden" id="_MENU_L1" value="2" />
