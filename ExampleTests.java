import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ExampleTests {
	
	// Set up JUnit to be able to check for expected exceptions
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	// This will make it a bit easier for us to make Date objects
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	// Helper method to build the example calendar
	private Calendar buildTinyExample() {
		Calendar calendar = new Assignment();
		try {
			calendar.add("A", df.parse("2016/09/03 09:00:00"), "SIT lab 117");
			calendar.add("B", df.parse("2016/09/04 16:00:00"), "SIT lab 117");
			calendar.add("C", df.parse("2016/09/05 17:00:00"), "SIT lab 118");
			calendar.add("D", df.parse("2016/09/05 14:00:00"), "SIT lab 117");
			calendar.add("E", df.parse("2016/09/05 16:00:00"), "SIT lab 118");
			calendar.add("F", df.parse("2016/09/05 16:00:00"), "SIT lab 117");
			
			
			
			
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
		return calendar;
	}

	@Test
	public void testGetAppointmentsExample() {
		
		Calendar calendar = buildTinyExample();
		
		// This should be a list containing Appointments C and E
		List<Appointment> appointments = calendar.getAppointments("SIT lab 118");
		
		// For this example, we'll just check the descriptions.
		// Good testing should be more thorough!
		List<String> descriptions = new ArrayList<String>();
		for(Appointment a: appointments) {
			descriptions.add(a.getDescription());
		}
		// Sorting the objects before we compare the list, since the assignment
		// doesn't require the output to be in any particular order
		Collections.sort(descriptions);
		assertEquals(Arrays.asList("C", "E"), descriptions);
	}
		
	@Test
	public void testGetNextAppointmentExample() {
		
		Calendar calendar = buildTinyExample();
		
		//this will return either C,or E or F
		try {
			Appointment appointment = calendar.getNextAppointment(df.parse("2016/09/05 15:00:00"));
			String description = appointment.getDescription();
			assertTrue(description.equals("C") || description.equals("E") ||description.equals("F"));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}

		// This should return null
		try {
			assertNull(calendar.getNextAppointment(df.parse("2020/01/01 13:00:00")));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}

	}
	
	@Test
	public void testGetNextAppointmentLocationExample() {

		Calendar calendar = buildTinyExample();
		try {
			Appointment appointment = calendar.getNextAppointment(df.parse("2016/09/03 13:00:00"), "SIT lab 117");
			String description = appointment.getDescription();
			assertTrue(description.equals("B") || description.equals("C") || description.equals("D") || description.equals("E") ||description.equals("F"));
		} catch (ParseException e) {
			e.printStackTrace();
			fail("The test case is broken, invalid SimpleDateFormat parse");
		}
	}
	
	@Test
	public void testAddAppointmentExample() throws ParseException{
		Calendar calendar = buildTinyExample();
		Appointment a = calendar.getNextAppointment(df.parse("2016/09/03 09:00:00"), "SIT lab 117");
		assertEquals(a.getDescription(),"A");
		calendar.remove(a);
	}
	
	@Test
	public void testRemoveAppointmentExample() throws ParseException{
		Calendar calendar = buildTinyExample();
		
		
		calendar.add("A", df.parse("2016/09/03 09:00:00"), "SIT lab 117");
	}
	
	@Test
	public void testIllegalArgumentExample() {

		Calendar calendar = buildTinyExample();

		// Tell JUnit to expect an IllegalArgumentException
		thrown.expect(IllegalArgumentException.class);
		
		// This should cause an IllegalArgumentException to be thrown
		calendar.getNextAppointment(null);

	}
	
	public void testAddIllegalArgumentExample(){
		Calendar calendar = buildTinyExample();

		// Tell JUnit to expect an IllegalArgumentException
		thrown.expect(IllegalArgumentException.class);
		
		// This should cause an IllegalArgumentException to be thrown
		calendar.add(null,null,null);
	}
	public void testRemoveIllegalArgumentExample(){
		Calendar calendar = buildTinyExample();

		// Tell JUnit to expect an IllegalArgumentException
		thrown.expect(IllegalArgumentException.class);
		
		// This should cause an IllegalArgumentException to be thrown
		calendar.remove(null);
	}

}

