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

package de.ubuntudroid.appbar.helpers;

public final class SystemPackages {
	
	//TODO: optimize! Use ArrayList?
	
	static final String[] systemPackages = {"com.android.settings",
											"com.android.browser"};
	
	public static boolean isSystemPackage(String packageName){
		int size = systemPackages.length;
		
		for (int i = 0; i < size; i++){
			if (systemPackages[i].equals(packageName)){
				return true;
			}
		}
		
		return false;
	}
}
