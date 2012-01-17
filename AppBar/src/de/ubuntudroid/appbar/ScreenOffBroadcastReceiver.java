package de.ubuntudroid.appbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenOffBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() != null && intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
			Intent serviceIntent = new Intent();
			serviceIntent.setAction("de.ubuntudroid.appbar.AppBarService");
			context.stopService(serviceIntent);
		}

	}

}
