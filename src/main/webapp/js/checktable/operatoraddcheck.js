	$(document).ready(function() {
		$(".operatoraddcheck").validate({
			rules : {
				acctCode : {
					remote : {
						type : "POST",
						url : "checkacctcode.do",
						dataType : "json",
						data : {
							acctCode : function() {
								return $("#_acctCode").val();
							}
						},
					},
					required : true,
					minlength : 6,
					maxlength : 32
				},
				userName : {
					remote : {
						type : "POST",
						url : "checkusername.do",
						dataType : "json",
						data : {
							acctCode : function() {
								return $("#_userName").val();
							}
						},
					},
					required : true,
					minlength : 2,
					maxlength : 32
				},
				acctPassword:{
					required : true,
					minlength : 6,
					maxlength : 32
				},
				confirmPassword:{
					equalTo : "#_acctPassword"
				},
				phone:{
					required : true,
					mobile:true,
				},
				email:{
					required : true,
					email:true
				},
				telNo:{
					required : true,
					mobile:true,
				}
			},
			messages : {
				acctCode : {
					required : "请填写登录名称",
					maxlength : "登录名称长度是6位到32",
					minlength : "登录名称长度是6位到32",
					remote : "登录名称已经存在"
				},
				userName : {
					required : "请填写操作员名称",
					minlength : "操作员名称长度是2位到32",
					maxlength : "操作员名称长度是2位到32",
					remote : "操作员名称已经存在"
				},
				acctPassword:{
					required : "请填写登录密码",
					maxlength : "登录密码长度是6位到32",
					minlength : "登录密码长度是6位到32",
				},
				confirmPassword:{
					equalTo : "两次密码输入不一致"
				},
				phone:{
					required : "请填写手机号码",
					mobile:"手机号码格式不正确",
				},
				email:{
					required : "请填写邮箱",
					email:"邮箱格式不正确"
				},
				telNo:{
					required : "请填写办公电话",
					mobile:"办公电话格式不正确",
				}
			}
		});
	});