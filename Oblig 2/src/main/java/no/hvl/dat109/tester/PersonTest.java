//package no.hvl.dat109.tester;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import no.hvl.dat109.Student;
//
//class StudentTest {
//	
//	private Student s1;
//	private Student s2;
//	List<String> emnekoderS1;
//	List<String> emnekoderS2;
//	
//	@BeforeEach
//	void start() {
//		
//		emnekoderS1 = List.of("DAT108", "DAT103");
//		emnekoderS2 = List.of();
//		
//		s1 = new Student(1,emnekoderS1);
//		s2 = new Student(2,emnekoderS2);
//	}
//
//	@Test
//	void testGetters() {
//		List<String> t = List.of("DAT108", "DAT103");
//	
// 		assertTrue(s1.getEmnekoder().equals(t));
// 		assertEquals(s2.getEmnekoder().equals(t));
// 		
// 		assertTrue(s1.getStudentId() == 1);
// 		assertTrue(s2.getStudentId() == 2);
//	}
//	
//	@Test
//	void testSetters() {
//		s2.setEmnekoder(List.of("DAT154", "DAT110"));
//		assertEquals(s2.getEmnekoder(), List.of("DAT154", "DAT110"));
//	}
//	
//	@Test
//	void testToString() {
//		assertEquals(s1.toString(), "Student Id: " + s1.getStudentId() + "DAT108 \n" + "DAT103 \n");
//	}
//	
//	
//
//}
package no;


