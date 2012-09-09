package familink_model;

import java.util.Calendar;

public class Nap extends Entry {

	private Calendar endTime;
	
	public Nap(Calendar startTime, Calendar endTime)
	{
		super(startTime);
		this.endTime = endTime;
	}
	
	public Calendar getStartTime()
	{
		return date;
	}
	
	public Calendar getEndTime()
	{
		return endTime;
	}
}
