package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageActivity extends Activity {

	private int group_id = 0;
	private int kid_id;
	
	TextView prueba; 
	TextView title;
	Button journal;
	Button message;
	Button announcement;
	Button calendar;
	RelativeLayout kid1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message);
        
        
        kid1 = (RelativeLayout) findViewById(R.id.relative_kid1);
        kid1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), MessageKid.class);
				intent.putExtra("KID_ID", 1);
				intent.putExtra("KID_NAME", "Amanda Solis");
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent,0);
				finish();
			}
		});
        
        
        
        
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
}
