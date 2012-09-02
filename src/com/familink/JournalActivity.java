package com.familink;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class JournalActivity extends Activity {
	
	TextView title;
	int kid_id, group_id;
	String kid_name;
	Button back_group, new_obs, new_meal, new_nap, new_depo, add_obs, cancel_obs;
	Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);
        
               
        kid_id = this.getIntent().getIntExtra("KID_ID", 0);
        kid_name = this.getIntent().getStringExtra("KID_NAME");
        group_id = this.getIntent().getIntExtra("GROUP_ID", 0);
        
        title = (TextView) findViewById(R.id.titulo);
        title.setText(kid_name);
        
        back_group = (Button) findViewById(R.id.back_group);
        back_group.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), FamilinkAndroidActivity.class);
				intent.putExtra("GROUP_ID", group_id);
				startActivityForResult(intent,0);
				finish();
			}
		});
        
        
		
        new_obs = (Button) findViewById(R.id.obs_add_button);
        new_obs.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				View view = getLayoutInflater().inflate( R.layout.observation_form, null );
				dialog = new Dialog(JournalActivity.this);
				dialog.setContentView(view);
				dialog.setTitle("Add a new observation");
				dialog.show();
				
				
			}
		});
        
        
        
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_journal, menu);
        return true;
    }
}
