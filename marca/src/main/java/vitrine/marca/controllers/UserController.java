package vitrine.marca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/User")
public class UserController {
	
	@GetMapping("/login-cadastro")
	public String formLogin() {
		return "User/Login-Cadastro";
	}
}
