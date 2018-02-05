package com.fabricio.festafimdeano.util;
import android.content.Context;
import android.content.SharedPreferences;

public class securityPreferences {

    private final SharedPreferences mSharedPreferences;

    public securityPreferences(Context context){
        this.mSharedPreferences = context.getSharedPreferences("FimDeAno", Context.MODE_PRIVATE);
    }//end public SharedPreferences(Context context)

    public void storeString(String key, String value){
        this.mSharedPreferences.edit().putString(key, value).apply();
    }//end public void storeString()

    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key, "");
    }//end public String getStoredString(String key, String value)

}//end public class securityPreferences
