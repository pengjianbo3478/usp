<%@page language="java" contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<link rel="stylesheet" href="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.css" />
<script type="text/javascript" src="${ctx}/js/component/my97DatePicker/WdatePicker.js?v=1.0.0"></script>
<script type="text/javascript" src="${ctx}/js/common/pageUtils4Ajax.js"></script>
<script type="text/javascript" src="${ctx}/js/ui/Dialog/jquery-ui-1.10.4.dialog.js?v=1"></script>
<input type="hidden" id="_MENU_L0" value="2" />
<input type="hidden" id="_MENU_L1" value="0" />
<div class="search">
	<form  action="${ctx}/finance/search.do" method="POST" class="validate">
		<div class="sear_tab mb10">
		<span>账户余额：</span>
		<input name="" class="input_txt_l"  Style="width:50px;" />
		<span>条数：</span>
		<input name="" class="input_txt_l" Style="width:50px;" />
		<br>
		<h4>配置余额不足提醒</h4>
		<br>
		<p>默认情况下。。。。</p>
		<br>
		<input type="radio" name="sex" id="male" value="male" >
		 <label for="male">默认</label><br>
  		<input type="radio" name="sex" id="female" value="female">
  		<label for="female">账户少于<input name=""  Style="width:50px;" />元时进行提醒（选择该项不填默认为0.00元）</label>
  		<br>
  		<input type="submit" value="确定" class="inp_btn">
		<input type="reset" value="取消" class="inp_btn">
		</div>
	</form>
</div>
</script>
<script type="text/javascript">
</script>
