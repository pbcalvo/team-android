package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MenuButtonActivity extends Activity {

	//Esta es la actividad que se inicia cuando se presiona el bot�n menu. 
	//Es decir, es lo que se llama en los m�todos onCreateOptionsMenu. 
	
	//Al apretar dicho bot�n en esta actividad, �sta deber�a cerrarse.
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_button);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_button, menu);
        return true;
    }
}
