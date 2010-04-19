package com.thoughtworks.mingle.preferences;

import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceChangeListener;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.listeners.PreferenceChangeListener;

public class ConnectionPreferences extends PreferenceActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.settings);

		PreferenceChangeListener preferenceChangeListener = new PreferenceChangeListener(this);
		findPreference(Constants.HTTPS_KEY).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {
			public boolean onPreferenceChange(Preference preference, Object newValue) {
				Editor editor = getSharedPreferences(Constants.APPLICATION_KEY, 0).edit();
				Boolean https = (Boolean) newValue;

				String protocol = https ? "https://" : "http://";
				editor.putString(preference.getKey(), protocol);
				return editor.commit();
			}
		});
		findPreference(Constants.SERVER_KEY).setOnPreferenceChangeListener(preferenceChangeListener);
		findPreference(Constants.USERNAME_KEY).setOnPreferenceChangeListener(preferenceChangeListener);
		findPreference(Constants.PASSWORD_KEY).setOnPreferenceChangeListener(preferenceChangeListener);
	}
}