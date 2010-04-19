package com.thoughtworks.mingle.listeners;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.thoughtworks.mingle.Constants;

public class PreferenceChangeListener implements OnPreferenceChangeListener {

	private final Context context;

	public PreferenceChangeListener(Context context) {
		this.context = context;
	}

	public boolean onPreferenceChange(Preference preference, Object newValue) {
		Editor editor = context.getSharedPreferences(Constants.APPLICATION_KEY, 0).edit();
		editor.putString(preference.getKey(), (String) newValue);
		return editor.commit();
	}
}
