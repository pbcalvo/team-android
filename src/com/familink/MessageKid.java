package com.familink;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageKid extends Activity {

	int kid_id, group_id;
	String kid_name;
	Button journal;
	Button message;
	Button announcement;
	Button calendar;
	TextView title;
	LinearLayout messagesLinearLayout;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_kid);
        
        messagesLinearLayout = (LinearLayout) findViewById(R.id.message_list);
        
        kid_id = this.getIntent().getIntExtra("KID_ID", 0);
		kid_name = this.getIntent().getStringExtra("KID_NAME");
		group_id = this.getIntent().getIntExtra("GROUP_ID", 0);

		title = (TextView) findViewById(R.id.titulo);
		title.setText(kid_name);
		
		addChat("", "Amanda Solis", "Hola que tal?", Calendar.getInstance());
		addChat("", "Jenny Bravo", "Hola bien y ud?", Calendar.getInstance());
        
        message = (Button) findViewById(R.id.message_button);
        message.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(getBaseContext(), MessageActivity.class);
	        	//startActivityForResult(intent, 0);				
			}
		});
        
        journal = (Button) findViewById(R.id.journal_button);
        journal.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub	
				Intent intent = new Intent(getBaseContext(), JournalActivity.class);
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
				Intent intent = new Intent(getBaseContext(), AnnouncementsActivity.class);
	        	startActivityForResult(intent, 0);	
	        	finish();
			}
		});
        
        calendar = (Button) findViewById(R.id.calendar_button);
        calendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Intent intent = new Intent(getBaseContext(), SelectGroupActivity.class);
	        	//startActivityForResult(intent, 0);				
			}
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu_button, menu);
        return true;
    }
    
    public void addChat(String picture, String name, String content, Calendar date) {

		View view = getLayoutInflater().inflate(R.layout.message_layout,
				null);
		
		TextView name_text = (TextView) view.findViewById(R.id.chat_name);
		name_text.setText(name);
		
		TextView content_text = (TextView) view.findViewById(R.id.chat_content);
		content_text.setText(content);

		TextView date_text = (TextView) view.findViewById(R.id.chat_date);
		date_text.setText(date.HOUR + ":" + date.MINUTE + ", today");


		messagesLinearLayout.addView(view, 0, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

	}
    
    
}
