package de.ubuntudroid.appbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenBroadcastReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() != null) {
			if (intent.getAction().equals(Intent.ACTION_SCREEN_OFF)) {
				if (AppBarService.getInstance() != null) {
					AppBarService.getInstance().stopNotificationUpdating();
				}
			} else if (intent.getAction().equals(Intent.ACTION_SCREEN_ON)) {
				if (AppBarService.getInstance() != null) {
					AppBarService.getInstance().startNotificationUpdating();
				} else {
					Intent serviceIntent = new Intent();
					serviceIntent.setAction("de.ubuntudroid.appbar.AppBarService");
					context.startService(serviceIntent);
				}
			}
		}

	}

}
