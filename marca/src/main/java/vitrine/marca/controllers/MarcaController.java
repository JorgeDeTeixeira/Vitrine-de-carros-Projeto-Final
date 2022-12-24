package vitrine.marca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vitrine.marca.models.Marca;
import vitrine.marca.repositories.MarcaRepository;

@Controller
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;

	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/vitrine/marca/form")
	public String formulario() {
		return "marca/formMarca";
	}

	@PostMapping("/vitrine/marca")
	public String adicionarMarca(Marca marca) {
		System.out.println(marca);
		marcaRepository.save(marca);

		return "marca/marcaAdicionada";
	}
}
