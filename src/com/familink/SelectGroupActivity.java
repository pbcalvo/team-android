package com.familink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SelectGroupActivity extends Activity{
	
	RelativeLayout button; 
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_group_layout);
        
        ImageView myImageView= (ImageView)findViewById(R.id.familink_logo);
        Animation myFadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fadein);
        myImageView.startAnimation(myFadeInAnimation);
                
        button = (RelativeLayout) findViewById(R.id.group1);
        
        button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(), FamilinkAndroidActivity.class);
				intent.putExtra("GROUP_ID", 1);
				startActivityForResult(intent,0);
				finish();
			}
		});
    }
	
	//Este menu no tiene la selección ni de Grupos ni de Campus (porque por ahora son lo mismo).
	//Cuando tengamos ambos, hay que modificarlo.
	@Override 
	public boolean onPrepareOptionsMenu(Menu menu){
		MenuItem mi = menu.findItem(R.id.group_settings);
		mi.setVisible(false); 
		mi.setEnabled(false); 
		return super.onPrepareOptionsMenu(menu);
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
