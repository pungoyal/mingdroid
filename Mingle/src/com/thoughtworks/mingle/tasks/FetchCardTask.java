package com.thoughtworks.mingle.tasks;

import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.mingle.CardActivity;
import com.thoughtworks.mingle.R;
import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.exceptions.CardNotFoundException;
import com.thoughtworks.mingle.web.MingleClient;

public class FetchCardTask extends MingleTask<Integer, Card> {

	public FetchCardTask(CardActivity cardActivity, MingleClient client) {
		super(cardActivity, client, "Loading card...");
	}

	@Override
	protected Card doInBackground(Integer... cardNumbers) {
		try {
			return client.getCard(cardNumbers[0]);
		} catch (CardNotFoundException e) {
			return null;
		}
	}

	@Override
	protected void onPostExecute(Card card) {
		if (card == null) {
			Toast.makeText(activity, "Requested card could not be found in mingle.", Toast.LENGTH_SHORT).show();
			super.onPostExecute(card);
			return;
		}

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
