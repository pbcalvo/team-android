package familink_model;

import java.util.List;

public class Kid {

	private int id;
	private String name;
	private List<String> guardians;
	
	public Kid(int id, String name, List<String> guardians)
	{
		this.id = id;
		this.name = name;
		this.guardians = guardians;
	}
	
	public int getId()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public List<String> getGuardians()
	{
		return guardians;
	}
}
