package no.hvl.dat109.tester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import no.hvl.dat109.Person;
import no.hvl.dat109.kontrollere.EmneController;
import no.hvl.dat109.repo.EmneRepo;
import no.hvl.dat109.repo.ForelesningRepo;
import no.hvl.dat109.repo.PersonRepo;
import no.hvl.dat109.repo.TilbakemeldingRepo;

public class EmneControllerTest {

    @Mock
    private EmneRepo emneRepo;

    @Mock
    private PersonRepo personRepo;

    @Mock
    private ForelesningRepo forelesningRepo;

    @Mock
    private TilbakemeldingRepo tilbakemeldingRepo;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes ra;

    @Mock
    private HttpSession session;

    @InjectMocks
    private EmneController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testVisInnlogging() {
        String view = controller.visInnlogging(model, 1, 2);
        verify(model).addAttribute("emnenr", 1);
        verify(model).addAttribute("forelesningsnr", 2);
        assertEquals("innlogging", view);
    }

    @Test
    public void testLoggInnLektorRedirect() {
        Person lektor = new Person(1001, true);
        when(personRepo.findById(1001)).thenReturn(Optional.of(lektor));

        String result = controller.loggInn(ra, session, 1001, null, null);

        verify(session).setAttribute("lektor", lektor);
        verify(ra).addFlashAttribute("lektor", lektor);
        assertEquals("redirect:emneoversikt", result);
    }

    @Test
    public void testVisEmnerLektor() {
        Person lektor = mock(Person.class);
        when(lektor.erLektor()).thenReturn(true);
        when(lektor.getEmner()).thenReturn(List.of());

        when(session.getAttribute("lektor")).thenReturn(lektor);

        String view = controller.visEmner(model, session);

        verify(model).addAttribute("lektor", lektor);
        verify(model).addAttribute("emner", List.of());
        assertEquals("emneoversikt", view);
    }

}
