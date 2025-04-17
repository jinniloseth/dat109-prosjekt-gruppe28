//package no.hvl.dat109.tester;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertFalse;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import no.hvl.dat109.Emne;
//import no.hvl.dat109.Forelesning;
//import no.hvl.dat109.Person;
//
//class ForelesningTest {
//
//    private Forelesning forelesning;
//    private Person student1;
//    private Person student2;
//
//
//    @BeforeEach
//    void setUp() {
//        // Setup sample data for the tests
//        forelesning = new Forelesning(LocalDate.of(2025, 4, 4), "Java Basics");
//
//        // Create students
//        student1 = new Person(1, false);
//        student2 = new Person(2, false);
//
//    }
//
//    @Test
//    void testConstructor() {
//        // Test if the constructor correctly sets the data
//        assertEquals(LocalDate.of(2025, 4, 4), forelesning.getDato());
//        assertEquals("Java Basics", forelesning.getTittel());
//    }
//
//    @Test
//    void testGetResultat() {
//        // Test if the average of feedback is calculated correctly
//        forelesning.giVurdering(2, student1);
//        forelesning.giVurdering(3, student2);
//        double expectedAverage = (3 + 2) / 2.0;
//        assertEquals(expectedAverage, forelesning.getResultat(), 0.01);
//    }
//
//    @Test
//    void testGiVurdering() {
//        // Test if feedback can be given and added to the list
//        assertTrue(forelesning.giVurdering(5, student1)); // Successfully adds a feedback
//        assertFalse(forelesning.giVurdering(4, student1)); // Since it's already in the list, this should fail
//    }
//}
