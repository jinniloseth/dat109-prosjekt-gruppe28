package no.hvl.dat109.tester;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat109.Emne;
import no.hvl.dat109.Person;

public class PersonTest {

    private Emne emne1;
    private Emne emne2;
    private Person student;
    private Person lektor;

    @BeforeEach
    public void setup() {
        emne1 = new Emne("DAT109", "Webutvikling", "H24", List.of());
        emne2 = new Emne("MAT102", "Diskret Matematikk", "H24", List.of());
        student = new Person(200001, false, List.of(emne1, emne2));
        lektor = new Person(1001, true);
    }

    @Test
    public void testGetBrukernavn() {
        assertEquals(200001, student.getBrukernavn());
        assertEquals(1001, lektor.getBrukernavn());
    }

    @Test
    public void testErLektor() {
        assertFalse(student.erLektor());
        assertTrue(lektor.erLektor());
    }

    @Test
    public void testGetEmner() {
        assertEquals(2, student.getEmner().size());
        assertTrue(student.getEmner().contains(emne1));
        assertTrue(student.getEmner().contains(emne2));
    }

    @Test
    public void testHarEmne() {
        assertTrue(student.harEmne(emne1));
        assertFalse(lektor.harEmne(emne1));
    }

    @Test
    public void testToString() {
        assertEquals("Person{brukernavn=200001}", student.toString());
    }
}
