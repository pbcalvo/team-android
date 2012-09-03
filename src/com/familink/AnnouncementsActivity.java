package com.familink;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AnnouncementsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_announcements, menu);
        return true;
    }
}
