	$(document).ready(function() {
		$(".operatormergecheck").validate({
			rules : {
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
				userName : {
					required : "请填写操作员名称",
					minlength : "操作员名称长度是2位到32",
					maxlength : "操作员名称长度是2位到32",
					remote : "操作员名称已经存在"
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