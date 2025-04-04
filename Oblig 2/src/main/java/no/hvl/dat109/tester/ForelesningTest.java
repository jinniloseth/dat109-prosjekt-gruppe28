package no.hvl.dat109.tester;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import no.hvl.dat109.Forelesning;
import no.hvl.dat109.Person;
import no.hvl.dat109.Tilbakemelding;

class ForelesningTest {

    private Forelesning forelesning;
    private Person student1;
    private Person student2;
    private Tilbakemelding tilbakemelding1;
    private Tilbakemelding tilbakemelding2;

    @BeforeEach
    void setUp() {
        // Setup sample data for the tests
        forelesning = new Forelesning(LocalDate.of(2025, 4, 4), "Java Basics");

        // Create students
        student1 = new Person(1, "student1", "Student One");
        student2 = new Person(2, "student2", "Student Two");

        // Create feedback objects
        tilbakemelding1 = new Tilbakemelding(5, student1);
        tilbakemelding2 = new Tilbakemelding(4, student2);
    }

    @Test
    void testConstructor() {
        // Test if the constructor correctly sets the data
        assertEquals(LocalDate.of(2025, 4, 4), forelesning.getDato());
        assertEquals("Java Basics", forelesning.getTittel());
    }

    @Test
    void testRegistrerTilbakemelding() {
        // Test if feedback can be registered once per student
        assertTrue(forelesning.registrerTilbakemelding(tilbakemelding1)); // First feedback from student1
        assertFalse(forelesning.registrerTilbakemelding(tilbakemelding1)); // Second feedback from the same student should fail
        assertTrue(forelesning.registrerTilbakemelding(tilbakemelding2)); // Feedback from a different student should succeed
    }

    @Test
    void testGetResultat() {
        // Test if the average of feedback is calculated correctly
        forelesning.registrerTilbakemelding(tilbakemelding1);
        forelesning.registrerTilbakemelding(tilbakemelding2);
        double expectedAverage = (5 + 4) / 2.0;
        assertEquals(expectedAverage, forelesning.getResultat(), 0.01);
    }

    @Test
    void testGiVurdering() {
        // Test if feedback can be given and added to the list
        assertTrue(forelesning.giVurdering(5, student1)); // Successfully adds a feedback
        assertFalse(forelesning.giVurdering(4, student1)); // Since it's already in the list, this should fail
    }
}
