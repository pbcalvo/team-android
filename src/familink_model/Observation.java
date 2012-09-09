package familink_model;

import java.util.Calendar;

public class Observation extends Entry{
	
	String observation;
	
	public Observation(Calendar date, String observation)
	{
		super(date);
		this.observation = observation;
	}
	
	public String getObservation()
	{
		return observation;
	}

}
