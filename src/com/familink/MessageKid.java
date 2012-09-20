package com.familink;

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import familink_model.Message;

public class MessageKid extends Activity {

	int kid_id, group_id;
	String kid_name, teacher_name;
	Button journal;
	Button message;
	Button announcement;
	Button calendar;
	Button back_group;
	Button send_button;
	EditText insert_message;
	TextView title;
	LinearLayout messagesLinearLayout;
	LinearLayout message_menu;
	ScrollView scroller;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_kid);

		messagesLinearLayout = (LinearLayout) findViewById(R.id.message_list);
		insert_message = (EditText) findViewById(R.id.insert_message);
		send_button = (Button) findViewById(R.id.send_button);
		message_menu = (LinearLayout) findViewById(R.id.message_menu);
		scroller = (ScrollView) findViewById(R.id.scrollview_messages);

		kid_id = this.getIntent().getIntExtra("KID_ID", 0);
		kid_name = this.getIntent().getStringExtra("KID_NAME");
		group_id = this.getIntent().getIntExtra("GROUP_ID", 0);

		title = (TextView) findViewById(R.id.titulo);
		title.setText(kid_name);

		// Hardcoded by now
		teacher_name = "Jenny Bravo";

		addChat1("", new Message(Calendar.getInstance(), "Amanda Solis",
				"Hola que tal?"));
		addChat("", new Message(Calendar.getInstance(), teacher_name,
				"Hola bien y ud?"));

		send_button.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				if (!insert_message.getText().toString().equals("")) {
					Message newMessage = new Message(Calendar.getInstance(),
							teacher_name, insert_message.getText().toString());

					insert_message.setText("");

					WebAPICommunicator communicator = WebAPICommunicator
							.getInstance();
					communicator.sendMessage(kid_id, newMessage);

					addChat("", newMessage);

					message_menu.setVisibility(View.VISIBLE);

					InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

					scroller.post(new Runnable() {
						public void run() {
							scroller.fullScroll(ScrollView.FOCUS_DOWN);
						}
					});
				}

			}
		});

		message = (Button) findViewById(R.id.message_button);
		message.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Intent intent = new Intent(getBaseContext(),
				// MessageActivity.class);
				// startActivityForResult(intent, 0);
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

	public void addChat(String picture, Message message) {

		View view = getLayoutInflater().inflate(R.layout.message_layout, null);

		TextView name_text = (TextView) view.findViewById(R.id.chat_name);
		name_text.setText(message.getSender());

		TextView content_text = (TextView) view.findViewById(R.id.chat_content);
		content_text.setText(message.getMessage());

		TextView date_text = (TextView) view.findViewById(R.id.chat_date);
		date_text.setText(message.getDate().get(Calendar.HOUR_OF_DAY) + ":"
				+ message.getDate().get(Calendar.MINUTE) + ", today");

		messagesLinearLayout.addView(view, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}

	public void addChat1(String picture, Message message) {

		View view = getLayoutInflater().inflate(
				R.layout.message_parents_layout, null);

		TextView name_text = (TextView) view.findViewById(R.id.chat_name);
		name_text.setText(message.getSender());

		TextView content_text = (TextView) view.findViewById(R.id.chat_content);
		content_text.setText(message.getMessage());

		TextView date_text = (TextView) view.findViewById(R.id.chat_date);
		date_text.setText(message.getDate().get(Calendar.HOUR_OF_DAY) + ":"
				+ message.getDate().get(Calendar.MINUTE) + ", today");

		messagesLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if ((keyCode == KeyEvent.KEYCODE_BACK)) {
			Intent intent = new Intent(getBaseContext(), MessageActivity.class);
			intent.putExtra("GROUP_ID", group_id);
			startActivityForResult(intent, 0);
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}

}
