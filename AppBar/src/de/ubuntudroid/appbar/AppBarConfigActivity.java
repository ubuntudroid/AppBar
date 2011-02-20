package de.ubuntudroid.appbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AppBarConfigActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override
    protected void onResume() {
    	super.onResume();
    	this.startService(new Intent(this, AppBarService.class));
    }
}