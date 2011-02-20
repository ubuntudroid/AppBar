package de.ubuntudroid.appbar;

import android.widget.RemoteViews;

public class AppBarNotification extends RemoteViews {

	public AppBarNotification(String packageName, int layoutId) {
		super(packageName, layoutId);
		// TODO Auto-generated constructor stub
	}
	
	public AppBarNotification(){
		super("de.ubuntudroid.appbar", R.layout.app_bar_notification);
	}

}
