package cn.obanks.usp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/account")
public class AccountController {
	
	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search() {
		return "account/search";
	}
	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(Model mode) {
		
		return "account/search";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "merge")
	public String merge() {
		return "account/merge";
	}

	@RequestMapping(method = RequestMethod.POST, value = "merge")
	public String merge(Model model) {
		return "account/merge";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "passwordmerge")
	public String passwordmerge() {
		return "account/passwordmerge";
	}

	@RequestMapping(method = RequestMethod.POST, value = "passwordmerge")
	public String passwordmerge(Model model, @RequestParam String oldPassword, @RequestParam String newPassword) {
		String view = "account/passwordmerge";
		//Long userId = OBSSOUtils.getUserId();
		/*try {
			this.operatorDetailsWrapperServiceRemote.changePassword(userId, oldPassword, newPassword);
		}
		catch (UserPasswordErrorException e) {
			model.addAttribute("msg", "你输入的旧密码与原密码不一致！请重新输入！");
			return view;
		}
		model.addAttribute("msg", "密码修改成功！");*/
		return view;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "detail")
	public String detail() {
		return "account/passwordmerge";
	}
}
