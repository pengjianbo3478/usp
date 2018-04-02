package cn.obanks.usp.controller.usp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.obanks.service.AuthenticateService;

@Controller
@RequestMapping("/")
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private AuthenticateService authenticateService;

	@RequestMapping(method = RequestMethod.GET, value = "login")
	public String login() {
		return "login/login";
	}

	@RequestMapping(method = RequestMethod.POST, value = "login")
	public String login(@RequestParam String username, @RequestParam String password) {
		LOG.debug("username:{},password:{}", username, password);
		boolean result = this.authenticateService.authenticate(username, password);
		if (result) return "ci/index";
		else
			return "login/login";
	}
}
