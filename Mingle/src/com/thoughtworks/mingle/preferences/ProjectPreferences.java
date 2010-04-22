package com.thoughtworks.mingle.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.tasks.GetProjectTask;
import com.thoughtworks.mingle.web.MingleClient;

public class ProjectPreferences extends PreferenceActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MingleClient mingleClient = new MingleClient(ProjectPreferences.this);

		addPreferencesFromResource(R.xml.project_settings);

		String displayPreferences = displayPreferences();

		GetProjectTask task = new GetProjectTask(this, mingleClient, displayPreferences);
		task.execute();
	}

	private String displayPreferences() {
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

		return connectionProperties;
	}
}