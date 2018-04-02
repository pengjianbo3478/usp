package cn.obanks.usp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/consumer")
public class ConsumerController {
	
	@RequestMapping(method = RequestMethod.GET, value = "search")
	public String search() {
		return "consumer/search";
	}
/*	@RequestMapping(method = RequestMethod.POST, value = "search")
	public String search(Model mode) {
		
		return "consumer/search";
	}*/
	
	@RequestMapping(method = RequestMethod.GET, value = "singlesearch")
	public String singlesearch() {
		return "consumer/singlesearch";
	}
	@RequestMapping(method = RequestMethod.GET, value = "masssearch")
	public String masssearch() {
		return "consumer/singlesearch";
	}
}
