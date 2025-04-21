package no.hvl.dat109.tester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat109.Forelesning;
import no.hvl.dat109.Person;
import no.hvl.dat109.Tilbakemelding;

public class TilbakemeldingTest {

    private Person student;
    private Forelesning forelesning;

    @BeforeEach
    void setUp() {
        student = new Person(200001, false);
        forelesning = new Forelesning(LocalDate.now(), "Testforelesning");
    }

    @Test
    void testConstructorAndGetters() {
        Tilbakemelding t = new Tilbakemelding(4, student, forelesning);

        assertEquals(4, t.getTilbakemelding());
        assertEquals(student, t.getStudent());
    }

    @Test
    void testConstructorWithStudentOnly() {
        Tilbakemelding t = new Tilbakemelding(5, student);

        assertEquals(5, t.getTilbakemelding());
        assertEquals(student, t.getStudent());
    }

    @Test
    void testToString() {
        Tilbakemelding t = new Tilbakemelding(3, student, forelesning);
        String str = t.toString();

        assertTrue(str.contains("tilbakemelding=3"));
        assertTrue(str.contains("student="));
        assertTrue(str.contains("forelesning="));
    }
}
