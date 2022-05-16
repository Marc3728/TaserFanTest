package com.example.taserfantest;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class GestionPreferencias {
    private static SharedPreferences preferences;

    public static String getUnidad(Context context){
        inicializa(context);
        return preferences.getString("unidades","default");
    }

    private static void inicializa(Context context) {
        if (preferences==null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static String getIP(Context context){
        inicializa(context);
        return preferences.getString("edittextip","default");
    }

    public static String getPORT(Context context){
        inicializa(context);
        return preferences.getString("edittextport","default");
    }
}
