package com.thoughtworks.mingle.preferences;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceActivity;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.listeners.PreferenceChangeListener;

public class ConnectionPreferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		EditTextPreference serverPreference = (EditTextPreference) findPreference(Constants.SERVER_KEY);
		serverPreference.setOnPreferenceChangeListener(new PreferenceChangeListener(this));

		EditTextPreference username = (EditTextPreference) findPreference(Constants.USERNAME_KEY);
		username.setOnPreferenceChangeListener(new PreferenceChangeListener(this));

		EditTextPreference password = (EditTextPreference) findPreference(Constants.PASSWORD_KEY);
		password.setOnPreferenceChangeListener(new PreferenceChangeListener(this));
	}
}