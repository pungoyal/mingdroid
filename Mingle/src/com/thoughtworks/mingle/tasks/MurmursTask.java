package com.thoughtworks.mingle.tasks;

import android.app.Activity;

import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.exceptions.ServerUnreachableException;
import com.thoughtworks.mingle.web.MingleClient;

public class MurmursTask extends MingleTask<Void, Murmurs> {
	public MurmursTask(Activity activity, MingleClient client) {
		super(activity, client, "Loading Murmurs...");
	}

	@Override
	protected Murmurs doInBackground(Void... params) {
		try {
			return client.getMurmurs();
		} catch (ServerUnreachableException e) {
			return null;
		}
	}
}
