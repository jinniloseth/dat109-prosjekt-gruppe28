package no.hvl.dat109.tester;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import no.hvl.dat109.Emne;
import no.hvl.dat109.Person;
import no.hvl.dat109.kontrollere.EmneController;
import no.hvl.dat109.repo.EmneRepo;
import no.hvl.dat109.repo.PersonRepo;

@SpringBootTest
public class EmneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmneRepo emneRepo;

    @MockBean
    private PersonRepo personRepo;

    @InjectMocks
    private EmneController emneController;

    private Person student;
    private Person lektor;
    private Emne emne;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(emneController).build();

        // Setup test data
        student = new Person(1, "student1", "Student One");
        lektor = new Person(2, "lektor1", "Lektor One");
        emne = new Emne("TDT4100", "Spring", "2025");
    }

    @Test
    void testVisInnlogging() throws Exception {
        mockMvc.perform(get("/innlogging"))
                .andExpect(status().isOk())
                .andExpect(view().name("innlogging"));
    }

    @Test
    void testLoggInnStudent() throws Exception {
        when(personRepo.findById(1)).thenReturn(java.util.Optional.of(student));
        when(emneRepo.findById(123)).thenReturn(java.util.Optional.of(emne));

        mockMvc.perform(post("/innlogging")
                .param("brukernavn", "1")
                .param("emnenr", "123")
                .param("forelesningnr", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("vurderingskjema"));
    }

    @Test
    void testLoggInnLektor() throws Exception {
        when(personRepo.findById(2)).thenReturn(java.util.Optional.of(lektor));

        mockMvc.perform(post("/innlogging")
                .param("brukernavn", "2")
                .param("emnenr", "123")
                .param("forelesningnr", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("oversikt"));
    }

    @Test
    void testHentVurdering() throws Exception {
        mockMvc.perform(get("/vurderingskjema")
                .param("student", "1")
                .param("emne", "TDT4100")
                .param("forelesningnr", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("vurderingskjema"))
                .andExpect(model().attribute("student", student))
                .andExpect(model().attribute("emne", emne))
                .andExpect(model().attribute("forelesningnr", 1));
    }

    @Test
    void testGiVurdering() throws Exception {
        when(personRepo.findById(1)).thenReturn(java.util.Optional.of(student));
        when(emneRepo.findById(123)).thenReturn(java.util.Optional.of(emne));

        mockMvc.perform(post("/vurderingskjema")
                .param("student", "1")
                .param("emne", "TDT4100")
                .param("forelesningnr", "1")
                .param("vurdering", "5"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("innlogging"));
    }

    @Test
    void testVisEmneoversiktForLektor() throws Exception {
        when(personRepo.findById(2)).thenReturn(java.util.Optional.of(lektor));

        mockMvc.perform(get("/emneoversikt")
                .param("lektor", "2"))
                .andExpect(status().isOk())
                .andExpect(view().name("emneoversikt"))
                .andExpect(model().attribute("emner", lektor.getEmner()));
    }

    @Test
    void testVisForelesninger() throws Exception {
        when(emneRepo.findById(123)).thenReturn(java.util.Optional.of(emne));

        mockMvc.perform(get("/forelesningsoversikt")
                .param("emne", "TDT4100"))
                .andExpect(status().isOk())
                .andExpect(view().name("forelesningsoversikt"))
                .andExpect(model().attribute("emne", emne))
                .andExpect(model().attribute("resultat", emne.getResultat()));
    }
}
