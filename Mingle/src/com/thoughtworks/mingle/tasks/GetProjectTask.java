package com.thoughtworks.mingle.tasks;

import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.widget.Toast;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.domain.Projects;
import com.thoughtworks.mingle.exceptions.ServerUnreachableException;
import com.thoughtworks.mingle.listeners.ProjectChangeListener;
import com.thoughtworks.mingle.preferences.ProjectPreferences;
import com.thoughtworks.mingle.web.MingleClient;

public class GetProjectTask extends MingleTask<Void, Projects> {

	private ListPreference projectPreference;

	public GetProjectTask(ProjectPreferences projectPreferences, MingleClient mingleClient, String waitMessage) {
		super(projectPreferences, mingleClient, waitMessage);
		projectPreference = (ListPreference) ((PreferenceActivity) activity).findPreference(Constants.PROJECT_KEY);
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		projectPreference.setOnPreferenceChangeListener(new ProjectChangeListener(activity));
	}

	@Override
	protected Projects doInBackground(Void... params) {
		try {
			return client.getProjects();
		} catch (ServerUnreachableException e) {
			return null;
		}
	}

	@Override
	protected void onPostExecute(Projects projects) {
		if (projects == null) {
			Toast.makeText(activity, "Server cannot be reached. Try again.", Toast.LENGTH_SHORT).show();
			super.onPostExecute(projects);
			return;
		}

		projectPreference.setEntries(projects.names());
		projectPreference.setEntryValues(projects.ids());
		super.onPostExecute(projects);
	}
}
