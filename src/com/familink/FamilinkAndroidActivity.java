package com.familink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FamilinkAndroidActivity extends Activity {
    /** Called when the activity is first created. */
	
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
        setContentView(R.layout.home);
        
        group_id = this.getIntent().getIntExtra("GROUP_ID", 0);
        
        title = (TextView) findViewById(R.id.titulo);
        title.setText("Journal");
                
        if(group_id == 0) {
        	Intent intent = new Intent(getBaseContext(), SelectGroupActivity.class);
        	startActivityForResult(intent, 0);
        	finish();
        }
        
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
				//Intent intent = new Intent(getBaseContext(), SelectGroupActivity.class);
	        	//startActivityForResult(intent, 0);				
			}
		});
        
        announcement = (Button) findViewById(R.id.announcement_button);
        announcement.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(getBaseContext(), SelectGroupActivity.class);
	        	//startActivityForResult(intent, 0);				
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
        
        kid1 = (RelativeLayout) findViewById(R.id.relative_kid1);
        kid1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), JournalActivity.class);
				intent.putExtra("KID_ID", 1);
				intent.putExtra("KID_NAME", "Amanda Solis");
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent,0);
				finish();
			}
		});
        
    }
    
}