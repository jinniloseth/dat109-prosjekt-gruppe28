package idk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.hjelpeklasser.LoginUtil;

@Controller
public class InnloggingKontroller {

	@Autowired
	DeltagerRepo deltagerRepository;

	PassordService passordservice = new PassordService();;

	@GetMapping("/innlogging")
	public String innlogging() {
		return "loginView";
	}

	@PostMapping("/innlogging")
	public String loggInn(@RequestParam String mobil, @RequestParam String passord, HttpServletRequest request,
			RedirectAttributes ra) {

		DeltagerMedPass d = deltagerRepository.findByMobil(mobil);

		if (d != null) {
			if (passordservice.erKorrektPassord(passord, d.getPassord().getSalt(), d.getPassord().getHash())) {
				LoginUtil.loggInnBruker(request, mobil);

				return "redirect:deltagerliste";
			} else {
				ra.addFlashAttribute("mismatchError", "Brukernavn og/eller passord er ukorrekt");
				return "redirect:innlogging";
			}

		} else {
			ra.addFlashAttribute("mismatchError", "Brukernavn og/eller passord er ukorrekt");
			return "redirect:innlogging";
		}
	}

	@PostMapping("/utlogging")
	public String loggUt(HttpServletRequest request, RedirectAttributes ra) {

		HttpSession session = request.getSession();

		LoginUtil.loggUtBruker(session);
		ra.addFlashAttribute("loggetUt", "Du har blitt logget ut.");

		return "redirect:innlogging";
	}

	@GetMapping("/")
	public String redirectToStart() {
		return "redirect:/startSide";
	}

	@GetMapping("/startSide")
	public String start() {
		return "startSideView";
	}
}
