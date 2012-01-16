package de.ubuntudroid.appbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class AppBarInfo extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		Intent serviceIntent = new Intent();
		serviceIntent.setAction("de.ubuntudroid.appbar.AppBarService");
		this.startService(serviceIntent);
	}
	
}
