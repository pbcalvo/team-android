package com.familink;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import familink_model.Kid;

public class FamilinkAndroidActivity extends Activity {
	/** Called when the activity is first created. */

	private int group_id = 0;
	private int kid_id;

	TextView prueba;
	String name_string;
	TextView title;
	Button journal;
	Button message;
	Button announcement;
	Button calendar;
	LinearLayout kidsTableLayout;
	List<String> parents;
	List<RelativeLayout> layout_buttons;
	List<String> names_kids;
	RelativeLayout home;
	List<Kid> kids;
	int i,j,p;
	Boolean aux;
	WebAPICommunicator api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		i=0;
		j=0;
		aux=true;
		layout_buttons = new ArrayList<RelativeLayout>();
		names_kids = new ArrayList<String>();
		
		api = WebAPICommunicator.getInstance();
		
		kids = api.getKids(10);
		
		for(int h = 0; h < kids.size(); h++)
		{
			//add
		}
		
		
		
		

		kidsTableLayout = (LinearLayout) findViewById(R.id.kidsList);

		parents = new ArrayList<String>();
		parents.add("Papa");
		parents.add("Mama");
		names_kids.add("Amanda Solis");
		names_kids.add("Juan Perez");

		addKid(kids.get(0));
		addKid(kids.get(1));

		group_id = this.getIntent().getIntExtra("GROUP_ID", 0);

		if (group_id == 0) {
			Intent intent = new Intent(getBaseContext(),
					SelectGroupActivity.class);
			startActivityForResult(intent, 0);
			finish();
		}
		
		
		
		
		
		
		
		//prueba de botones autogenerados sin medoto addkid
		
		 for(int i = 0; i<5; i++) {
	    	   RelativeLayout l = new RelativeLayout(this);
	    	   l.setId(i);
	    	   //l.setClickable(true);
	    	   
	    	   l.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View view) {
					
					Log.d("RelLayout", "El ID apretado es"+view.getId());
				}
			});
	       }
		
		
		
		
		
		
		
		
		
		/*layout_buttons.get(1).setOnClickListener(new View.OnClickListener() {

		       public void onClick(View v) {
		                     // Perform action on click
		    	   Intent intent = new Intent(getBaseContext(),
							JournalActivity.class);
					intent.putExtra("KID_ID", 1);
					intent.putExtra("KID_NAME",names_kids.get(0));
					intent.putExtra("GROUP_ID", group_id);
					startActivityForResult(intent, 0);
					finish();
		                 }

		});
		
		layout_buttons.get(2).setOnClickListener(new View.OnClickListener() {

		       public void onClick(View v) {
		                     // Perform action on click
		    	   Intent intent = new Intent(getBaseContext(),
							JournalActivity.class);
					intent.putExtra("KID_ID", 1);
					intent.putExtra("KID_NAME", names_kids.get(1));
					intent.putExtra("GROUP_ID", group_id);
					startActivityForResult(intent, 0);
					finish();
		    	   
		                 }

		});
		
		
		
		/*while(aux)
		{
			for(j = 0; j < 1; j++)
			{
				layout_buttons.get(j+1).setOnClickListener(new View.OnClickListener() {

				       public void onClick(View v) {
				                     // Perform action on click
				    	   Intent intent = new Intent(getBaseContext(),
									JournalActivity.class);
							intent.putExtra("KID_ID", 1);
							intent.putExtra("KID_NAME", names_kids.get(j));
							intent.putExtra("GROUP_ID", group_id);
							startActivityForResult(intent, 0);
							finish();
							aux = false;
				    	   
				                 }

				});
				
			}
			break;
			
		}
		
		*/
		
		/*for(j = 0; j < 1; j++)
		{
			layout_buttons.get(j + 1).setOnClickListener(new View.OnClickListener() {

			       public void onClick(View v) {
			                     // Perform action on click
			    	   Intent intent = new Intent(getBaseContext(),
								JournalActivity.class);
						intent.putExtra("KID_ID", 1);
						intent.putExtra("KID_NAME", names_kids.get(j));
						intent.putExtra("GROUP_ID", group_id);
						startActivityForResult(intent, 0);
						finish();
			    	   
			                 }

			});
		}*/
		
		p=0;
		/*for(int k = 0; k<1; k++) {
	    	   RelativeLayout l = new RelativeLayout(this);
	    	   l = layout_buttons.get(k);
	    	   l.setId(k);
	    	   l.setOnClickListener(new View.OnClickListener() {
				
				
				@Override
				public void onClick(View view) {
					
					Log.d("RelLayout", "El ID apretado es"+view.getId());
					Intent intent = new Intent(getBaseContext(),
							JournalActivity.class);
					intent.putExtra("KID_ID", 1);
					//intent.putExtra("KID_NAME", names_kids.get(p));
					intent.putExtra("KID_NAME", Log.d("RelLayout", "El ID apretado es"+view.getId()));
					intent.putExtra("GROUP_ID", group_id);
					startActivityForResult(intent, 0);
					//Log.d("RelLayout", "El ID apretado es"+view.getId());
					finish();
				}
			});
	    	   p++;
	       }
		
		
		
		/*layout_buttons.setOnClickListener(new OnClickListener() {

		       public void onClick(View v) {
		                     // Perform action on click
		                 }

		});*/
		
		
		/*home.setOnClickListener(new View.OnClickListener() {

		       public void onClick(View v) {
		                     // Perform action on click
		    	   Intent intent = new Intent(getBaseContext(),
							JournalActivity.class);
					intent.putExtra("KID_ID", 1);
					intent.putExtra("KID_NAME", names_kids.get(j));
					intent.putExtra("GROUP_ID", group_id);
					startActivityForResult(intent, 0);
					finish();
		    	   
		                 }

		});*/
		

		journal = (Button) findViewById(R.id.journal_button);
		journal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			}
		});

		message = (Button) findViewById(R.id.message_button);
		message.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						MessageActivity.class);
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

		calendar = (Button) findViewById(R.id.calendar_button);
		calendar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(getBaseContext(),
				// SelectGroupActivity.class);
				// startActivityForResult(intent, 0);
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
		case R.id.testing_internet_button:
			Intent intent = new Intent(getBaseContext(),
					WebActivity.class);
			startActivityForResult(intent, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	public void addKid(Kid kid) {
		//name_string = kid;

		View view = getLayoutInflater().inflate(R.layout.kids_layout, null);
		//view.setClickable(true);

		TextView name_kid = (TextView) view.findViewById(R.id.name_kid);
		name_kid.setText(kid.getName());
		//name_kid.setId(i);
		//name_kid.setId(kid.getId());

		/*String names_parents = "";
		for(int o = 0; o < kid.getGuardians().size(); o++)
		{
			if(kid.getGuardians().get(o) == kid.getGuardians().get(kid.getGuardians().size()-1))
			{
				names_parents = kid.getGuardians().get(o);
			}
			else if(kid.getGuardians().get(o) == kid.getGuardians().get(kid.getGuardians().size()-2))
			{
				names_parents = kid.getGuardians().get(o) +" y ";
			}
			else
			{
				names_parents = kid.getGuardians().get(o) +" ";
			}
			
		}*/
		TextView parents_kid = (TextView) view.findViewById(R.id.parents_kid);
		//parents_kid.setText(names_parents);
		parents_kid.setText(parents.get(0) + " y " + parents.get(1));
		//parents_kid.setId(i);

		RelativeLayout layout_kid = (RelativeLayout) view
				.findViewById(R.id.relative_kid);
		//layout_kid.setId(i);
		layout_kid.setId(kid.getId());
		
		
		
		layout_buttons.add(layout_kid);
		
		
		layout_kid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						JournalActivity.class);
				
				int aux=0;
				for(int u =0; u< kids.size();u++)
				{
					if(kids.get(u).getId() == v.getId())
					{
						aux = u;
					}
					
				}
				intent.putExtra("KID_ID", aux);
				intent.putExtra("KID_NAME", kids.get(aux).getName());
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent, 0);
				finish();
			}
		});
		
		layout_buttons.add(layout_kid);

		kidsTableLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
		
		//i = i +1;

	}

}