package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AnnouncementsActivity extends Activity {

	Button newActivityButton;
	Button journal;
	Button message;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        
        newActivityButton = (Button) findViewById(R.id.add_announcement_button);
        newActivityButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				View view = getLayoutInflater().inflate( R.layout.new_announcement, null );
				AlertDialog.Builder builder = new AlertDialog.Builder(AnnouncementsActivity.this);
				builder.setTitle("Create a new announcement");
			    builder.setView(view);
			    
			    builder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
			            Toast.makeText(AnnouncementsActivity.this, "Announcement sent", Toast.LENGTH_SHORT).show();
						
					}
				});
			    
			    
			    
			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						
					}
				});
				
			    builder.create().show();
			}

			
		});
        
        /*message = (Button) findViewById(R.id.message_button);
        message.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), MessageActivity.class);
	        	startActivityForResult(intent, 0);	
	        	finish();
			}
		});
        journal = (Button) findViewById(R.id.journal_button);
        journal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				Intent intent = new Intent(getBaseContext(), JournalActivity.class);
				intent.putExtra("GROUP_ID", 1);
	        	startActivityForResult(intent, 0);	
	        	finish();
			}
		});*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_button, menu);
        return true;
    }
}
