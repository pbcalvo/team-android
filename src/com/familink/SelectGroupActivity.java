package com.familink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectGroupActivity extends Activity{
	
	Button button; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_group_layout);
        
        Intent sender = getIntent();
        String extraData = sender.getExtras().getString("ComingFrom");
        
        button = (Button) findViewById(R.id.group1);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				//Ojo: por ahora t‡ harcodeado el 1.
				intent.putExtra("ComingFrom", 1);
				setResult(RESULT_OK,intent);
				finish();
			}
		});
    }

	
}
