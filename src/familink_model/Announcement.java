package familink_model;

import java.util.Calendar;

public class Announcement extends Entry {

	private String subject;
	private String message;
	
	public Announcement(Calendar date, String subject, String message) {
		super(date);
		this.subject = subject;
		this.message = message;
	}
	
	public String getSubject()
	{
		return subject;
	}
	
	public String getMessage()
	{
		return message;
	}

}
