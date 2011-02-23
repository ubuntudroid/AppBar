package de.ubuntudroid.appbar;

import android.widget.RemoteViews;

public class AppBarNotification extends RemoteViews {

	private AppBarService service;
	
	public AppBarNotification(String packageName, int layoutId, AppBarService service) {
		super(packageName, layoutId);
		// TODO Auto-generated constructor stub
	}
	
	public AppBarNotification(AppBarService service){
		super("de.ubuntudroid.appbar", R.layout.app_bar_notification);
	}
	
	@Override
	public boolean onLoadClass(Class clazz) {
		// TODO Auto-generated method stub
		return super.onLoadClass(clazz);
	}

}
