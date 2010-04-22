package com.thoughtworks.mingle.listeners;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.web.MingleClient;

public class ProjectChangeListener implements OnPreferenceChangeListener {

	private final Context context;
	private final MingleClient mingleClient;

	public ProjectChangeListener(Context context, MingleClient mingleClient) {
		this.context = context;
		this.mingleClient = mingleClient;
	}

	public boolean onPreferenceChange(Preference preference, Object newValue) {
		Editor editor = context.getSharedPreferences(Constants.APPLICATION_KEY, 0).edit();
		String projectId = (String) newValue;
		editor.putString(preference.getKey(), projectId);
		editor.commit();
		return true;
	}
}