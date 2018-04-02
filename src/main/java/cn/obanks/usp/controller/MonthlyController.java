package cn.obanks.usp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/monthly")
public class MonthlyController {
	
	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search() {
		return "monthly/search";
	}
	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(Model mode) {
		
		return "monthly/search";
	}
}
