import java.util.Date;

public class MyApp implements Appointment{

	private String desc;
	private String location;
	private Date when;
	
	// constructor
	public MyApp(String desc, String loc, Date when){
		this.desc = desc;
		this.location = loc;
		this.when = when;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		
		return desc;
	}

	@Override
	public String getLocation() {
		// TODO Auto-generated method stub
		return location;
	}

	@Override
	public Date getStartTime() {
		// TODO Auto-generated method stub
		return when;
	}


}

