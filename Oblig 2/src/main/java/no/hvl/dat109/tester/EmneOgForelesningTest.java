package no.hvl.dat109.tester;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat109.Emne;
import no.hvl.dat109.Forelesning;

public class EmneOgForelesningTest {

    private Emne emne;
    private Forelesning f1, f2;

    @BeforeEach
    public void setup() {
        f1 = new Forelesning(LocalDate.now(), "Forelesning 1");
        f1.setResultat(0.8);
        setForelesningnr(f1, 1);

        f2 = new Forelesning(LocalDate.now().plusDays(1), "Forelesning 2");
        f2.setResultat(0.6);
        setForelesningnr(f2, 2);

        List<Forelesning> forelesninger = new ArrayList<>();
        forelesninger.add(f1);
        forelesninger.add(f2);

        emne = new Emne("DAT109", "Distribuerte Systemer", "V2025", forelesninger);

        f1.setEmne(emne);
        f2.setEmne(emne);
    }

    @Test
    public void testEmneGettere() {
        assertEquals("Distribuerte Systemer", emne.getNavn());
        assertEquals("DAT109", emne.getEmnekode());
        assertEquals("V2025", emne.getSemester());
    }

    @Test
    public void testEmneResultatOgForelesninger() {
        assertEquals(0.8, emne.getResultat(1), 0.001);
        assertEquals(0.6, emne.getResultat(2), 0.001);
        assertEquals(0.0, emne.getResultat(999), 0.001); // Finnes ikke
        assertEquals(2, emne.antallForelesninger());
        assertEquals(f1, emne.finnForelesning(1));
        assertEquals(f2, emne.finnForelesning(2));
    }

    @Test
    public void testForelesningData() {
        assertEquals("Forelesning 1", f1.getTittel());
        assertEquals("Forelesning 2", f2.getTittel());
        assertEquals(0.8, f1.getResultat());
        assertEquals(0.6, f2.getResultat());
        assertEquals(emne, f1.getEmne());
    }

    // Hjelpemetode for å sette forelesningnr manuelt (siden det er autogenerert normalt)
    private void setForelesningnr(Forelesning f, int nr) {
        try {
            var felt = Forelesning.class.getDeclaredField("forelesningnr");
            felt.setAccessible(true);
            felt.setInt(f, nr);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
