package com.thoughtworks.mingle.tasks;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.TextView;

import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.web.MingleClient;

public class CardAsyncTask extends AsyncTask<Integer, Integer, Card> {

	private final Activity cardActivity;
	private final MingleClient client;
	private ProgressDialog progress;

	public CardAsyncTask(Activity cardActivity, MingleClient client) {
		this.cardActivity = cardActivity;
		this.client = client;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = ProgressDialog.show(cardActivity, "Please wait...", "Loading...", true);
	}

	@Override
	protected Card doInBackground(Integer... params) {
		return client.getCard(params[0]);
	}

	@Override
	protected void onPostExecute(Card card) {
		TextView number = (TextView) cardActivity.findViewById(R.id.card_number);
		number.setText(card.getType() + " #" + card.getNumber());
		TextView name = (TextView) cardActivity.findViewById(R.id.card_title);
		name.setText(card.getName());

		TextView description = (TextView) cardActivity.findViewById(R.id.card_description);
		description.setText(card.getShortDescription());

		TextView assignee = (TextView) cardActivity.findViewById(R.id.card_assignee);
		assignee.setText(card.getAssignee());

		TextView status = (TextView) cardActivity.findViewById(R.id.card_status);
		status.setText(card.getStatus());

		progress.dismiss();
	}
}
