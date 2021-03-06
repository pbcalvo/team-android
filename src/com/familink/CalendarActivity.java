package com.familink;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

//TODO: Ver qu� pasa si se elije una fecha anterior a la actual. 
/*La idea es guardar la fecha actual num�ricamente y obtener la presionada num�ricamente y 
 * restarlas.
 */

public class CalendarActivity extends Activity implements OnClickListener {
	
	private static final String tag = "SimpleCalendarViewActivity";

	private ImageView calendarToJournalButton;
	private Button selectedDayMonthYearButton;
	private Button currentMonth;
	private ImageView prevMonth;
	private ImageView nextMonth;
	private GridView calendarView;
	private GridCellAdapter adapter;
	private Calendar _calendar;
	private int month, year;
	private final DateFormat dateFormatter = new DateFormat();
	private static final String dateTemplate = "MMMM yyyy";
	
	private String currentSelectedDate = ""; 
	private boolean selectedDate = false; 
	private LinearLayout button_layout; 
	private String previouslySelectedDate = ""; 

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);

		_calendar = Calendar.getInstance(Locale.getDefault());
		month = _calendar.get(Calendar.MONTH) + 1;
		year = _calendar.get(Calendar.YEAR);
		Log.d(tag, "Calendar Instance:= " + "Month: " + month + " " + "Year: " + year);

		/*selectedDayMonthYearButton = (Button) this.findViewById(R.id.selectedDayMonthYear);
		selectedDayMonthYearButton.setText("Selected: ");*/

		prevMonth = (ImageView) this.findViewById(R.id.prevMonth);
		prevMonth.setOnClickListener(this);

		currentMonth = (Button) this.findViewById(R.id.currentMonth);
		currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));

		nextMonth = (ImageView) this.findViewById(R.id.nextMonth);
		nextMonth.setOnClickListener(this);

		calendarView = (GridView) this.findViewById(R.id.calendar);
		
		this.button_layout = (LinearLayout) this.findViewById(R.id.button_layout);
		button_layout.setVisibility(View.INVISIBLE); 

		// Initialised
		//Para este GridView se implementa el adapter GridCellAdapter.
		adapter = new GridCellAdapter(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}

	/**
	 * 
	 * @param month
	 * @param year
	 */
	//Se usa cuando se cambia de mes usando la barra superior.
	private void setGridCellAdapterToDate(int month, int year)	{
		adapter = new GridCellAdapter(getApplicationContext(), R.id.calendar_day_gridcell, month, year);
		_calendar.set(year, month - 1, _calendar.get(Calendar.DAY_OF_MONTH));
		currentMonth.setText(dateFormatter.format(dateTemplate, _calendar.getTime()));
		adapter.notifyDataSetChanged();
		calendarView.setAdapter(adapter);
	}

	@Override
	public void onClick(View v)
	{
		if (v == prevMonth) {
			if (month <= 1) {
				month = 12;
				year--;
			}
			else
			{
				month--;
			}
			Log.d(tag, "Setting Prev Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}
		if (v == nextMonth) {
			if (month > 11) {
				month = 1;
				year++;
			}
			else {
				month++;
			}
			Log.d(tag, "Setting Next Month in GridCellAdapter: " + "Month: " + month + " Year: " + year);
			setGridCellAdapterToDate(month, year);
		}

	}

	@Override
	public void onDestroy()	{
		Log.d(tag, "Destroying View ...");
		super.onDestroy();
	}

	// ///////////////////////////////////////////////////////////////////////////////////////
	// Inner Class
	//El adapter contiene todas las gridcell (las que tienen los d�as).
	public class GridCellAdapter extends BaseAdapter implements OnClickListener
	{
		private static final String tag = "GridCellAdapter";
		private final Context _context;

		private final List<String> list;
		private static final int DAY_OFFSET = 1;
		private final String[] weekdays = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
		private final String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		private Map<String, Integer> months_numbers; 
		private final int[] daysOfMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		private final int month, year;
		private int daysInMonth, prevMonthDays;
		private int currentDayOfMonth;
		private int currentWeekDay;
		private Button gridcell;
		private TextView num_events_per_day;
		private final HashMap eventsPerMonthMap;
		private final SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MMM-yyyy");
		
		private String previousCell = ""; 
		

		// Days in Current Month
		//Constructor.
		public GridCellAdapter(Context context, int textViewResourceId, int month, int year) {
			super();
			this._context = context;
			this.list = new ArrayList<String>();
			this.month = month;
			this.year = year;
			
			this.initiateMonthsNumbers(); 
			

			Log.d(tag, "==> Passed in Date FOR Month: " + month + " " + "Year: " + year);
			Calendar calendar = Calendar.getInstance();
			setCurrentDayOfMonth(calendar.get(Calendar.DAY_OF_MONTH));
			setCurrentWeekDay(calendar.get(Calendar.DAY_OF_WEEK));
			Log.d(tag, "New Calendar:= " + calendar.getTime().toString());
			Log.d(tag, "CurrentDayOfWeek :" + getCurrentWeekDay());
			Log.d(tag, "CurrentDayOfMonth :" + getCurrentDayOfMonth());

			// Print Month
			printMonth(month, year);

			// Find Number of Events
			eventsPerMonthMap = findNumberOfEventsPerMonth(year, month);
		}
		private String getMonthAsString(int i) {
			return months[i];
		}

		private String getWeekDayAsString(int i) {
			return weekdays[i];
		}

		private int getNumberOfDaysOfMonth(int i) {
			return daysOfMonth[i];
		}

		public String getItem(int position) {
			return list.get(position);
		}

		@Override
		public int getCount() {
			return list.size();
		}

		/**
		* Prints Month
		* 
		* @param mm
		* @param yy
		*/
		private void printMonth(int mm, int yy) {
			Log.d(tag, "==> printMonth: mm: " + mm + " " + "yy: " + yy);
			// The number of days to leave blank at
			// the start of this month.
			int trailingSpaces = 0;
			int leadSpaces = 0;
			int daysInPrevMonth = 0;
			int prevMonth = 0;
			int prevYear = 0;
			int nextMonth = 0;
			int nextYear = 0;

			int currentMonth = mm - 1;
			String currentMonthName = getMonthAsString(currentMonth);
			daysInMonth = getNumberOfDaysOfMonth(currentMonth);

			Log.d(tag, "Current Month: " + " " + currentMonthName + " having " + daysInMonth + " days.");

			// Gregorian Calendar : MINUS 1, set to FIRST OF MONTH
			GregorianCalendar cal = new GregorianCalendar(yy, currentMonth, 1);
			Log.d(tag, "Gregorian Calendar:= " + cal.getTime().toString());

			if (currentMonth == 11) {
				prevMonth = currentMonth - 1;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 0;
				prevYear = yy;
				nextYear = yy + 1;
				Log.d(tag, "*->PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
			}
			else if (currentMonth == 0) {
				prevMonth = 11;
				prevYear = yy - 1;
				nextYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				nextMonth = 1;
				Log.d(tag, "**--> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
			}
			else {
				prevMonth = currentMonth - 1;
				nextMonth = currentMonth + 1;
				nextYear = yy;
				prevYear = yy;
				daysInPrevMonth = getNumberOfDaysOfMonth(prevMonth);
				Log.d(tag, "***---> PrevYear: " + prevYear + " PrevMonth:" + prevMonth + " NextMonth: " + nextMonth + " NextYear: " + nextYear);
			}

			// Compute how much to leave before before the first day of the
			// month.
			// getDay() returns 0 for Sunday.
			int currentWeekDay = cal.get(Calendar.DAY_OF_WEEK) - 1;
			trailingSpaces = currentWeekDay;

			Log.d(tag, "Week Day:" + currentWeekDay + " is " + getWeekDayAsString(currentWeekDay));
			Log.d(tag, "No. Trailing space to Add: " + trailingSpaces);
			Log.d(tag, "No. of Days in Previous Month: " + daysInPrevMonth);

			if (cal.isLeapYear(cal.get(Calendar.YEAR)) && mm == 1) {
				++daysInMonth;
			}

			// Trailing Month days
			for (int i = 0; i < trailingSpaces; i++) {
				Log.d(tag, "PREV MONTH:= " + prevMonth + " => " + getMonthAsString(prevMonth) + " " + String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i));
				list.add(String.valueOf((daysInPrevMonth - trailingSpaces + DAY_OFFSET) + i) + "-GREY" + "-" + getMonthAsString(prevMonth) + "-" + prevYear);
			}

			// Current Month Days
			for (int i = 1; i <= daysInMonth; i++) {
				Log.d(currentMonthName, String.valueOf(i) + " " + getMonthAsString(currentMonth) + " " + yy);
				if (i == getCurrentDayOfMonth()) {
					list.add(String.valueOf(i) + "-BLUE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
				}
				else {
					list.add(String.valueOf(i) + "-WHITE" + "-" + getMonthAsString(currentMonth) + "-" + yy);
				}
			}

			// Leading Month days
			for (int i = 0; i < list.size() % 7; i++) {
				Log.d(tag, "NEXT MONTH:= " + getMonthAsString(nextMonth));
				list.add(String.valueOf(i + 1) + "-GREY" + "-" + getMonthAsString(nextMonth) + "-" + nextYear);
			}
		}

		/**
		* NOTE: YOU NEED TO IMPLEMENT THIS PART Given the YEAR, MONTH, retrieve
		* ALL entries from a SQLite database for that month. Iterate over the
		* List of All entries, and get the dateCreated, which is converted into
		* day.
		* 
		* @param year
		* @param month
		* @return
		*/
		private HashMap findNumberOfEventsPerMonth(int year, int month)
		{
			HashMap map = new HashMap<String, Integer>();
			// DateFormat dateFormatter2 = new DateFormat();
			//						
			// String day = dateFormatter2.format("dd", dateCreated).toString();
			//
			// if (map.containsKey(day))
			// {
				// Integer val = (Integer) map.get(day) + 1;
				// map.put(day, val);
				// }
			// else
			// {
			// map.put(day, 1);
			// }
			return map;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.calendar_day_gridcell, parent, false);
			}

			// Get a reference to the Day gridcell
			gridcell = (Button) row.findViewById(R.id.calendar_day_gridcell);
			gridcell.setOnClickListener(this);

			// ACCOUNT FOR SPACING

			Log.d(tag, "Current Day: " + getCurrentDayOfMonth());
			String[] day_color = list.get(position).split("-");
			String theday = day_color[0];
			String themonth = day_color[2];
			String theyear = day_color[3];
		
			String thecolor = day_color[1]; 
			if ((!eventsPerMonthMap.isEmpty()) && (eventsPerMonthMap != null)) {
				if (eventsPerMonthMap.containsKey(theday)) {
					num_events_per_day = (TextView) row.findViewById(R.id.num_events_per_day);
					Integer numEvents = (Integer) eventsPerMonthMap.get(theday);
					num_events_per_day.setText(numEvents.toString());
				}
			}
			

			// Set the Day GridCell
			gridcell.setText(theday);
			gridcell.setTag(theday + "-" + themonth + "-" + theyear + "-"+thecolor+"-"+position);
			Log.d(tag, "Setting GridCell " + theday + "-" + themonth + "-" + theyear);

			if (day_color[1].equals("GREY")) {
				gridcell.setTextColor(Color.LTGRAY);
			}
			if (day_color[1].equals("WHITE")) {
				gridcell.setTextColor(Color.WHITE);
			}
			if (day_color[1].equals("BLUE")) {
				gridcell.setTextColor(getResources().getColor(R.color.static_text_color));
			}
			if (day_color[1].equals("RED")) {
				gridcell.setTextColor(Color.RED);
			}
			return row;
		}
		
		//M�todo cuando se selecciona una fecha. 
		@Override
		public void onClick(View view) {
			
			//Recupero el tag del lugar que ha sido cliqueado.
			String date_month_year_color_pos = (String) view.getTag();  
			
			//Ahora si hay una fecha cliqueada, por lo que:
			currentSelectedDate = date_month_year_color_pos; 
			
			if(!selectedDate)
				selectedDate = true; 
			
			
			//Esto es del c�digo original.
			//selectedDayMonthYearButton.setText("Selected: " + date_month_year);
			
			//Veo si las fechas son del mismo mes y a�o.
			String[] sub1 = currentSelectedDate.split("-");
			String[] sub2 = previouslySelectedDate.split("-"); 
			
			Calendar rightNow = Calendar.getInstance();
			int selected_year = Integer.parseInt(sub1[2]);
			int selected_month = this.months_numbers.get(sub1[1]);
			int selected_day = Integer.parseInt(sub1[0]);
			
			//Ac� ver si son del mismo mes. Si es as�, se cambian de rojo a blanco.
			if(!previouslySelectedDate.equals("")) {
				
				//Veo si la fecha anterior selecciona es del mismo mes.
				//De ser as�, la cambio de rojo a blanco.
				if(sub1[1].equals(sub2[1]))
				{
					//Ac� se debe cambiar el elemento en la lista y guardar.
					//Formo el nuevo string 
					String new_previous = sub2[0]+"-WHITE-"+sub2[1]+"-"+sub2[2]; 
					int position = Integer.parseInt(sub2[4]); 
					list.remove(position); 
					list.add(position,new_previous);
				}
			}
			
			//Hago visible los botones ahora que hay fecha seleccionada.
			//Antes de eso veo si la fecha es anterior o posterior y cambio
			//las prop de cada bot�n.
			boolean posterior = true; 
			if(selected_year <= rightNow.get(Calendar.YEAR)) {
				int current_month = rightNow.get(Calendar.MONTH);
				if((selected_month-1) <=current_month) {
					if(selected_day<=rightNow.get(Calendar.DAY_OF_MONTH)) {
						findViewById(R.id.button_add_event).setVisibility(View.INVISIBLE); 
						findViewById(R.id.button_add_task).setVisibility(View.INVISIBLE); 
						posterior = false;
					}
						
				}
			} if(posterior) {
				findViewById(R.id.button_add_event).setVisibility(View.VISIBLE); 
				findViewById(R.id.button_add_task).setVisibility(View.VISIBLE); 
			}
			
			previouslySelectedDate = currentSelectedDate; 
		
			String new_current = sub1[0]+"-RED-"+sub1[1]+"-"+sub1[2]; 
			int position = Integer.parseInt(sub1[4]); 
			list.remove(position); 
			list.add(position,new_current);
			
			button_layout.setVisibility(View.VISIBLE);
			
			try {
				Date parsedDate = dateFormatter.parse(date_month_year_color_pos);
				Log.d(tag, "Parsed Date: " + parsedDate.toString());

			}
			catch (ParseException e) {
				e.printStackTrace();
			}
			
			//Finalmente, actualizo la vista. 
			this.notifyDataSetChanged(); 
		}

		public int getCurrentDayOfMonth() {
			return currentDayOfMonth;
		}

		private void setCurrentDayOfMonth(int currentDayOfMonth) {
			this.currentDayOfMonth = currentDayOfMonth;
		}
		public void setCurrentWeekDay(int currentWeekDay) {
			this.currentWeekDay = currentWeekDay;
		}
		public int getCurrentWeekDay() {
			return currentWeekDay;
		}
		
		/* M�todos agregados por PameCal */
		public void initiateMonthsNumbers() {
			this.months_numbers = new HashMap<String, Integer>(); 
			months_numbers.put("January", 1); 
			months_numbers.put("February", 2); 
			months_numbers.put("March", 3); 
			months_numbers.put("April", 4); 
			months_numbers.put("May", 5); 
			months_numbers.put("June", 6); 
			months_numbers.put("July", 7); 
			months_numbers.put("August", 8); 
			months_numbers.put("September", 9); 
			months_numbers.put("October", 10); 
			months_numbers.put("November", 11); 
			months_numbers.put("December", 12); 
		}
	}
	
	
	
	//M�todo Men�.
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.activity_menu_button, menu);
	        return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        // Handle item selection
	        switch (item.getItemId()) {
	            case R.id.groups_menu_button:
	                //Volver a la selecci�n de grupos.
	                return true;
	            case R.id.campus_menu_button:
	                //Idem al anterior.
	                return true;
	            case R.id.settings_menu_button:
	            	//Ventana de setting, a�n no implementada.
	            	return true; 
	            case R.id.logout_menu_button:
	            	//Logout, tampoco implementado.
	            	return true; 
	            default:
	                return super.onOptionsItemSelected(item);
	        }
	    }
	
}
