package vitrine.marca.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vitrine.marca.models.RoleModel;
import vitrine.marca.models.UserModel;
import vitrine.marca.repositories.RoleRepository;
import vitrine.marca.repositories.UserRepository;

@Controller
@RequestMapping("/User")
public class UserController {

	@Autowired
	 private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@GetMapping("/login-cadastro")
	public String formLogin() {
		return "User/Login-Cadastro";
	}
	
	@PostMapping("/cadastro")
	public String salvar(UserModel usuario) {
		
		ArrayList<RoleModel> roles = new ArrayList<RoleModel>();
		RoleModel role = roleRepository.findByRoleName("ROLE_EMPRESA"); 
		roles.add(role);

		usuario.setRoles(roles);

		usuario.setPassword(new BCryptPasswordEncoder().encode(usuario.getPassword()));

		userRepository.save(usuario);

		return "redirect:/login";
	}

}
