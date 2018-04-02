$(document).ready(function() {
	$(".organizationaddcheck").validate({
		rules : {
			orgName : {
				required : true,
				minlength : 6,
				maxlength : 60
			},
			address : {
				required : true,
				minlength : 6,
				maxlength : 64
			},
			phone : {
				required : true,
				mobile : true,
			},
			contacts : {
				required : true,
				minlength : 2,
				maxlength : 16
			},
			prorate : {
				required : true,
				minlength : 2,
				maxlength : 8
			},
			transactionChannel : {
				required : true,
			},
			status : {
				required : true,
			}
		},
		messages : {
			orgName : {
				required : "请填写机构名称",
				maxlength : "机构名称超长",
				minlength : "机构名称格式不正确"
			},
			address : {
				required : "请填写联系人地址",
				minlength : "联系人地址格式不正确",
				maxlength : "联系人地址超长"
			},
			phone : {
				required : "请填写手机号码",
				mobile : "手机格式不正确"
			},
			contacts : {
				required : "请填写联系人",
				minlength : "联系人格式不正确",
				maxlength : "联系人超长"
			},
			prorate : {
				required : "请填写机构分润比例",
				minlength : "格式不正确",
				maxlength : "格式不正确"
			},
			transactionChannel : {
				required : "请选择交易渠道"
			},
			status : {
				required : "请选择状态"
			}
		}
	});
});