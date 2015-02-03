package ir.chocolategroup.majiddelbandam;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrefrencesManager {

	public SharePrefrencesManager() {
	}
	public static void addValue(Context context , String key , String value)
	{
		SharedPreferences sharedPref =  context.getSharedPreferences(context.getString(R.string.SharedPreferencesFileName), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putString(key, value);
		editor.commit();
	}
	public static boolean isExistKey(Context context ,String key)
	{
		SharedPreferences sharedPref =  context.getSharedPreferences(context.getString(R.string.SharedPreferencesFileName), Context.MODE_PRIVATE);
		return sharedPref.contains(key);
	}
	public static String getValue(Context context ,String key)
	{
		SharedPreferences sharedPref =  context.getSharedPreferences(context.getString(R.string.SharedPreferencesFileName), Context.MODE_PRIVATE);
		return sharedPref.getString(key, "");
	}
}

