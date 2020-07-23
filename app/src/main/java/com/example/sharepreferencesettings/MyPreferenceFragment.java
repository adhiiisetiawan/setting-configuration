package com.example.sharepreferencesettings;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceFragmentCompat;

public class MyPreferenceFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {
    private String DEFAULT_VALUE = "Not Found";
    private String NAME;
    private String EMAIL;
    private String AGE;
    private String PHONE_NUMBER;
    private String LOVE;

    private EditTextPreference namePreference;
    private EditTextPreference emailPreference;
    private EditTextPreference agePreference;
    private EditTextPreference phonePreference;
    private CheckBoxPreference isLovePreference;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        init();
        setSummaries();
    }

    private void init(){
        NAME = getResources().getString(R.string.key_name);
        EMAIL = getResources().getString(R.string.key_email);
        AGE = getResources().getString(R.string.key_age);
        PHONE_NUMBER = getResources().getString(R.string.key_phone);
        LOVE = getResources().getString(R.string.key_love);

        namePreference = findPreference(NAME);
        emailPreference = findPreference(EMAIL);
        agePreference = findPreference(AGE);
        phonePreference = findPreference(PHONE_NUMBER);
        isLovePreference = findPreference(LOVE);
    }

    private void setSummaries(){
        SharedPreferences sharedPreferences = getPreferenceManager().getSharedPreferences();
        namePreference.setSummary(sharedPreferences.getString(NAME, DEFAULT_VALUE));
        emailPreference.setSummary(sharedPreferences.getString(EMAIL, DEFAULT_VALUE));
        agePreference.setSummary(sharedPreferences.getString(AGE, DEFAULT_VALUE));
        phonePreference.setSummary(sharedPreferences.getString(PHONE_NUMBER, DEFAULT_VALUE));
        isLovePreference.setChecked(sharedPreferences.getBoolean(LOVE, false));
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String s) {
        if (s.equals(NAME)){
            namePreference.setSummary(sharedPreferences.getString(NAME, DEFAULT_VALUE));
        }

        if (s.equals(EMAIL)){
            emailPreference.setSummary(sharedPreferences.getString(EMAIL, DEFAULT_VALUE));
        }

        if (s.equals(AGE)){
            agePreference.setSummary(sharedPreferences.getString(AGE, DEFAULT_VALUE));
        }

        if (s.equals(PHONE_NUMBER)){
            phonePreference.setSummary(sharedPreferences.getString(PHONE_NUMBER, DEFAULT_VALUE));
        }

        if (s.equals(LOVE)){
            isLovePreference.setChecked(sharedPreferences.getBoolean(LOVE, false));
        }
    }
}
