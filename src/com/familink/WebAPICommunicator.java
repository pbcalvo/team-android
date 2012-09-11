package com.familink;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import familink_model.Announcement;
import familink_model.Group;
import familink_model.HowMuchEat;
import familink_model.Kid;
import familink_model.Meal;
import familink_model.Message;
import familink_model.Nap;
import familink_model.Observation;
import familink_model.Stool;
import familink_model.StoolCharacteristics;
import familink_model.TypeMeal;

public class WebAPICommunicator {
	
	private static WebAPICommunicator instance;
	
	private WebAPICommunicator()
	{
		
	}

	public static WebAPICommunicator getInstance()
	{
		if (instance == null)
		{
			instance = new WebAPICommunicator();
		}
		
		return instance;
	}
	
	public List<Group> getGroups(int schoolID)
	{
		List<Group> groups = new ArrayList<Group>();
		groups.add(new Group(10, "Grupo 01"));
		
		return groups;
	}
	
	public List<Kid> getKids(int groupID)
	{
		List<Kid> kids = new ArrayList<Kid>();
		List<String> guardians = new ArrayList<String>();
		
		guardians.add("Juan");
		guardians.add("María");
		kids.add(new Kid(11, "Juan Perez", guardians));
		
		guardians.clear();
		guardians.add("Pedro");
		guardians.add("Amanda");
		
		kids.add(new Kid(12, "Amanda Solís", guardians));
		
		return kids;
	}
	
	public List<Observation> getObservations(int kidID)
	{
		List<Observation> observations = new ArrayList<Observation>();
		if (kidID == 12)
		{
			Calendar date = Calendar.getInstance();
			date.set(2012, 9, 3, 14, 35);
			
			observations.add(new Observation(date, "Amanda se portó muy bien hoy, comió toda su " +
					"comida e hizo nuevos amigos. Su comportamiento ha mejorado " +
					"mucho desde la semana pasada. Excelente trabajo en casa."));
			
			date = Calendar.getInstance();
			date.set(2012, 8, 31, 16, 35);
			
			observations.add(new Observation(date, "Amanda no se portó muy bien hoy, se peleó " +
					"con una compañera y tuvo un comportamiento bastante egoísta. " +
					"Les pediría que porfavor trabajen esto durante el fin de semana. Gracias"));
		}
		
		return observations;
	}
	
	public void addObservation(int kidID, Observation observation)
	{
		//Agregar código aquí.
	}
	
	public List<Meal> getMeals(int kidID)
	{
		List<Meal> meals = new ArrayList<Meal>();
		
		if (kidID == 12)
		{
			Calendar date = Calendar.getInstance();
			date.set(Calendar.HOUR_OF_DAY, 9);
			date.set(Calendar.MINUTE, 25);
			meals.add(new Meal(date, TypeMeal.BREAKFAST, HowMuchEat.ALL, "Papilla de manzana"));
			
			date = Calendar.getInstance();
			date.set(Calendar.HOUR_OF_DAY, 12);
			date.set(Calendar.MINUTE, 40);
			meals.add(new Meal(date, TypeMeal.LUNCH, HowMuchEat.ALL, "Arroz con pollo"));
		}
		
		return meals;
	}
	
	public void addMeal(int kidID, Meal meal)
	{
		//Agregar código aquí
	}
	
	public List<Nap> getNaps(int kidID)
	{
		List<Nap> naps = new ArrayList<Nap>();
		
		if (kidID == 12)
		{
			Calendar startTime = Calendar.getInstance();
			startTime.set(Calendar.HOUR_OF_DAY, 13);
			startTime.set(Calendar.MINUTE, 00);
			
			Calendar endTime = Calendar.getInstance();
			endTime.set(Calendar.HOUR_OF_DAY, 15);
			endTime.set(Calendar.MINUTE, 00);
			
			naps.add(new Nap(startTime, endTime));
		}
		
		return naps;
	}
	
	public void addNap(int kidID, Nap nap)
	{
		// Agregar código aquí
	}
	
	public List<Stool> getStools(int kidID)
	{
		List<Stool> stools = new ArrayList<Stool>();
		
		if (kidID == 12)
		{
			Calendar date = Calendar.getInstance();
			date.set(Calendar.HOUR_OF_DAY, 11);
			date.set(Calendar.MINUTE, 00);
			
			stools.add(new Stool(date, StoolCharacteristics.NORMAL, "Deposiciones normales"));
		}
		
		return stools;
	}
	
	public void addStool(int kidID, Stool stool)
	{
		// Agregar código aquí
	}
	
	public List<Message> getMessages(int kidID)
	{
		List<Message> messages = new ArrayList<Message>();
		
		if (kidID == 12)
		{
			Calendar date = Calendar.getInstance();
			date.set(2012, 8, 31, 18, 42);
			messages.add(new Message(date, "Amanda Solis", "Hola, quisiera saber si mañana hay reunión"));
			
			date = Calendar.getInstance();
			date.set(2012, 9, 1, 9, 23);
			messages.add(new Message(date, "Jenny Bravo", "Sí, tendremos reunión en la sala pitufo 2"));
			
			date = Calendar.getInstance();
			date.set(2012, 9, 3, 14, 56);
			messages.add(new Message(date, "Amanda Solis", "¿Cuando hay que llevar los crayones?"));
			
			date = Calendar.getInstance();
			date.set(2012, 9, 4, 9, 50);
			messages.add(new Message(date, "Jenny Bravo", "El jueves que viene"));
		}
		
		return messages;
	}
	
	public void sendMessage(int kidID, Message message)
	{
		// Agregar código aquí
	}
	
	public List<Announcement> getAnnouncements(int groupID)
	{
		List<Announcement> announcements = new ArrayList<Announcement>();
		
		Calendar date = Calendar.getInstance();
		date.set(2012, 7, 24, 11, 29);
		announcements.add(new Announcement(date, "Suspensión reunión", "Estimados apoderados, " +
				"se suspende la reunión programada para este jueves debido a que una de las tías " +
				"se encuentra con licencia médica."));
		
		date = Calendar.getInstance();
		date.set(2012, 7, 24, 10, 05);
		announcements.add(new Announcement(date, "Suspensión de actividades", "Estimados apoderados, " +
				"les recordamos que este viernes las actividades se suspenden a las 14:00 hrs" +
				"debido a que las tías tendrán reunión."));
		
		date = Calendar.getInstance();
		date.set(2012, 7, 24, 10, 05);
		announcements.add(new Announcement(date, "Suspensión reunión", "Estimados apoderados, " +
				"tendremos una reunión de apoderados el día jueves 30 de agosto. Su asistencia " +
				"es importante, ya que se conversará caso a caso la siuación de sus hijos."));
		
		return announcements;
	}
	
	public void sendAnnouncement(int groupID, Announcement announcement)
	{
		//Agregar código aquí.
	}
}
