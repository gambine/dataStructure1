import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;

public class Assignment implements Calendar {		
	private List<Appointment> app;		
	public Assignment() {
		app = new ArrayList<Appointment>();		
	}
	
	@Override
	public List<Appointment> getAppointments(String location) {				
		if(location == null){
			throw new IllegalArgumentException("location was null");
		}
		//create an arrayList and store appointments
		//use for loop to put all matched appointment in the arrayList
		List<Appointment> list = new ArrayList<Appointment>();
		for(int i=0; i<app.size();i++){
			if(app.get(i).getLocation().equals(location)){
				list.add(app.get(i));						
			}
		}
		return list;
	}

	@Override
	public Appointment getNextAppointment(Date when) {
		if(when == null) {
			throw new IllegalArgumentException("time was null");
		}
		//use for loop to get the closest appointment to current appointment
		Appointment next = null;
		long min = when.getTime();
		long diff = 0;
		for(int i=0; i<app.size();i++){						
			if(app.get(i).getStartTime().getTime()>=when.getTime()){
				diff = app.get(i).getStartTime().getTime() - when.getTime();
				if(diff<=min){
					min = diff;
					next = app.get(i);
				}								
			}
		}		
		return next;                                       
	}

	@Override
	public Appointment getNextAppointment(Date when, String location) {	
		//use for loop
		//find the appointment that match location 
		//find their time, calculate the difference between them,and return the smallest, which is the closest appointment to the current appointment 
		if(app!=null){
			Appointment next = null;
			long min = when.getTime();
			long diff = 0;
			for(int i=0;i<app.size();i++){							
				if(app.get(i).getLocation().compareTo(location)==0){
					if(app.get(i).getStartTime().getTime()>=when.getTime()){
						diff = app.get(i).getStartTime().getTime() - when.getTime();
						if(diff<=min){
							min = diff;
							next = app.get(i);
						}	
					}	
				}				
			}
			return next;
		}		
		return null;
	}

	@Override
	public void add(String description, Date when, String location) {   		
		if(when == null||description == null||location == null) {
			throw new IllegalArgumentException("appointment was null");
		}		
		Appointment newApp = new MyApp(description,location,when);
		app.add(newApp);		
	}

	@Override
	public void remove(Appointment appointment) {		
		if(appointment == null) {
			throw new IllegalArgumentException("appointment was null");
		}		
		for(int i=0;i<app.size();i++){
			if(app.get(i).equals(appointment)){
				app.remove(app.get(i));
			}
		}
	}
}
