package com.familink;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private int group_id = 0;
	private int kid_id;
	
	Button journal;
	Button message;
	Button announcement;
	Button calendar;
	LinearLayout kidsTableLayout;
	List<String> parents;
	List<RelativeLayout> layout_buttons;
	String name_string;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        
        layout_buttons = new ArrayList<RelativeLayout>();

		kidsTableLayout = (LinearLayout) findViewById(R.id.kidsList);

		parents = new ArrayList<String>();
		parents.add("Papa");
		parents.add("Mama");

		addKid("Amanda Solis");
		addKid("Juan Perez");      
                
        
        message = (Button) findViewById(R.id.message_button);
        message.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(getBaseContext(), MessageActivity.class);
	        	//startActivityForResult(intent, 0);		
	        	//finish();
			}
			
			
		});
        
     
        
        journal = (Button) findViewById(R.id.journal_button);
        journal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				Intent intent = new Intent(getBaseContext(), FamilinkAndroidActivity.class);
				intent.putExtra("GROUP_ID", 1);
	        	startActivityForResult(intent, 0);	
	        	finish();
			}
		});
        
      
        announcement = (Button) findViewById(R.id.announcement_button);
        announcement.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), AnnouncementsActivity.class);
	        	startActivityForResult(intent, 0);	
	        	finish();
			}
		});
        
        calendar = (Button) findViewById(R.id.calendar_button);
        calendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(getBaseContext(), SelectGroupActivity.class);
	        	//startActivityForResult(intent, 0);				
			}
		});
      
        
        
    }
    

    
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_button, menu);
        return true;
    }
    
    
    //Ojo: no he copiado este en MessageKid.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.groups_menu_button:
                //Volver a la selección de grupos.
                return true;
            case R.id.campus_menu_button:
                //Idem al anterior.
                return true;
            case R.id.settings_menu_button:
            	//Ventana de setting, aún no implementada.
            	return true; 
            case R.id.logout_menu_button:
            	//Logout, tampoco implementado.
            	return true; 
            default:
                return super.onOptionsItemSelected(item);
        }
    } 
    
    public void addKid(String name) {
		name_string = name;

		View view = getLayoutInflater().inflate(R.layout.kids_layout, null);

		TextView name_kid = (TextView) view.findViewById(R.id.name_kid);
		name_kid.setText(name);

		TextView parents_kid = (TextView) view.findViewById(R.id.parents_kid);
		parents_kid.setText(parents.get(0) + " y " + parents.get(1));

		RelativeLayout layout_kid = (RelativeLayout) view
				.findViewById(R.id.relative_kid);
		layout_kid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						MessageKid.class);
				intent.putExtra("KID_ID", 1);
				intent.putExtra("KID_NAME", name_string);
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent, 0);
				finish();
			}
		});

		layout_buttons.add(layout_kid);

		kidsTableLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}
}
