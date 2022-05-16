package com.example.taserfantest;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;

import androidx.annotation.NonNull;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;


import java.util.Arrays;
import java.util.List;

public class ConfigFragment extends PreferenceFragmentCompat {


    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencesc,rootKey);

        SwitchPreference switchPreference = findPreference("switchtema");
        switchPreference.setOnPreferenceClickListener(new androidx.preference.Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(androidx.preference.Preference preference) {
                if (switchPreference.isChecked()){
                    NLmode.setMode(0);
                } else {
                    NLmode.setMode(1);
                }
                return true;
            }
        });
    }
}
