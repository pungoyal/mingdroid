package com.thoughtworks.mingle.listeners;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.thoughtworks.mingle.Constants;

public class ProjectChangeListener implements OnPreferenceChangeListener {

	private final Context context;

	public ProjectChangeListener(Context context) {
		this.context = context;
	}

	public boolean onPreferenceChange(Preference preference, Object newValue) {
		Editor editor = context.getSharedPreferences(Constants.APPLICATION_KEY, 0).edit();
		String projectId = (String) newValue;
		editor.putString(preference.getKey(), projectId);
		editor.commit();
		return true;
	}
}