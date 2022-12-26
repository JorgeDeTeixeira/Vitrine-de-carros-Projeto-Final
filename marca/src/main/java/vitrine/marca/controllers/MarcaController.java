package vitrine.marca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.validation.Valid;
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
	public String formulario(Marca marca) {
		return "marca/formMarca";
	}

	@PostMapping("marca")
	public String salvarMarca(@Valid Marca marca, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			return formulario(marca);
		}

		marcaRepository.save(marca);
		attributes.addFlashAttribute("mensagem", "Marca " + marca.getNome() + " adicionada com sucesso!");

		return "redirect:/vitrine/marcas";
	}

	@GetMapping("marcas")
	public ModelAndView listarMarcas() {
		List<Marca> marcas = marcaRepository.findAll();
		ModelAndView mv = new ModelAndView("marca/lista");
		mv.addObject("marcas", marcas);
		return mv;
	}

	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id, Carro carro) {
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
	public String apagarMarca(@PathVariable Long id, RedirectAttributes attributes) {

		Optional<Marca> opt = marcaRepository.findById(id);

		if (!opt.isEmpty()) {
			Marca marca = opt.get();

			List<Carro> carros = carroRepository.findByMarca(marca);

			carroRepository.deleteAll(carros);
			marcaRepository.delete(marca);
			attributes.addFlashAttribute("mensagem", "Marca " + marca.getNome() + " removida com sucesso!");
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

	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarMarca(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Marca> opt = marcaRepository.findById(id);
		if (opt.isEmpty()) {
			md.setViewName("redirect:/vitrine/marcas");
			return md;
		}

		Marca marca = opt.get();
		md.setViewName("marca/formMarca");
		md.addObject("marca", marca);

		return md;
	}

	@GetMapping("/{idMarca}/carros/{idCarro}/selecionar")
	public ModelAndView selecionarCarro(@PathVariable Long idMarca, @PathVariable Long idCarro) {
		ModelAndView md = new ModelAndView();

		Optional<Marca> optMarca = marcaRepository.findById(idMarca);
		Optional<Carro> optCarro = carroRepository.findById(idCarro);

		if (optMarca.isEmpty() || optCarro.isEmpty()) {
			md.setViewName("redirect:/vitrine/marcas");
			return md;
		}

		Marca marca = optMarca.get();
		Carro carro = optCarro.get();

		if (marca.getId() != carro.getMarca().getId()) {
			md.setViewName("redirect:/vitrine/marcas");
			return md;
		}

		md.setViewName("marca/detalhes");
		md.addObject("carro", carro);
		md.addObject("marca", marca);
		md.addObject("carros", carroRepository.findByMarca(marca));

		return md;
		
	}

}
