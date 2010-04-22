package com.thoughtworks.mingle.tasks;

import android.preference.ListPreference;
import android.preference.PreferenceActivity;

import com.thoughtworks.mingle.Constants;
import com.thoughtworks.mingle.domain.Projects;
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
		return client.getProjects();
	}

	@Override
	protected void onPostExecute(Projects projects) {
		projectPreference.setEntries(projects.names());
		projectPreference.setEntryValues(projects.ids());
		super.onPostExecute(projects);
	}
}
