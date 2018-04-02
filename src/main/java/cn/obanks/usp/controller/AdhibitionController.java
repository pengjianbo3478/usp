package cn.obanks.usp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/adhibition")
public class AdhibitionController {
	
	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search() {
		return "adhibition/search";
	}
	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(Model mode) {
		
		return "adhibition/search";
	}
	@RequestMapping(method = RequestMethod.GET, value = "add")
	public String add() {
		return "adhibition/add";
	}
	@RequestMapping(method = RequestMethod.POST, value = "add")
	public String add(Model mode) {
		
		return "adhibition/add";
	}
	@RequestMapping(method = RequestMethod.GET, value = "detail")
	public String detail() {
		return "adhiobition/add";
	}
	@RequestMapping(method = RequestMethod.GET, value = "list")
	public String list(Model mode) {
		return "adhibition/list";
	}
}
