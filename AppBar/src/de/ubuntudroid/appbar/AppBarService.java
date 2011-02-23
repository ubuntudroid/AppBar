package de.ubuntudroid.appbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import android.app.ActivityManager;
import android.app.ActivityManager.RecentTaskInfo;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.IBinder;
import android.util.Log;

public class AppBarService extends Service {

	private static final int APP_BAR_NOTIFICATION = 0;
	private NotificationManager nm;
	private ActivityManager am;

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
	
	@SuppressWarnings("static-access")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					reloadNotification();
					try {
						Thread.currentThread().sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private void prepareButtonBitmaps(AppBarNotification appBarNotification, List<Bitmap> appIcons) {
		//TODO: really ugly!
		int apps = appIcons.size();
		if (apps == 0){
			return;
		}
		appBarNotification.setImageViewBitmap(R.id.app1, appIcons.get(0));
		if (apps == 1){
			return;
		}
		appBarNotification.setImageViewBitmap(R.id.app2, appIcons.get(1));
		if (apps == 2){
			return;
		}
		appBarNotification.setImageViewBitmap(R.id.app3, appIcons.get(2));
		if (apps == 3){
			return;
		}
		appBarNotification.setImageViewBitmap(R.id.app4, appIcons.get(3));
		if (apps == 4){
			return;
		}
		appBarNotification.setImageViewBitmap(R.id.app5, appIcons.get(4));
	}
	
	private void prepareButtonClickListeners(AppBarNotification appBarNotification, List<PendingIntent> appPendingIntents) {
		//TODO: really ugly!
		int apps = appPendingIntents.size();
		if (apps == 0){
			return;
		}
		appBarNotification.setOnClickPendingIntent(R.id.app1, appPendingIntents.get(0));
		if (apps == 1){
			return;
		}
		appBarNotification.setOnClickPendingIntent(R.id.app2, appPendingIntents.get(1));
		if (apps == 2){
			return;
		}
		appBarNotification.setOnClickPendingIntent(R.id.app3, appPendingIntents.get(2));
		if (apps == 3){
			return;
		}
		appBarNotification.setOnClickPendingIntent(R.id.app4, appPendingIntents.get(3));
		if (apps == 4){
			return;
		}
		appBarNotification.setOnClickPendingIntent(R.id.app5, appPendingIntents.get(4));
	}

	private List<RecentTaskInfo> getFiveRecentTasks(List<RecentTaskInfo> recentTasks) {
		List<RecentTaskInfo> fiveRecentTasks = new ArrayList<RecentTaskInfo>();
		int i = 0;
		for (RecentTaskInfo rti: recentTasks){
			if (i > 4){
				return fiveRecentTasks;
			}
			
			Intent baseIntent = rti.baseIntent;
			if (baseIntent != null){
				Set<String> categories = baseIntent.getCategories();
				if ((categories != null && categories.contains(Intent.CATEGORY_LAUNCHER)) || (baseIntent.getAction() != null && baseIntent.getAction().equals("android.settings.SETTINGS"))){
					fiveRecentTasks.add(rti);
					i++;
				}
			}
		}
		return fiveRecentTasks;
	}

	private List<Bitmap> getRecentTaskIcons(List<RecentTaskInfo> recentTasks) {
		List<Bitmap> icons = new ArrayList<Bitmap>();
		for (RecentTaskInfo rti : recentTasks){
			try {
				icons.add(((BitmapDrawable) getPackageManager().getApplicationIcon(rti.baseIntent.getComponent().getPackageName())).getBitmap());
			} catch (NameNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return icons;
	}

	private List<PendingIntent> getRecentTaskPendingIntents(List<RecentTaskInfo> recentTasks) {
		List<PendingIntent> appPendingIntents = new ArrayList<PendingIntent>();
		int i = 0;
		for (RecentTaskInfo rti : recentTasks){
			appPendingIntents.add(PendingIntent.getActivity(getApplicationContext(), 0, rti.baseIntent, 0));
			i++;
		}
		return appPendingIntents;
	}
	
	private void reloadNotification() {
		Log.v("AppBar", "Reloading running app list...");
		
		List<RecentTaskInfo> recentTasks = am.getRecentTasks(50, 0);
		List<RecentTaskInfo> fiveRecentTasks = getFiveRecentTasks(recentTasks);
		List<Bitmap> appIcons = getRecentTaskIcons(fiveRecentTasks);
		List<PendingIntent> appPendingIntents = getRecentTaskPendingIntents(fiveRecentTasks);
		
		AppBarNotification appBarNotification = new AppBarNotification(getPackageName(), R.layout.app_bar_notification);
		prepareButtonBitmaps(appBarNotification, appIcons);
		prepareButtonClickListeners(appBarNotification, appPendingIntents);
		
		Notification notification = new Notification();
		notification.contentView = appBarNotification;
		notification.flags |= Notification.FLAG_ONGOING_EVENT;  
		notification.flags |= Notification.FLAG_NO_CLEAR;
		
		Intent notificationIntent = new Intent();
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);
		notification.contentIntent = contentIntent;
		notification.icon = R.drawable.icon;
		nm.notify(APP_BAR_NOTIFICATION, notification);
	}
}
