package familink_model;

import java.util.Calendar;

public class Stool extends Entry {
	
	private StoolCharacteristics stoolCharacteristic;
	private String comments;

	public Stool(Calendar date, StoolCharacteristics stoolCharacteristic, String comments) {
		super(date);
		this.stoolCharacteristic = stoolCharacteristic;
		this.comments = comments;
	}
	
	public StoolCharacteristics getStoolCarasteristic()
	{
		return stoolCharacteristic;
	}
	
	public String getComments()
	{
		return comments;
	}

}
