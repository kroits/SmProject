package kssproject.com.smproject;

import android.content.Context;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by b3216 on 2017-05-28.
 */

public class SharedPreferences {
    public static void saveSettingintItem(Context context, String item, int itemValue){
        android.content.SharedPreferences shareData = context.getSharedPreferences(item, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = shareData.edit();
        editor.putInt(item,itemValue);
        editor.apply();
    }
    public static void saveSettingfloatItem(Context context, String item, float itemValue){
        android.content.SharedPreferences shareData = context.getSharedPreferences(item, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = shareData.edit();
        editor.putFloat(item,itemValue);
        editor.apply();
    }
    public static void saveSettingItem(Context context, String item, String itemValue){
        android.content.SharedPreferences shareData = context.getSharedPreferences(item, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = shareData.edit();
        editor.putString(item,itemValue);
        editor.apply();
    }

    public static int getSettingintItem(Context context, String item){
        return context.getSharedPreferences(item,MODE_PRIVATE).getInt(item,0);
    }
    public static String getSettingItem(Context context, String item){
        return context.getSharedPreferences(item,MODE_PRIVATE).getString(item,null);
    }
    public static float getSettingfloatItem(Context context, String item){
        return context.getSharedPreferences(item,MODE_PRIVATE).getFloat(item,0);
    }

    private static void removeSettingintItem(Context context,String item, String itemValue){
        android.content.SharedPreferences preferences = context.getSharedPreferences(item, MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = preferences.edit();
        editor.remove(itemValue);
        editor.apply();
    }

    public static void removeSettingAll(Context context, String item) {
        android.content.SharedPreferences shareData = context.getSharedPreferences(item,MODE_PRIVATE);
        android.content.SharedPreferences.Editor editor = shareData.edit();
        editor.clear();
        editor.apply();
    }
}
