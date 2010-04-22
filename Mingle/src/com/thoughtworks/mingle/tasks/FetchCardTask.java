package com.thoughtworks.mingle.tasks;

import android.widget.TextView;

import com.thoughtworks.mingle.CardActivity;
import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.web.MingleClient;

public class FetchCardTask extends MingleTask<Integer, Card> {

	public FetchCardTask(CardActivity cardActivity, MingleClient client) {
		super(cardActivity, client, "Loading...");
	}

	@Override
	protected Card doInBackground(Integer... params) {
		return client.getCard(params[0]);
	}

	@Override
	protected void onPostExecute(Card card) {
		TextView number = (TextView) activity.findViewById(R.id.card_number);
		number.setText(card.getType() + " #" + card.getNumber());
		TextView name = (TextView) activity.findViewById(R.id.card_title);
		name.setText(card.getName());

		TextView description = (TextView) activity.findViewById(R.id.card_description);
		description.setText(card.getShortDescription());

		TextView assignee = (TextView) activity.findViewById(R.id.card_assignee);
		assignee.setText(card.getAssignee());

		TextView status = (TextView) activity.findViewById(R.id.card_status);
		status.setText(card.getStatus());

		super.onPostExecute(card);
	}
}
