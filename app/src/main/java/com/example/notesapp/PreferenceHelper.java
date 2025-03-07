package com.example.notesapp;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PreferenceHelper {

    public static void savePref(Context context, ArrayList<String> array){
        SharedPreferences sharedPref = context.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
        Set<String> set = new HashSet<>(array); // Convert ArrayList to Set
        sharedPref.edit().putStringSet("notes_list", set).apply();
    }

    public static ArrayList<String> getPref(Context context){
        SharedPreferences prefs = context.getSharedPreferences("com.example.notesapp", Context.MODE_PRIVATE);
        return new ArrayList<String> (prefs.getStringSet("notes_list", new HashSet<>()));
    }


}
