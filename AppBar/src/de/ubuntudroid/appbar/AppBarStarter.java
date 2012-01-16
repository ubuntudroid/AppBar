package de.ubuntudroid.appbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AppBarStarter extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("AppBar", "received intent " + intent.getAction());
		if (intent.getAction() != null){
			if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)||intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
				Intent serviceIntent = new Intent();
				serviceIntent.setAction("de.ubuntudroid.appbar.AppBarService");
				context.startService(serviceIntent);
			} else if (intent.getAction().equals(AppBarService.START_ACTIVITY_INTENT)){
				AppBarService.getInstance().startActivity(intent.getIntExtra(AppBarService.ACTIVITY_ID_EXTRA, -1));
			}
		}
	}

}
