package com.familink;

import java.util.ArrayList;
import java.util.List;

import familink_model.Kid;

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
	List<RelativeLayout> layout_buttons;
	String name_string;
	List<Kid> kids;
	WebAPICommunicator api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        
        layout_buttons = new ArrayList<RelativeLayout>();

		kidsTableLayout = (LinearLayout) findViewById(R.id.kidsList);
		
		api = WebAPICommunicator.getInstance();
		kids = api.getKids(10);

			
		for(int h = 0; h < kids.size(); h++)
		{
			addKid(kids.get(h));
		}
                
        
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
    
      
    public void addKid(Kid kid) {
		
		View view = getLayoutInflater().inflate(R.layout.kids_layout, null);
		
		TextView name_kid = (TextView) view.findViewById(R.id.name_kid);
		name_kid.setText(kid.getName());
		
		String names_parents = "";
		for(int o = 0; o < kid.getGuardians().size(); o++)
		{
			if(kid.getGuardians().get(o) == kid.getGuardians().get(kid.getGuardians().size()-1))
			{
				names_parents = names_parents + kid.getGuardians().get(o);
			}
			else if(kid.getGuardians().get(o) == kid.getGuardians().get(kid.getGuardians().size()-2))
			{
				names_parents = names_parents + kid.getGuardians().get(o) +" y ";
			}
			else
			{
				names_parents = names_parents + kid.getGuardians().get(o) +" ";
			}
			
		}
		TextView parents_kid = (TextView) view.findViewById(R.id.parents_kid);
		parents_kid.setText(names_parents);
	
		RelativeLayout layout_kid = (RelativeLayout) view
				.findViewById(R.id.relative_kid);
		layout_kid.setId(kid.getId());

		layout_buttons.add(layout_kid);
		
		layout_kid.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						MessageKid.class);
				
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

	}
}
