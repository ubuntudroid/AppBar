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
