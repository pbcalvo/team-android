package com.familink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FamilinkAndroidActivity extends Activity {
    /** Called when the activity is first created. */
	
	private int group_id;
	
	TextView prueba; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        
        if(group_id != 1) {
        	Intent intent = new Intent(getBaseContext(), SelectGroupActivity.class);
        	intent.putExtra("ComingFrom","FamilinkAndroidActivity");
        	final int result = 0; 
        	startActivityForResult(intent, result);
        }
        
        group_id=1;
    }
    
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
    	super.onActivityResult(requestCode, resultCode, data);
    	//estoy asumiendo que el default value es el que queda si hubo un cancel.
    	group_id = data.getIntExtra("ComingFrom", 0);
    	group_id = 1;
    	
    	prueba = (TextView) findViewById(R.id.resultado);
    	prueba.setText("1");
    }
}