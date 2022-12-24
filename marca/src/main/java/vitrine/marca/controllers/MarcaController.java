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

import vitrine.marca.models.Carro;
import vitrine.marca.models.Marca;
import vitrine.marca.repositories.CarroRepository;
import vitrine.marca.repositories.MarcaRepository;

@Controller
@RequestMapping("/vitrine")
public class MarcaController {

	@Autowired
	private MarcaRepository marcaRepository;
	@Autowired
	private CarroRepository carroRepository;

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
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Marca> opt = marcaRepository.findById(id);

		if (opt.isEmpty()) {
			md.setViewName("redirect:/vitrine/marcas");
			return md;
		}

		md.setViewName("marca/detalhes");
		Marca marca = opt.get();
		md.addObject("marca", marca);

		List<Carro> carros = carroRepository.findByMarca(marca);
		md.addObject("carros", carros);

		return md;
	}

	@PostMapping("/{idMarca}")
	public String salvarCarro(@PathVariable Long idMarca, Carro carro) {

		Optional<Marca> opt = marcaRepository.findById(idMarca);
		if (opt.isEmpty()) {
			return "redirect:/vitrine/marcas";
		}

		Marca marca = opt.get();
		carro.setMarca(marca);

		carroRepository.save(carro);

		return "redirect:/vitrine/{idMarca}";
	}

	@GetMapping("/{id}/remover")
	public String apagarMarca(@PathVariable Long id) {

		Optional<Marca> opt = marcaRepository.findById(id);

		if (!opt.isEmpty()) {
			Marca marca = opt.get();

			List<Carro> carros = carroRepository.findByMarca(marca);

			carroRepository.deleteAll(carros);
			marcaRepository.delete(marca);
		}

		return "redirect:/vitrine/marcas";
	}

	@GetMapping("/{idMarca}/carros/{idCarro}/remover")
	public String apagarMarca(@PathVariable Long idMarca, @PathVariable Long idCarro) {

		Optional<Carro> opt = carroRepository.findById(idCarro);

		if (!opt.isEmpty()) {
			Carro carro = opt.get();
			carroRepository.delete(carro);
		}

		return "redirect:/vitrine/{idMarca}";
	}

}
