package vitrine.marca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Index {
	@RequestMapping("/")
	public String index() {
		return "redirect:/vitrine";
	}
}
