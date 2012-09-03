package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MessageKid extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_kid);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_message_kid, menu);
        return true;
    }
}
