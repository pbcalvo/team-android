package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
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
}
