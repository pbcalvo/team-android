package com.familink;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import familink_model.HowMuchEat;
import familink_model.Kid;
import familink_model.Meal;
import familink_model.Nap;
import familink_model.Observation;
import familink_model.Stool;
import familink_model.StoolCharacteristics;
import familink_model.TypeMeal;

public class JournalActivity extends Activity {

	TextView title;
	int kid_id, group_id;
	String kid_name;
	Button back_group, new_obs, new_meal, new_nap, new_stool, add_obs,
			cancel_obs;
	Dialog dialog;
	Button journal;
	Button message;
	Button announcement;
	Boolean observation, meal, nap, stool;
	LinearLayout obsLinearLayout, mealsLinearLayout, napsLinearLayout,
			stoolsLinearLayout;
	List<RelativeLayout> layout_buttons;
	
	List<Observation> observation_kid;
	List<Meal> meal_kid;
	List<Nap> nap_kid;
	List<Stool> stool_kid;
	
	
	
	
	View clickView;
	Kid kid;
	WebAPICommunicator api;
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_journal);

		layout_buttons = new ArrayList<RelativeLayout>();
		observation_kid = new ArrayList<Observation>();
		meal_kid = new ArrayList<Meal>();
		nap_kid = new ArrayList<Nap>();
		stool_kid = new ArrayList<Stool>();

		obsLinearLayout = (LinearLayout) findViewById(R.id.observation_list);
		mealsLinearLayout = (LinearLayout) findViewById(R.id.meals_list);
		napsLinearLayout = (LinearLayout) findViewById(R.id.naps_list);
		stoolsLinearLayout = (LinearLayout) findViewById(R.id.stools_list);

		observation = true;
		meal = true;
		nap = true;
		stool = true;
		
		kid_id = this.getIntent().getIntExtra("KID_ID", 0);
		kid_name = this.getIntent().getStringExtra("KID_NAME");
		group_id = this.getIntent().getIntExtra("GROUP_ID", 0);
		
		//Api para obtener los datos del niño seleccionado
		api = WebAPICommunicator.getInstance();
		kid = api.getKids(10).get(kid_id);
		
		title = (TextView) findViewById(R.id.titulo);
		title.setText(kid_name);

		back_group = (Button) findViewById(R.id.back_group);
		back_group.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						FamilinkAndroidActivity.class);
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent, 0);
				finish();
			}
		});

		if (observation) {

			TableLayout observation_visibility = (TableLayout) findViewById(R.id.observations_layout);
			observation_visibility.setVisibility(0);

			observation_kid = api.getObservations(kid.getId());
			for(int o = 0; o< observation_kid.size(); o++)
			{
				
				addObs(new Observation(observation_kid.get(o).getDate(), observation_kid.get(o).getObservation()));
			}
			
			//addObs(new Observation(Calendar.getInstance(), "La niña se portó bien"));
			//addObs(new Observation(Calendar.getInstance(), "La niña ahora se porto mal"));

			new_obs = (Button) findViewById(R.id.obs_add_button);
			new_obs.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					clickView = getLayoutInflater().inflate(
							R.layout.observation_form, null);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							JournalActivity.this);
					builder.setTitle(R.string.obs_new);
					builder.setView(clickView);

					builder.setPositiveButton(R.string.string_add,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									EditText editText = (EditText) clickView.findViewById(R.id.obs_text);
									WebAPICommunicator communicator = WebAPICommunicator.getInstance();
									Observation obs = new Observation(Calendar.getInstance(), editText.getText().toString());
									communicator.addObservation(kid_id, obs);
									
									addObs(obs);

									dialog.dismiss();
									Toast.makeText(JournalActivity.this,
											R.string.obs_done,
											Toast.LENGTH_SHORT).show();
								}

							});

					builder.setNegativeButton(R.string.string_cancel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Do nothing
									dialog.dismiss();
								}
							});

					builder.create().show();

				}
			});
		}

		if (meal) {

			TableLayout meal_visibility = (TableLayout) findViewById(R.id.meals_layout);
			meal_visibility.setVisibility(0);
			
			
			
			meal_kid = api.getMeals(kid.getId());
			for(int o = 0; o< meal_kid.size(); o++)
			{
				
				addMeals(new Meal(meal_kid.get(o).getDate(), meal_kid.get(o).getTypeMeal(),  meal_kid.get(o).getHowMuchEat(), meal_kid.get(o).getMeal()));

			}
			
			//addMeals(new Meal(Calendar.getInstance(), TypeMeal.SNACK_AM, HowMuchEat.ALMOST_EVERYTHING, "Chocman"));
			//addMeals(new Meal(Calendar.getInstance(), TypeMeal.LUNCH, HowMuchEat.SOMETHING, "Carne con Arroz"));

			new_meal = (Button) findViewById(R.id.meals_add_button);
			new_meal.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					clickView = getLayoutInflater().inflate(
							R.layout.meals_form, null);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							JournalActivity.this);
					builder.setTitle(R.string.meal_new);
					builder.setView(clickView);

					builder.setPositiveButton(R.string.string_add,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									
									TypeMeal typeMeal;
									HowMuchEat howMuchEat;
									String specifiedMeal;
									
									RadioButton breakfast = (RadioButton) clickView.findViewById(R.id.breakfast);
									RadioButton snackAM = (RadioButton) clickView.findViewById(R.id.snack_am);
									RadioButton lunch = (RadioButton) clickView.findViewById(R.id.lunch);
									RadioButton snackPM = (RadioButton) clickView.findViewById(R.id.snack_pm);
									
									if (breakfast.isChecked()) typeMeal = TypeMeal.BREAKFAST;
									else if (snackAM.isChecked()) typeMeal = TypeMeal.SNACK_AM;
									else if (lunch.isChecked()) typeMeal = TypeMeal.LUNCH;
									else if (snackPM.isChecked()) typeMeal = TypeMeal.SNACK_PM;
									else typeMeal = TypeMeal.DINNER;
									
									RadioButton tasted = (RadioButton) clickView.findViewById(R.id.tasted);
									RadioButton something = (RadioButton) clickView.findViewById(R.id.something);
									RadioButton half = (RadioButton) clickView.findViewById(R.id.half);
									RadioButton almost = (RadioButton) clickView.findViewById(R.id.almost);
									
									if (tasted.isChecked()) howMuchEat = HowMuchEat.JUST_TASTED_IT;
									else if (something.isChecked()) howMuchEat = HowMuchEat.SOMETHING;
									else if (half.isChecked()) howMuchEat = HowMuchEat.HALF;
									else if (almost.isChecked()) howMuchEat = HowMuchEat.ALMOST_EVERYTHING;
									else howMuchEat = HowMuchEat.ALL;
									
									EditText text = (EditText)clickView.findViewById(R.id.editText1);
									specifiedMeal = text.getText().toString();
									
									Meal meal = new Meal(Calendar.getInstance(), typeMeal, howMuchEat, specifiedMeal);
									
									WebAPICommunicator communicator = WebAPICommunicator.getInstance();
									communicator.addMeal(kid_id, meal);
									addMeals(meal);
											
									dialog.dismiss();
									Toast.makeText(JournalActivity.this,
											R.string.meal_done,
											Toast.LENGTH_SHORT).show();
								}

							});

					builder.setNegativeButton(R.string.string_cancel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Do nothing
									dialog.dismiss();
								}
							});

					builder.create().show();

				}
			});
		}

		if (nap) {
			
			
			
			
			nap_kid = api.getNaps(kid.getId());
			for(int o = 0; o< nap_kid.size(); o++)
			{
				
				//addObs(new Observation(observation_kid.get(o).getDate(), observation_kid.get(o).getObservation()));
				addNaps(new Nap(nap_kid.get(o).getStartTime(), nap_kid.get(o).getEndTime()));
			}
			
			//addNaps(new Nap(Calendar.getInstance(), Calendar.getInstance()));
			
			TableLayout nap_visibility = (TableLayout) findViewById(R.id.naps_layout);
			nap_visibility.setVisibility(0);
			
			
			

			new_nap = (Button) findViewById(R.id.naps_add_button);
			new_nap.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					clickView = getLayoutInflater().inflate(R.layout.nap_form,
							null);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							JournalActivity.this);
					builder.setTitle(R.string.nap_new);
					builder.setView(clickView);

					builder.setPositiveButton(R.string.string_add,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									TimePicker picker1 = (TimePicker) clickView.findViewById(R.id.timePicker1);
									TimePicker picker2 = (TimePicker) clickView.findViewById(R.id.timePicker2);
									
									Calendar startTime = Calendar.getInstance();
									startTime.set(Calendar.HOUR_OF_DAY, picker1.getCurrentHour());
									startTime.set(Calendar.MINUTE, picker1.getCurrentMinute());
									
									Calendar endTime = Calendar.getInstance();
									endTime.set(Calendar.HOUR_OF_DAY, picker2.getCurrentHour());
									endTime.set(Calendar.MINUTE, picker2.getCurrentMinute());
									
									WebAPICommunicator communicator = WebAPICommunicator.getInstance();
									Nap nap = new Nap(startTime, endTime);
									communicator.addNap(kid_id, nap);
									addNaps(nap);
									
									dialog.dismiss();
									Toast.makeText(JournalActivity.this,
											R.string.nap_done,
											Toast.LENGTH_SHORT).show();
								}

							});

					builder.setNegativeButton(R.string.string_cancel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Do nothing
									dialog.dismiss();
								}
							});

					builder.create().show();

				}
			});
		}

		if (stool) {
			
			
			
			stool_kid = api.getStools(kid.getId());
			for(int o = 0; o< stool_kid.size(); o++)
			{
				
				//addObs(new Observation(observation_kid.get(o).getDate(), observation_kid.get(o).getObservation()));
				addStools(new Stool(stool_kid.get(o).getDate(),stool_kid.get(o).getStoolCarasteristic(),stool_kid.get(o).getComments()));

			}

			//addStools(new Stool(Calendar.getInstance(), StoolCharacteristics.NORMAL, "Sin olor"));
			
			TableLayout stool_visibility = (TableLayout) findViewById(R.id.stools_layout);
			stool_visibility.setVisibility(0);
			new_stool = (Button) findViewById(R.id.stools_add_button);
			new_stool.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

					clickView = getLayoutInflater().inflate(
							R.layout.stool_form, null);
					AlertDialog.Builder builder = new AlertDialog.Builder(
							JournalActivity.this);
					builder.setTitle(R.string.stool_new);
					builder.setView(clickView);

					builder.setPositiveButton(R.string.string_add,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									TimePicker picker = (TimePicker) clickView.findViewById(R.id.timePicker1);
									
									Calendar date = Calendar.getInstance();
									date.set(Calendar.HOUR_OF_DAY, picker.getCurrentHour());
									date.set(Calendar.MINUTE, picker.getCurrentMinute());
									
									RadioButton normal = (RadioButton) clickView.findViewById(R.id.normal);
									RadioButton solid = (RadioButton) clickView.findViewById(R.id.solid);
									RadioButton soft = (RadioButton) clickView.findViewById(R.id.soft);
									RadioButton fluid = (RadioButton) clickView.findViewById(R.id.fluid);
									RadioButton pee = (RadioButton) clickView.findViewById(R.id.pee);
									RadioButton dry = (RadioButton) clickView.findViewById(R.id.dry);
									
									StoolCharacteristics stoolCharacteristic;
									
									if (normal.isChecked()) stoolCharacteristic = StoolCharacteristics.NORMAL;
									else if (solid.isChecked()) stoolCharacteristic = StoolCharacteristics.SOLID;
									else if (soft.isChecked()) stoolCharacteristic = StoolCharacteristics.SOFT;
									else if (fluid.isChecked()) stoolCharacteristic = StoolCharacteristics.FLUID;
									else if (pee.isChecked()) stoolCharacteristic = StoolCharacteristics.PEE;
									else if (dry.isChecked()) stoolCharacteristic = StoolCharacteristics.DRY;
									else stoolCharacteristic = StoolCharacteristics.OTHER;
									
									EditText text = (EditText) clickView.findViewById(R.id.editText1);
									
									Stool stool = new Stool(date, stoolCharacteristic, text.getText().toString());
									WebAPICommunicator communicator = WebAPICommunicator.getInstance();
									communicator.addStool(kid_id, stool);
									addStools(stool);
									
									dialog.dismiss();
									Toast.makeText(JournalActivity.this,
											R.string.stool_new,
											Toast.LENGTH_SHORT).show();
								}

							});

					builder.setNegativeButton(R.string.string_cancel,
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// Do nothing
									dialog.dismiss();
								}
							});

					builder.create().show();

				}
			});
		}

		message = (Button) findViewById(R.id.message_button);
		message.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), MessageKid.class);
				intent.putExtra("KID_ID", 1);
				intent.putExtra("KID_NAME", "Amanda Solis");
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent, 0);
				finish();
			}

		});

		announcement = (Button) findViewById(R.id.announcement_button);
		announcement.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						AnnouncementsActivity.class);
				startActivityForResult(intent, 0);
				finish();
			}
		});

	}

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
			// Volver a la selección de grupos.
			return true;
		case R.id.campus_menu_button:
			// Idem al anterior.
			return true;
		case R.id.settings_menu_button:
			// Ventana de setting, aún no implementada.
			return true;
		case R.id.logout_menu_button:
			// Logout, tampoco implementado.
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void addObs(Observation obs) {

		View view = getLayoutInflater().inflate(R.layout.observation_layout,
				null);

		TextView content_text = (TextView) view.findViewById(R.id.obs_content);
		content_text.setText(obs.getObservation());

		TextView date_text = (TextView) view.findViewById(R.id.obs_date);
		date_text.setText(obs.getDate().get(Calendar.HOUR_OF_DAY) + ":" + obs.getDate().get(Calendar.MINUTE) + ", today");

		RelativeLayout obs_layout = (RelativeLayout) view
				.findViewById(R.id.obs);
		obs_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent(getBaseContext(),
				 * JournalActivity.class); startActivityForResult(intent, 0);
				 * finish();
				 */
			}
		});

		layout_buttons.add(obs_layout);

		obsLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}

	public void addMeals(Meal meal) {

		String type, howMuch;
		
		if (meal.getTypeMeal() == TypeMeal.BREAKFAST) type = this.getString(R.string.string_breakfast);
		else if (meal.getTypeMeal() == TypeMeal.SNACK_AM) type = this.getString(R.string.string_snackam);
		else if (meal.getTypeMeal() == TypeMeal.LUNCH) type = this.getString(R.string.string_lunch);
		else if (meal.getTypeMeal() == TypeMeal.SNACK_PM) type = this.getString(R.string.string_snackpm);
		else type = this.getString(R.string.string_dinner);
		
		if (meal.getHowMuchEat() == HowMuchEat.JUST_TASTED_IT) howMuch = this.getString(R.string.string_justtasted);
		else if (meal.getHowMuchEat() == HowMuchEat.SOMETHING) howMuch = this.getString(R.string.string_something);
		else if (meal.getHowMuchEat() == HowMuchEat.HALF) howMuch = this.getString(R.string.string_half);
		else if (meal.getHowMuchEat() == HowMuchEat.ALMOST_EVERYTHING) howMuch = this.getString(R.string.string_alev);
		else howMuch = this.getString(R.string.string_all);
		
		View view = getLayoutInflater().inflate(R.layout.meals_layout, null);

		TextView content_text = (TextView) view.findViewById(R.id.meal_content);
		content_text.setText(type + ": " + howMuch);

		TextView description_text = (TextView) view
				.findViewById(R.id.meal_description);
		description_text.setText(meal.getMeal());

		RelativeLayout meals_layout = (RelativeLayout) view
				.findViewById(R.id.meals);
		meals_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent(getBaseContext(),
				 * JournalActivity.class); startActivityForResult(intent, 0);
				 * finish();
				 */
			}
		});

		layout_buttons.add(meals_layout);

		mealsLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}

	public void addNaps(Nap nap) {

		View view = getLayoutInflater().inflate(R.layout.naps_layout, null);

		TextView content_text = (TextView) view.findViewById(R.id.nap_content);
		content_text.setText(JournalActivity.this.getResources().getString (R.string.nap_from) + " " + nap.getStartTime().get(Calendar.HOUR_OF_DAY) + ":"
				+ nap.getStartTime().get(Calendar.MINUTE));

		RelativeLayout naps_layout = (RelativeLayout) view
				.findViewById(R.id.naps);
		naps_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent(getBaseContext(),
				 * JournalActivity.class); startActivityForResult(intent, 0);
				 * finish();
				 */
			}
		});

		layout_buttons.add(naps_layout);

		napsLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}

	public void addStools(Stool stool) {

		String characteristic;
		
		if (stool.getStoolCarasteristic() == StoolCharacteristics.NORMAL) characteristic = this.getString(R.string.string_normal);
		else if (stool.getStoolCarasteristic() == StoolCharacteristics.SOLID) characteristic = this.getString(R.string.string_solid);
		else if (stool.getStoolCarasteristic() == StoolCharacteristics.SOFT) characteristic = this.getString(R.string.string_soft);
		else if (stool.getStoolCarasteristic() == StoolCharacteristics.FLUID) characteristic = this.getString(R.string.string_fluid);
		else if (stool.getStoolCarasteristic() == StoolCharacteristics.PEE) characteristic = this.getString(R.string.string_pee);
		else if (stool.getStoolCarasteristic() == StoolCharacteristics.DRY) characteristic = this.getString(R.string.string_dry);
		else characteristic = this.getString(R.string.string_other);
		
		View view = getLayoutInflater().inflate(R.layout.stools_layout, null);

		TextView content_text = (TextView) view
				.findViewById(R.id.stools_content);
		content_text.setText(stool.getDate().get(Calendar.HOUR_OF_DAY) + ":" + stool.getDate().get(Calendar.MINUTE) + " - " + characteristic);

		TextView description_text = (TextView) view
				.findViewById(R.id.stools_description);
		description_text.setText(stool.getComments());

		RelativeLayout stool_layout = (RelativeLayout) view
				.findViewById(R.id.stools);
		stool_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent(getBaseContext(),
				 * JournalActivity.class); startActivityForResult(intent, 0);
				 * finish();
				 */
			}
		});

		layout_buttons.add(stool_layout);

		stoolsLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}
}
