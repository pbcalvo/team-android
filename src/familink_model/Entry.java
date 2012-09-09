package familink_model;

import java.util.Calendar;

public abstract class Entry {

	protected Calendar date;
	
	protected Entry(Calendar date)
	{
		this.date = date;
	}
	
	public Calendar getDate()
	{
		return date;
	}
}
