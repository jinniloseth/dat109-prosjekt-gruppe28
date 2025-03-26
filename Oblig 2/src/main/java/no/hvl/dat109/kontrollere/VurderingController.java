package no.hvl.dat109.kontrollere;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import no.hvl.dat109.Forelesning;
import no.hvl.dat109.repo.ForelesningRepo;

@Controller
public class VurderingController {

	@Autowired
	ForelesningRepo forelesningRepository;

//	@Autowired
//	PassordService passordService = new PassordService();
//
//	@PostMapping("/paamelding")
//	public String taImotRegistrering(@ModelAttribute @Valid Deltager d, BindingResult bindingResult, RedirectAttributes ra,
//			Model model, HttpServletRequest request) {
//	
//		 if (bindingResult.hasErrors()) {
//	            // Samler error meldinger
//	            List<String> feilmeldinger = bindingResult.getAllErrors()
//	                    .stream()
//	                    .map(e -> e.getDefaultMessage())
//	                    .toList();
//	            model.addAttribute("feilmeldinger", feilmeldinger);
//	            return "redirect:paameldingview";
//	        }
//
//		if (deltagerRepository.findByMobil(d.getMobil()) != null) {
//			ra.addFlashAttribute("feilmeldinger", "Mobilnummeret er allerede registrert.");
//			return "redirect:paameldingview";
//		}
//		
//		String passord = request.getParameter("passord");
//		String passordRepetert = request.getParameter("passordRepetert");
//
//		if(!passord.equals(passordRepetert)) {
//			ra.addFlashAttribute("feilmeldinger", "Passordene stemmer ikke overens.");
//			return "redirect:paameldingview";
//		}
//		
//		
//		String salt = passordService.genererTilfeldigSalt();
//		String hash = passordService.hashMedSalt(passord, salt);
//		
//		Passord p = new Passord(hash, salt);
//
//		DeltagerMedPass nyDeltager = new DeltagerMedPass(d.getFornavn(), d.getEtternavn(), d.getMobil(), p, d.getKjonn());
//
//		deltagerRepository.save(nyDeltager);
//		LoginUtil.loggInnBruker(request, d.getMobil());
//		ra.addFlashAttribute("person", d);
//		return "redirect:resultat";
//
//	}

	
	
	@GetMapping("/vurderingskjema")
	public String vurderingsSkjema(Model model, @RequestParam String emnekode, @RequestParam int forelesningsnr) {
		model.addAttribute("emnekode", emnekode);
		model.addAttribute("forelesningsnr", forelesningsnr);
		return "vurderingskjema";
	}
	
	@PostMapping("/vurderingskjema")
	public String sendVurderingSkjema(Model model, RedirectAttributes ra){
		
		
		forelesningRepository.save(forelensing objekt);
		
		
		return "vurderingskjema";
	}

}
