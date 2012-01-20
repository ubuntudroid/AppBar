/**
 * AppBar - a simple application switcher for Android
 * Copyright (C) 2012  Sven Bendel (ubuntudroid@googlemail.com)
 *
 * This file is part of AppBar.
 *
 * AppBar is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.

 * AppBar is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with AppBar. If not, see <http://www.gnu.org/licenses/>.
 */

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
