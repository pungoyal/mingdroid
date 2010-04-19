package com.thoughtworks.mingle.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.domain.Projects;
import com.thoughtworks.mingle.listeners.PreferenceChangeListener;
import com.thoughtworks.mingle.web.MingleClient;

public class ProjectPreferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.project_settings);

	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);

		ListPreference projectPreference = (ListPreference) findPreference(Constants.PROJECT_KEY);
		projectPreference.setOnPreferenceChangeListener(new PreferenceChangeListener(this));

		MingleClient mingleClient = new MingleClient(ProjectPreferences.this);
		Projects projects = mingleClient.getProjects();
		projectPreference.setEntries(projects.names());
		projectPreference.setEntryValues(projects.ids());

		SharedPreferences preferences = getSharedPreferences(Constants.APPLICATION_KEY, 0);
		String server = preferences.getString(Constants.SERVER_KEY, "NOT DEFINED!");
		String port = preferences.getString(Constants.PORT_KEY, "<default>");
		String username = preferences.getString(Constants.USERNAME_KEY, "NOT DEFINED!");
		String password = preferences.getString(Constants.PASSWORD_KEY, "NOT DEFINED!");
		String project = preferences.getString(Constants.PROJECT_KEY, "NOT SELECTED!");

		String connectionProperties = "Current connection properties:";
		connectionProperties += "\nServer - " + server;
		connectionProperties += "\nPort - " + port;
		connectionProperties += "\nUsername - " + username;
		connectionProperties += "\nPassword - " + password;
		connectionProperties += "\nProject - " + project;

		Toast toast = Toast.makeText(this, connectionProperties, Toast.LENGTH_SHORT);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();

	}
}