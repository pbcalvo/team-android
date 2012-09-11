package familink_model;

import java.util.Calendar;

public class Meal extends Entry {
	
	private TypeMeal typeMeal;
	private HowMuchEat howMuchEat;
	private String meal;
	
	public Meal(Calendar date, TypeMeal typeMeal, HowMuchEat howMuchEat, String meal)
	{
		super(date);
		this.typeMeal = typeMeal;
		this.howMuchEat = howMuchEat;
		this.meal = meal;
	}
	
	public TypeMeal getTypeMeal()
	{
		return typeMeal;
	}
	
	public HowMuchEat getHowMuchEat()
	{
		return howMuchEat;
	}
	
	public String getMeal()
	{
		return meal;
	}

}
