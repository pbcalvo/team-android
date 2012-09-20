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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import familink_model.Announcement;

public class AnnouncementsActivity extends Activity {

	int group_id;
	Button newActivityButton;
	Button journal;
	Button message;
	LinearLayout announcementsLinearLayout;
	List<RelativeLayout> layout_buttons;
	View clickView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_announcements);
		
		layout_buttons = new ArrayList<RelativeLayout>();

		announcementsLinearLayout = (LinearLayout) findViewById(R.id.annoucements_list);
		
		addAnnouncement(new Announcement(Calendar.getInstance(), "Reunión de apoderados",
				"Este Lunes 10 de septiembre nos juntaremos en la sala 12"));
		
		newActivityButton = (Button) findViewById(R.id.announcements_add_button);
		newActivityButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				clickView = getLayoutInflater().inflate(
						R.layout.new_announcement, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AnnouncementsActivity.this);
				builder.setTitle(R.string.announcement_new);
				builder.setView(clickView);

				builder.setPositiveButton(R.string.string_send,
						new DialogInterface.OnClickListener() {

							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								
								EditText subjectText = (EditText) clickView.findViewById(R.id.subject_text);
								EditText messageText = (EditText) clickView.findViewById(R.id.message_text);
								
								if (!subjectText.getText().toString().equals("") &&
										!messageText.getText().toString().equals(""))
								{
									Announcement announcement = new Announcement(Calendar.getInstance(), subjectText.getText().toString(),
											messageText.getText().toString());
									
									WebAPICommunicator communicator = WebAPICommunicator.getInstance();
									communicator.sendAnnouncement(group_id, announcement);
									
									addAnnouncement(announcement);
									
									dialog.dismiss();
									Toast.makeText(AnnouncementsActivity.this,
											R.string.announcement_sent,
											Toast.LENGTH_SHORT).show();
								}

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
						FamilinkAndroidActivity.class);
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
		default:
			return super.onOptionsItemSelected(item);
		}
	}
	
	public void addAnnouncement(Announcement announcement) {

		View view = getLayoutInflater().inflate(R.layout.announcement_layout,
				null);

		TextView content_text = (TextView) view.findViewById(R.id.announcement_content);
		content_text.setText(announcement.getMessage());
		
		TextView title_text = (TextView) view.findViewById(R.id.announcement_title);
		title_text.setText(announcement.getSubject());

		TextView date_text = (TextView) view.findViewById(R.id.announcement_date);
		date_text.setText(announcement.getDate().get(Calendar.HOUR_OF_DAY) + ":" + announcement.getDate().get(Calendar.MINUTE) + ", today");

		RelativeLayout announcement_layout = (RelativeLayout) view
				.findViewById(R.id.announcement);
		announcement_layout.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				clickView = getLayoutInflater().inflate(
						R.layout.announcement_form, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(
						AnnouncementsActivity.this);
				
				TextView title_text = (TextView) v.findViewById(R.id.announcement_title);
				TextView content_text = (TextView) v.findViewById(R.id.announcement_content);
				TextView date_text = (TextView) v.findViewById(R.id.announcement_date);
				
				builder.setTitle(title_text.getText().toString());
				
				TextView announcement_show_content = (TextView) clickView.findViewById(R.id.announcement_show_content);
				announcement_show_content.setText(content_text.getText().toString());
				
				TextView announcement_show_date = (TextView) clickView.findViewById(R.id.announcement_show_date);
				announcement_show_date.setText(date_text.getText().toString());
				
				builder.setView(clickView);
				
				builder.setPositiveButton("OK",
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

		layout_buttons.add(announcement_layout);

		announcementsLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}
}
