package com.familink;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

public class WebActivity extends Activity {
	
	Button journal, message, announcement, calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        
        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("http://familink.cl/en");
        
        journal = (Button) findViewById(R.id.journal_button);
		journal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getBaseContext(),
						FamilinkAndroidActivity.class);
				startActivityForResult(intent, 0);
				finish();
			}
		});

		message = (Button) findViewById(R.id.message_button);
		message.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						MessageActivity.class);
				startActivityForResult(intent, 0);
				finish();
			}
		});

		announcement = (Button) findViewById(R.id.announcement_button);
		announcement.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						AnnouncementsActivity.class);
				startActivityForResult(intent, 0);
				finish();
			}
		});

		calendar = (Button) findViewById(R.id.calendar_button);
		calendar.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(getBaseContext(),
				// SelectGroupActivity.class);
				// startActivityForResult(intent, 0);
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
			// Volver a la selección de grupos.
			return true;
		case R.id.campus_menu_button:
			// Idem al anterior.
			return true;
		case R.id.settings_menu_button:
			// Ventana de setting, aún no implementada.
			return true;
		case R.id.logout_menu_button:
			// Logout, tampoco implementado.
			return true;
		case R.id.testing_internet_button:
			Intent intent = new Intent(getBaseContext(),
					WebActivity.class);
			startActivityForResult(intent, 0);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
}
