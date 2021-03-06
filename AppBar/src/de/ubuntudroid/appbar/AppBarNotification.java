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
