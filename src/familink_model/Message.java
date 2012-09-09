package familink_model;

import java.util.Calendar;

public class Message extends Entry {

	String sender;
	String message;
	
	public Message(Calendar date, String sender, String message) {
		super(date);
		this.sender = sender;
		this.message = message;
	}
	
	public String getSender()
	{
		return sender;
	}
	
	public String getMessage()
	{
		return message;
	}

}
