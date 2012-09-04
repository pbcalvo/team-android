package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AnnouncementsActivity extends Activity {

	Button newActivityButton;
	
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_button, menu);
        return true;
    }
}
