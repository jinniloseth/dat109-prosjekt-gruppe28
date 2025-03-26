package no.hvl.dat109.kontrollere;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import no.hvl.dat109.Forelesning;
import no.hvl.dat109.Tilbakemeldingsystem;
import no.hvl.dat109.repo.EmneRepo;

@Controller
public class ResultatController {

	@Autowired
	EmneRepo emneRepo;
//
//	@Autowired
//	PassordService passordService = new PassordService();
//
	private Tilbakemeldingsystem system;

	@GetMapping("/resultat")
	public String vurderingsSkjema(Model model, @RequestParam String emnekode, @RequestParam int forelesningsnr) {
		system = new Tilbakemeldingsystem();
		model.addAttribute("emnekode", emnekode);
		model.addAttribute("forelesningsnr", forelesningsnr);

		List<Forelesning> forelesninger = emneRepo.findByEmnekode(emnekode);
		
		Forelesning f = forelesninger.stream()
						.filter(fo -> fo.getEmnekode().equals(emnekode))
						.filter(a -> a.getForelesningsnr() == forelesningsnr)
						.findFirst()
						.orElse(null);

		if (f != null) {
			model.addAttribute("forelesning", f);
		}

		
		
		HashMap<String, Integer> resultat = system.getResultatEnkelForelesning(emnekode, forelesningsnr);

		model.addAllAttributes(resultat);

		return "ressultat";
	}

}
