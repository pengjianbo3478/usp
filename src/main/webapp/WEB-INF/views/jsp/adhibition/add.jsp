<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" id="_MENU_L0" value="2" />
<input type="hidden" id="_MENU_L1" value="1" />
<head>
</head>
<div id="_dtl_area">
	<form  action="${ctx}/adhibition/add.do" method="POST" class="validate">
		<table id="content" class="tab_list mb20">
			<tr>
				<td style="width: 5%">应用名称：</td>
				<td>
					<input path="code" cssClass="input_txt_l {required:true,maxlength:4,minlength:2}" />
				</td>
			</tr>
			<tr>
				<td style="width: 5%">使用功能：</td>
				<td>
						<label><input name="Fruit" type="checkbox" value="" />短信验证 </label>
						<label><input name="Fruit" type="checkbox" value="" />短信通知 </label>
				</td>
			</tr>
			
		</table>
		<input type="submit" value="提交" class="inp_btn">
		<input type="reset" value="重置" class="inp_btn">
	</form>
	<script type="text/javascript">
		
	</script>
</div>
