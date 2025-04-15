package no.hvl.dat109.kontrollere;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.Emne;
import no.hvl.dat109.Forelesning;
import no.hvl.dat109.Person;
import no.hvl.dat109.Tilbakemelding;
import no.hvl.dat109.repo.EmneRepo;
import no.hvl.dat109.repo.ForelesningRepo;
import no.hvl.dat109.repo.PersonRepo;
import no.hvl.dat109.repo.TilbakemeldingRepo;

@Controller
public class EmneController {

	@Autowired
	EmneRepo emneRepo;

	@Autowired
	PersonRepo personRepo;

	@Autowired
	ForelesningRepo forelesningRepo;

	@Autowired
	TilbakemeldingRepo tilbakemeldingRepo;

	@GetMapping("/innlogging")
	public String visInnlogging(Model model, @RequestParam(required = false) Integer emnenr, Integer forelesningnr) {
		model.addAttribute("emnenr", emnenr);
		model.addAttribute("forelesningsnr", forelesningnr);
		return "innlogging";
	}

	@PostMapping("/innlogging")
	public String loggInn(RedirectAttributes ra, HttpSession hs, @RequestParam("brukernavn") int brukernavn,
			@RequestParam(required = false) Integer emnenr, @RequestParam(required = false) Integer forelesningnr) {
		
		Person person = personRepo.findById(brukernavn).orElse(null);
		
		
		System.out.println("Emnenr er " + emnenr);
		
		
		Emne emne = (emnenr != null) ? emneRepo.findById(emnenr).orElse(null) : null;

		
		System.out.println("Henter emne med emnenr INNLOGINGPOSTMAPPING = " + emnenr);
		

		
		if (person != null) {
			if (person.erLektor()) {
				hs.setAttribute("lektor", person);
				return "redirect:oversikt";
			} else {
				ra.addFlashAttribute("student", person);
				ra.addFlashAttribute("emne", emne);
				System.out.println("Emne som blir satt i session: " + emne.toString());
				ra.addFlashAttribute("forelesningnr", forelesningnr);
				hs.setAttribute("student", person);
				hs.setAttribute("emne", emne);
				hs.setAttribute("forelesningnr", forelesningnr);
				System.out.println("Emne som blir satt i session: " + emne);

				return "redirect:vurderingskjema";
			}
		}
		return "innlogging";
	}

	@GetMapping("/vurderingskjema")
	public String hentVurdering(Model model, HttpSession session) {
		Person student = (Person) session.getAttribute("student");
		Emne emne = (Emne) session.getAttribute("emne");
		Integer forelesningnr = (Integer) session.getAttribute("forelesningnr");

		model.addAttribute("student", student);
		model.addAttribute("emne", emne);
		model.addAttribute("forelesningnr", forelesningnr);

		return "vurderingskjema";
	}

	@PostMapping("/vurderingskjema")
	public String giVurdering(Model model,
			@RequestParam("vurdering") String vurdering,
			@RequestParam("student") Integer studentId,
			@RequestParam("emne") Integer emneId,
			@RequestParam("forelesningnr") Integer forelesningnr) {

		Person student = personRepo.findById(studentId).orElse(null);
		Emne emne = emneRepo.findById(emneId).orElse(null);
		Optional<Forelesning> forelesningOpt = forelesningRepo.findById(forelesningnr);

		System.out.println("STUDENT:" + student.toString());
		System.out.println("EMNE: " + emne.toString());
		System.out.println("FORLESNING: " + forelesningOpt.toString());
		

		if (student != null && emne != null && forelesningOpt.isPresent() && !student.erLektor()) {

			Forelesning forelesning = forelesningOpt.get();
			System.out.println("FORELENSISNSINSNSINFG: " + forelesning.toString());
			
			int v = Integer.parseInt(vurdering);

			
			
			Tilbakemelding t = new Tilbakemelding(v, student, forelesning);
			
			System.out.println("FØR LAGRING");
			System.out.println("Tilbakemelding: " + t.toString());
			
			
			forelesning.registrerTilbakemelding(t);
			tilbakemeldingRepo.save(t);

			System.out.println("RESULTATET BLIR: " + forelesning.getResultat() + " I FORELESNINGNR " + forelesning.getForelesningnr());
			forelesning.oppdaterResultat(t.getTilbakemelding()); // setter resultat
			System.out.println("RESULTATET BLE: " + forelesning.getResultat() + " I FORELESNINGNR " + forelesning.getForelesningnr());
			
			forelesningRepo.save(forelesning);
		}

		return "redirect:innlogging";
	}

	@GetMapping("/emneoversikt")
	public String visEmner(Model model, HttpSession hs) {
		Person lektor = (Person) hs.getAttribute("lektor");
		if (lektor != null && lektor.erLektor()) {
			model.addAttribute("lektor", lektor);
			List<Emne> emner = lektor.getEmner();
			model.addAttribute("emner", emner);
			return "emneoversikt";
		}
		return "redirect:innlogging";
	}

	@GetMapping("/forelesningsoversikt")
	public String visForelesninger(Model model, HttpSession hs, Emne emne) {
		Person lektor = (Person) hs.getAttribute("lektor");
		if (emne != null && lektor != null && lektor.erLektor() && lektor.harEmne(emne)) {
			model.addAttribute("emne", emne);
			model.addAttribute("resultat", emne.getResultat());
			return "forelesningsoversikt";
		}
		return "emneoversikt";
	}
}