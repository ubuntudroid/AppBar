package de.ubuntudroid.appbar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AppBarStarter extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.i("AppBar", "received intent " + intent.getAction());
		if (intent.getAction() != null){
			Intent serviceIntent = new Intent();
			serviceIntent.setAction("de.ubuntudroid.appbar.AppBarService");
			if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
				context.startService(serviceIntent);
			} else if (intent.getAction().equals(AppBarService.START_ACTIVITY_INTENT)){
				if (AppBarService.getInstance() != null) {
					AppBarService.getInstance().startActivity(intent.getIntExtra(AppBarService.ACTIVITY_ID_EXTRA, -1));
				} else {
					Toast.makeText(context, "AppBar-Service seems to have been removed by system. Restarting...", Toast.LENGTH_LONG).show();
				}
			}
		}
	}

}
