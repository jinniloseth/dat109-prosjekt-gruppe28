package no.hvl.dat109.kontrollere;

import java.time.LocalDate;
import java.util.List;

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

		System.out.println("BRUKERNAVN ERRRR: " + brukernavn);

		Person person = personRepo.findById(brukernavn).orElse(null);

		System.out.println("ER LEKTOR ???? : " + person.erLektor());
		
		if (person.erLektor()) {
			hs.setAttribute("lektor", person);
			ra.addFlashAttribute("lektor", person);
			return "redirect:emneoversikt";
		}

		Emne emne = emneRepo.findById(emnenr).orElse(null);
		Forelesning forelesning = forelesningRepo.findByForelesningnrAndEmne(forelesningnr, emne).orElse(null);

		System.out.println("Henter emne med emnenr INNLOGINGPOSTMAPPING = " + emnenr);
		System.out.println("Henter emne med person INNLOGINGPOSTMAPPING = " + brukernavn);
		System.out.println("Henter emne med forelesning INNLOGINGPOSTMAPPING = " + forelesningnr);

		if (person != null) {
			if (!person.harEmne(emne)) {
				ra.addFlashAttribute("feilmelding", "Student ved studentnummer: " + person.getBrukernavn()
						+ " eksisterer ikke i emnet: " + emne.getEmnekode());
				return "redirect:innlogging?emnenr=" + emne.getEmnenr() + "&forelesningnr="
						+ forelesning.getForelesningnr();
			} else if (forelesning.harGittTilbakemelding(person)) {
				ra.addFlashAttribute("feilmelding",
						"Student ved studentnummer: " + person.getBrukernavn()
								+ " har allerede gitt tilbakemelding i : " + emne.getEmnekode() + " forelesning nr: "
								+ forelesning.getForelesningnr());
				return "redirect:innlogging?emnenr=" + emne.getEmnenr() + "&forelesningnr="
						+ forelesning.getForelesningnr();
			}

			ra.addFlashAttribute("student", person);
			ra.addFlashAttribute("emne", emne);
			ra.addFlashAttribute("forelesningnr", forelesning.getForelesningnr());

			System.out.println("Emne som blir satt i session: " + emne.toString());
			System.out.println("STUDENT I SESSION FØR:" + person.toString());
			System.out.println("forelesning I SESSION FØR:" + forelesning.toString());

			hs.setAttribute("student", person);
			hs.setAttribute("emne", emne);
			hs.setAttribute("forelesning", forelesning);

			System.out.println("Emne som blir satt i session: " + emne);
			System.out.println("STUDENT I SESSION Eteter:" + person.toString());
			System.out.println("forelesning I SESSION etter:" + forelesning.toString());

			return "redirect:vurderingskjema";
		}

		System.out.println("RETURNERER TIL INNLOGGING FEIL HAR SKJEDD");

		return "innlogging";

	}

	@GetMapping("/vurderingskjema")
	public String hentVurdering(Model model, HttpSession session) {
		Person student = (Person) session.getAttribute("student");
		Emne emne = (Emne) session.getAttribute("emne");
		Forelesning forelesning = (Forelesning) session.getAttribute("forelesning");

		System.out.println("Hentet fra session: " + student.toString());
		System.out.println("Hentet fra session: " + emne.toString());
		System.out.println("Hentet fra session: " + forelesning.toString());

		model.addAttribute("student", student);
		model.addAttribute("emne", emne);
		model.addAttribute("forelesning", forelesning);

		return "vurderingskjema";
	}

	@PostMapping("/vurderingskjema")
	public String giVurdering(Model model, @RequestParam("vurdering") String vurdering,
			@RequestParam("student") Integer studentId, @RequestParam("emne") Integer emneId,
			@RequestParam("forelesning") Integer forelesningnr, RedirectAttributes ra) {

		Person student = personRepo.findById(studentId).orElse(null);
		Emne emne = emneRepo.findById(emneId).orElse(null);
		Forelesning forelesning = forelesningRepo.findById(forelesningnr).orElse(null);

		System.out.println("STUDENT:" + student.toString());
		System.out.println("EMNE: " + emne.toString());
		System.out.println("FORLESNING: " + forelesning.toString());
		
		if (forelesning.getDato().isBefore(LocalDate.now()) || forelesning.getDato().isEqual(LocalDate.now())) {
			if (student != null && emne != null && forelesning != null && !student.erLektor()) {

				student.getEmner().stream().forEach(System.out::println);

				System.out.println("FORELENSISNSINSNSINFG: " + forelesning.toString());

				int v = Integer.parseInt(vurdering);

				System.out.println("FØR LAGRING");
				System.out.println("Tilbakemelding: " + vurdering);
				System.out.println("forelesnings: " + forelesning.toString());
				System.out.println("emne: " + emne.toString());

				Tilbakemelding t = new Tilbakemelding(v, student, forelesning);

				forelesning.setEmne(emne);
				forelesning.giVurdering(t, student, forelesning);

				if (emne.getForelesninger() != null && !emne.getForelesninger().isEmpty()) {
					double nyttResultat = emne.getForelesninger().stream().mapToDouble(Forelesning::getResultat).average()
							.orElse(0);
					forelesning.setResultat(nyttResultat);
				}

				ra.addFlashAttribute("resultat",
						"Registrert: <br>" + "Student: " + student.getBrukernavn() + "<br>" + "Vurdering gitt: " + v
								+ "<br>" + "Emne: " + emne.getEmnekode() + "<br>" + "Forelesningsnr: "
								+ forelesning.getForelesningnr());

				forelesningRepo.save(forelesning);
			}

		}

		return "redirect:innlogging";
	}

	@GetMapping("/emneoversikt")
	public String visEmner(Model model, HttpSession hs) {
		Person lektor = (Person) hs.getAttribute("lektor");
		System.out.println("EMNEOVERSIKT LEKTOR OBJEKT = " + lektor.toString());
		if (lektor != null && lektor.erLektor()) {
			model.addAttribute("lektor", lektor);
			List<Emne> emner = lektor.getEmner();
			model.addAttribute("emner", emner);
			return "emneoversikt";

//	select * from tilbakemelding, forelesning where tilbakemelding.forelesningid = forelesning.forelesningnr;

		}
		return "redirect:innlogging";
	}

}