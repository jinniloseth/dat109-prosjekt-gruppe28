package no.hvl.dat109.tester;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import no.hvl.dat109.Emne;
import no.hvl.dat109.Person;
import no.hvl.dat109.repo.EmneRepo;
import no.hvl.dat109.repo.PersonRepo;

@SpringBootTest
@AutoConfigureMockMvc
public class EmneControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EmneRepo emneRepo;

	@MockBean
	private PersonRepo personRepo;

	private Person student;
	private Person lektor;
	private Emne emne;

	@BeforeEach
	public void setup() {
		emne = new Emne("TDT4100", "Spring", "H2025", List.of());
		student = new Person(1, false, List.of(emne));
		lektor = new Person(2, true, List.of(emne));
	}

	@Test
	void testVisInnlogging() throws Exception {
		mockMvc.perform(get("/innlogging")).andExpect(status().isOk()).andExpect(view().name("innlogging"));
	}

	@Test
	void testLoggInnStudent() throws Exception {
		when(personRepo.findById(1)).thenReturn(Optional.of(student));
		when(emneRepo.findById(123)).thenReturn(Optional.of(emne));

		mockMvc.perform(post("/innlogging").param("brukernavn", "1").param("emnenr", "123").param("forelesningnr", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("vurderingskjema"));
	}

	@Test
	void testLoggInnLektor() throws Exception {
		when(personRepo.findById(2)).thenReturn(Optional.of(lektor));

		mockMvc.perform(post("/innlogging").param("brukernavn", "2").param("emnenr", "123").param("forelesningnr", "1"))
				.andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("oversikt"));
	}

	@Test
	void testGiVurdering() throws Exception {
		when(emneRepo.findById(123)).thenReturn(Optional.of(emne));
		when(personRepo.findById(1)).thenReturn(Optional.of(student));

		mockMvc.perform(post("/vurderingskjema").param("student", "1").param("emne", "123").param("forelesningnr", "0")
				.param("vurdering", "5")).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("innlogging"));
	}

	@Test
	void testVisEmneoversiktForLektor() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("lektor", lektor);

		mockMvc.perform(get("/emneoversikt").session(session)).andExpect(status().isOk())
				.andExpect(view().name("emneoversikt")).andExpect(model().attributeExists("emner"))
				.andExpect(model().attribute("lektor", lektor));
	}

	@Test
	void testVisForelesninger() throws Exception {
		MockHttpSession session = new MockHttpSession();
		session.setAttribute("lektor", lektor);

		mockMvc.perform(get("/forelesningsoversikt").session(session).flashAttr("emne", emne))
				.andExpect(status().isOk()).andExpect(view().name("forelesningsoversikt"))
				.andExpect(model().attribute("emne", emne)).andExpect(model().attributeExists("resultat"));
	}
}
