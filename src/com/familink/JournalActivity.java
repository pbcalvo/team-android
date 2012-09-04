package com.familink;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
	Button journal;
	Button message;
	Button announcement;

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
				AlertDialog.Builder builder = new AlertDialog.Builder(JournalActivity.this);
			    builder.setTitle("Add a new observation");
			    builder.setView(view);

			    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

			        @Override
					public void onClick(DialogInterface dialog, int which) {
			            // Do nothing but close the dialog
			            dialog.dismiss();
			            Toast.makeText(JournalActivity.this, "Observation created", Toast.LENGTH_SHORT).show();
			        }

			    });

			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing
			            dialog.dismiss();
			        }
			    });
			    
			    builder.create().show();
				
			}
		});
        
        new_meal = (Button) findViewById(R.id.meals_add_button);
        new_meal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				View view = getLayoutInflater().inflate( R.layout.meals_form, null );				
				AlertDialog.Builder builder = new AlertDialog.Builder(JournalActivity.this);
			    builder.setTitle("Add a new meal");
			    builder.setView(view);

			    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

			        @Override
					public void onClick(DialogInterface dialog, int which) {
			            // Do nothing but close the dialog
			            dialog.dismiss();
			            Toast.makeText(JournalActivity.this, "Meal created", Toast.LENGTH_SHORT).show();
			        }

			    });

			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing
			            dialog.dismiss();
			        }
			    });
			    
			    builder.create().show();
				
			}
		});
        
        new_nap = (Button) findViewById(R.id.naps_add_button);
        new_nap.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				View view = getLayoutInflater().inflate( R.layout.nap_form, null );				
				AlertDialog.Builder builder = new AlertDialog.Builder(JournalActivity.this);
			    builder.setTitle("Add a new nap");
			    builder.setView(view);

			    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

			        @Override
					public void onClick(DialogInterface dialog, int which) {
			            // Do nothing but close the dialog
			            dialog.dismiss();
			            Toast.makeText(JournalActivity.this, "Nap created", Toast.LENGTH_SHORT).show();
			        }

			    });

			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing
			            dialog.dismiss();
			        }
			    });
			    
			    builder.create().show();
				
			}
		});
        
        new_depo = (Button) findViewById(R.id.depos_add_button);
        new_depo.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				View view = getLayoutInflater().inflate( R.layout.depo_form, null );				
				AlertDialog.Builder builder = new AlertDialog.Builder(JournalActivity.this);
			    builder.setTitle("Add a new deposition");
			    builder.setView(view);

			    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {

			        @Override
					public void onClick(DialogInterface dialog, int which) {
			            // Do nothing but close the dialog
			            dialog.dismiss();
			            Toast.makeText(JournalActivity.this, "Deposition created", Toast.LENGTH_SHORT).show();
			        }

			    });

			    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

			        @Override
			        public void onClick(DialogInterface dialog, int which) {
			            // Do nothing
			            dialog.dismiss();
			        }
			    });
			    
			    builder.create().show();
				
			}
		});
        
        
        message = (Button) findViewById(R.id.message_button);
        message.setOnClickListener(new View.OnClickListener() {
			
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
}
