package de.ubuntudroid.appbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AppBarStarter extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction() != null){
			if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)||intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
				Intent serviceIntent = new Intent();
				serviceIntent.setAction("de.ubuntudroid.appbar.AppBarService");
				context.startService(serviceIntent);
			}
		}
	}

}
