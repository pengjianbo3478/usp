package cn.obanks.usp.controller.usp;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/index")
public class IndexController {
	@RequestMapping(method = RequestMethod.GET, value = "index")
	public String index() {
		return "index/index";
	}
	
}
