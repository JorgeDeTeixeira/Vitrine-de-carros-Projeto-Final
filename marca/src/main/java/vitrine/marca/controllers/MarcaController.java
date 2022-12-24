package vitrine.marca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import vitrine.marca.models.Marca;
import vitrine.marca.repositories.MarcaRepository;

@Controller
@RequestMapping("/vitrine")
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;

	@RequestMapping
	public String home() {
		return "home";
	}

	@GetMapping("/marca/form")
	public String formulario() {
		return "marca/formMarca";
	}

	@PostMapping("marca")
	public String adicionarMarca(Marca marca) {
		System.out.println(marca);
		marcaRepository.save(marca);

		return "marca/marcaAdicionada";
	}

	@GetMapping("marcas")
	public ModelAndView listarMarcas() {
		List<Marca> marcas = marcaRepository.findAll();
		ModelAndView mv = new ModelAndView("marca/lista");
		mv.addObject("marcas", marcas);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalharMarca(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Marca> opt = marcaRepository.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/vitrine/marcas");
			return md;
		}

		md.setViewName("marca/detalhes");
		Marca marca = opt.get();
		md.addObject("marca", marca);

		return md;
	}
}
