package no.hvl.dat109.kontrollere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.Emne;
import no.hvl.dat109.Forelesning;
import no.hvl.dat109.Person;
import no.hvl.dat109.repo.EmneRepo;
import no.hvl.dat109.repo.PersonRepo;

public class EmneController {

	@Autowired
	EmneRepo emneRepo;

	@Autowired
	PersonRepo personRepo;

	public EmneController() {
	}

	@GetMapping("/innlogging")
	public String visInnlogging(Model model, @RequestParam(required = false) Integer emnenr, Integer forelesningnr) {
		model.addAttribute("emnenr", emnenr);
		model.addAttribute("forelesningsnr", forelesningnr);
		return "innlogging";
	}

	@PostMapping("/innlogging")
	public String loggInn(RedirectAttributes ra, HttpSession hs, String brukernavn,
			@RequestParam(required = false) Integer emnenr, @RequestParam(required = false) Integer forelesningnr) {
		int bn = Integer.parseInt(brukernavn);
		Person person = personRepo.findById(bn).orElse(null);

		Emne emne = (emnenr != null) ? emneRepo.findById(emnenr).orElse(null) : null;
		if (person != null) {
			if (person.erLektor()) {
				hs.setAttribute("lektor", person);
				return "redirect:oversikt";
			} else {
				ra.addFlashAttribute("student", person);
				ra.addFlashAttribute("emne", emne);
				ra.addFlashAttribute("forelesningnr", forelesningnr);
				return "redirect:vurderingskjema";
			}
		}
		return "innlogging";
	}

	@GetMapping("/vurderingskjema")
	public String hentVurdering(Model model, @ModelAttribute Person student, @ModelAttribute Emne emne,
			@ModelAttribute Integer forelesningnr) {
		model.addAttribute("student", student);
		model.addAttribute("emne", emne);
		model.addAttribute("forelesningnr", forelesningnr);
		return "vurderingskjema";
	}

	@PostMapping("/vurderingskjema")
	public String giVurdering(Model model, Person student, Emne emne, Integer forelesningnr, String vurdering) {
		if (student != null && emne != null && forelesningnr != null && !student.erLektor() && student.harEmne(emne)
				&& 0 < forelesningnr && forelesningnr <= emne.antallForelesninger()) {
			int v = Integer.parseInt(vurdering);
			emne.giVurdering(forelesningnr, v, student);
			return "redirect:innlogging"; // Implementere godkjenning
		}
		return "redirect:innlogging"; // Implementere feilmelding
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

//	private Emne finnEmne(String emnekode, String semester) {
//		List<Emne> emner = emneRepo.findAll();
//		for (Emne e : emner) {
//			if (emnekode.equals(e.getEmnekode()) && semester.equals(e.getSemester())) {
//				return e;
//			}
//		}
//		return null;
//	}
}
