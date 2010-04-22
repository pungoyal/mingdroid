package com.thoughtworks.mingle.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.thoughtworks.mingle.web.MingleClient;

public abstract class MingleTask<InputType, ReturnType> extends AsyncTask<InputType, Integer, ReturnType> {

	protected final Activity activity;
	protected final MingleClient client;
	protected ProgressDialog progress;
	private final String waitMessage;

	public MingleTask(Activity activity, MingleClient client, String waitMessage) {
		this.activity = activity;
		this.client = client;
		this.waitMessage = waitMessage;
	}

	@Override
	protected void onPreExecute() {
		progress = ProgressDialog.show(activity, "Please wait...", waitMessage, true);
	}

	@Override
	protected void onPostExecute(ReturnType result) {
		progress.dismiss();
	}
}