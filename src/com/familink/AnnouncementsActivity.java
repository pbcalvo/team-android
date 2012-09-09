package com.familink;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AnnouncementsActivity extends Activity {

	Button newActivityButton;
	Button journal;
	Button message;
	LinearLayout announcementsLinearLayout;
	List<RelativeLayout> layout_buttons;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_announcements);
		
		layout_buttons = new ArrayList<RelativeLayout>();

		announcementsLinearLayout = (LinearLayout) findViewById(R.id.annoucements_list);
		
		addAnnouncement("Reuni�n de apoderados", "Este Lunes 10 de septiembre nos juntaremos en la sala 12", Calendar.getInstance());
		

		newActivityButton = (Button) findViewById(R.id.announcements_add_button);
		newActivityButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				View view = getLayoutInflater().inflate(
						R.layout.new_announcement, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AnnouncementsActivity.this);
				builder.setTitle(R.string.announcement_new);
				builder.setView(view);

				builder.setPositiveButton(R.string.string_send,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
								Toast.makeText(AnnouncementsActivity.this,
										R.string.announcement_sent,
										Toast.LENGTH_SHORT).show();

							}
						});

				builder.setNegativeButton(R.string.string_cancel,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
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
				Intent intent = new Intent(getBaseContext(),
						MessageActivity.class);
				intent.putExtra("GROUP_ID", 1);
				startActivityForResult(intent, 0);
				finish();
			}
		});

		journal = (Button) findViewById(R.id.journal_button);
		journal.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(getBaseContext(),
						JournalActivity.class);
				intent.putExtra("GROUP_ID", 1);
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
			// Volver a la selecci�n de grupos.
			return true;
		case R.id.campus_menu_button:
			// Idem al anterior.
			return true;
		case R.id.settings_menu_button:
			// Ventana de setting, a�n no implementada.
			return true;
		case R.id.logout_menu_button:
			// Logout, tampoco implementado.
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void addAnnouncement(String title, String content, Calendar date) {

		View view = getLayoutInflater().inflate(R.layout.announcement_layout,
				null);

		TextView content_text = (TextView) view.findViewById(R.id.announcement_content);
		content_text.setText(content);
		
		TextView title_text = (TextView) view.findViewById(R.id.announcement_title);
		title_text.setText(title);

		TextView date_text = (TextView) view.findViewById(R.id.announcement_date);
		date_text.setText(date.HOUR + ":" + date.MINUTE + ", today");

		RelativeLayout announcement_layout = (RelativeLayout) view
				.findViewById(R.id.announcement);
		announcement_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*
				 * Intent intent = new Intent(getBaseContext(),
				 * JournalActivity.class); startActivityForResult(intent, 0);
				 * finish();
				 */
			}
		});

		layout_buttons.add(announcement_layout);

		announcementsLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}
}
