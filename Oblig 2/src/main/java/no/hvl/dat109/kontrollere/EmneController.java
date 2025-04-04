package no.hvl.dat109.kontrollere;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String loggInn(RedirectAttributes ra, String brukernavn, @RequestParam(required = false) Integer emnenr, @RequestParam(required = false) Integer forelesningnr) {
		int bn = Integer.parseInt(brukernavn);
		Person person = personRepo.findById(bn).orElse(null);
		
		Emne emne = (emnenr != null) ? emneRepo.findById(emnenr).orElse(null) : null;
		Forelesning forelesning = (forelesningnr != null) ? emne.finnForelesning(forelesningnr) : null;
		if (person != null) {
			if (person.erLektor()) {
				ra.addFlashAttribute("lektor", person);
				return "redirect:oversikt"; // skal vise oversikt over fag, så skal man kunne velge fag
			} else if (person.harEmne(emne) && forelesningnr != null) {
				ra.addFlashAttribute("student", person);
				ra.addFlashAttribute("emne", emne);
				ra.addFlashAttribute("forelesning", forelesning);
				return "redirect:vurderingskjema";
			}
		}
		return "innlogging";
	}
	
	@GetMapping("/vurderingskjema")
	public String hentVurdering(Model model, @ModelAttribute("student") Person student, @ModelAttribute("emne") Emne emne, @ModelAttribute("forelesning") Forelesning forelesning) {
		model.addAttribute("student", student);
		model.addAttribute("emne", emne);
		model.addAttribute("forelesning", forelesning);
		return "vurderingskjema";
	}

	@PostMapping("/vurderingskjema")
	public String giVurdering(Model model, Person student, Emne emne, Forelesning forelesning) {
		
		
		return "redirect:innlogging"; // Hvor skal man gå etter gitt vurdering?
	}
	
	public boolean giVurdering(String emnekode, String semester, int forelesningsnr, int tilbakemelding,
			int brukernavn) {
		Emne emne = finnEmne(emnekode, semester);
		Person student = personRepo.findById(brukernavn).orElse(null);
		if (emne != null && student != null && student.harEmne(emne) && !student.erLektor()) {
			return emne.giVurdering(forelesningsnr, tilbakemelding, student);
		}
		return false;
	}

	@GetMapping("/oversikt")
	public String visEmner(Model model) {
		String s = (String) model.getAttribute("brukernavn");
		Person student = personRepo.findById(Integer.parseInt(s)).orElse(null);
		if (student != null) {
			model.addAttribute("emner", student.getEmner());
		}

		return "oversikt";
	}

	private Emne finnEmne(String emnekode, String semester) {
		List<Emne> emner = emneRepo.findAll();
		for (Emne e : emner) {
			if (emnekode.equals(e.getEmnekode()) && semester.equals(e.getSemester())) {
				return e;
			}
		}
		return null;
	}
	
	@GetMapping("/forelesninger")
    public String visForelesninger(@RequestParam String emnekode, @RequestParam String semester, Model model) {
        Emne e = .finnEmne(emnekode, semester);
        if (e != null) {
            model.addAttribute("emne", e);
            model.addAttribute("forelesninger", e.getForelesninger());
            return "forelesningsoversikt";
        }
        return "emneoversikt";
    }

//	@GetMapping("/resultat")
//	public String getResultat(Model model, @RequestParam String emnekode, @RequestParam int forelesningsnr) {
//		model.addAttribute("emnekode", emnekode);
//		model.addAttribute("forelesningsnr", forelesningsnr);
//
//		Emne emne = emneRepo.findByEmnekode(emnekode);
//		
//		for (Forelesning f : emne.ge)
//
//		if (f != null) {
//			model.addAttribute("forelesning", f);
//		}
//
//		model.addAllAttributes(resultat);
//
//		return "ressultat";
//	}

//	public double getResultat(String emnekode, int forelesningsnr) {
//		return finnEmne(emnekode).getResultat(int forelesningsnr);
//
//		double resultat = null;
//		
//		Forelesning fo = forelesningRepository.findByEmnekodeAndForelesningsnr(emnekode, forelesningsnr);
//		
//		for(Forelesning f : forelesninger) {
//			if(f.getEmnekode().equals(emnekode) && f.getForelesningsnr() == forelesningsnr) {
//				fo = f;
//			}
//		}
//		
//		List<Tilbakemelding> tilbakemeldinger = fo.getTilbakemelding();
//		
//		for(Tilbakemelding t : tilbakemeldinger) {
//			resultat += t.getTilbakemelding();
//		}
//		
//		
//		return resultat;
//	}

//	public void giVurdering(int studentId, String emnekode, int forelesningsnr, int tilbakemelding){
//		Forelesning f = forelesninger.stream()
//									 .filter(fo -> fo.getEmnekode().equals(emnekode))
//									 .filter(a -> a.getForelesningsnr() == forelesningsnr)
//									 .findFirst()
//									 .orElse(null);
//		
//		if(f != null) {
//			Tilbakemelding t = new Tilbakemelding(tilbakemelding, studentId);
//			f.registrerTilbakemelding(t);
//		}
//	}

//	public List<Tilbakemelding> getTilbakemeldinger(String emnekode){
//		
//	}

//	@GetMapping("/vurderingskjema")
//	public String vurderingsSkjema(Model model, @RequestParam String emnekode, @RequestParam int forelesningsnr) {
//		model.addAttribute("emnekode", emnekode);
//		model.addAttribute("forelesningsnr", forelesningsnr);
//		return "vurderingskjema";
//	}
//	
//	@PostMapping("/vurderingskjema")
//	public String sendVurderingSkjema(Model model, RedirectAttributes ra){
//		
//		
//		emneRepo.save(forelensing, objekt);
//		
//		
//		return "vurderingskjema";
//	}

}
