package de.ubuntudroid.appbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;

public class AppBarService extends Service {

	private static final int APP_BAR_NOTIFICATION = 0;
	private NotificationManager nm;
	private ActivityManager am;
	private List<RunningAppProcessInfo> runningApps;

	@Override
	public IBinder onBind(Intent intent) {
		/* not implemented as other Activities/Apps should not be able to connect to this service
		 * (except for the configurator?)
		 */
		return null;
	}
	
	@Override
	public void onCreate() {
		nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		am = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
		//this.setForeground(true);
		super.onCreate();
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		
		runningApps = am.getRunningAppProcesses();
		//TODO: check for the correct order by comparing with the list retrieved last time
		
		List<RunningAppProcessInfo> latestApps = getLatestApps(runningApps);
		List<Bitmap> appIcons = getLatestAppIcons(latestApps);
		
		AppBarNotification appBarNotification = new AppBarNotification(getPackageName(), R.layout.app_bar_notification);
		prepareButtonBitmaps(appBarNotification, appIcons);
		
//		appBarNotification.setOnClickPendingIntent(R.id.app1, pendingIntent);
		
		Notification notification = new Notification();
		notification.contentView = appBarNotification;
		
		nm.notify(APP_BAR_NOTIFICATION, notification);
	}

	private void prepareButtonBitmaps(AppBarNotification appBarNotification, List<Bitmap> appIcons) {
		appBarNotification.setImageViewBitmap(R.id.app1, appIcons.get(0));
		appBarNotification.setImageViewBitmap(R.id.app1, appIcons.get(1));
		appBarNotification.setImageViewBitmap(R.id.app1, appIcons.get(2));
		appBarNotification.setImageViewBitmap(R.id.app1, appIcons.get(3));
		appBarNotification.setImageViewBitmap(R.id.app1, appIcons.get(4));
	}

	private List<RunningAppProcessInfo> getLatestApps(List<RunningAppProcessInfo> runningApps) {
		
		//The sorting is based on the fact, that the smaller the lru of a process, the more recently it was used
		
		TreeMap<Integer, RunningAppProcessInfo> runningAppsAndLrus = new TreeMap<Integer, ActivityManager.RunningAppProcessInfo>();
		
		for (RunningAppProcessInfo pi:runningApps){
			runningAppsAndLrus.put(pi.lru, pi);
		}
		
		Collection<RunningAppProcessInfo> collection = runningAppsAndLrus.values();
		Iterator<RunningAppProcessInfo> iter = collection.iterator();
				
		List<RunningAppProcessInfo> firstFiveAppsList = new ArrayList<ActivityManager.RunningAppProcessInfo>();
		for (int i = 0; i < 5; i++){
			firstFiveAppsList.add(iter.next());
		}
		
		return firstFiveAppsList;
	}

	private List<Bitmap> getLatestAppIcons(List<RunningAppProcessInfo> latestApps) {
		List<Bitmap> icons = new ArrayList<Bitmap>();
		for (RunningAppProcessInfo pi: latestApps){
			try {
				icons.add(((BitmapDrawable) getPackageManager().getApplicationIcon(pi.pkgList[0])).getBitmap());
			} catch (NameNotFoundException e) {
				// TODO: cycle through all packages in pkgList until we find the correct one
				e.printStackTrace();
			}
		}
		return icons;
	}
	
}
