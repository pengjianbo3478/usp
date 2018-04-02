package cn.obanks.usp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@RequestMapping(method = RequestMethod.GET, value = "register")
	public String register() {
		return "register/register";
	}
	@RequestMapping(method = RequestMethod.POST, value = "register")
	public String register(Model mode) {
		
		return "register/register";
	}
}
