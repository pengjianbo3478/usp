package cn.obanks.usp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/template")
public class TemplateController {
	
	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search() {
		return "template/search";
	}
	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String register(Model mode) {
		
		return "template/search";
	}
	@RequestMapping(method = RequestMethod.GET, value = "add")
	public String add() {
		return "template/add";
	}
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public String add(Model mode) {
		
		return "template/add";
	}
	@RequestMapping(method = RequestMethod.GET, value = "detail")
	public String detail() {
		return "template/detail";
	}
	@RequestMapping(method = RequestMethod.POST, value = "detail")
	public String detail(Model mode) {
		return "template/detail";
	}
}
