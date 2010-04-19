package com.thoughtworks.mingle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.web.MingleClient;

public class CardActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);

		MingleClient mingleClient = new MingleClient(CardActivity.this);
		Card card = mingleClient.getCard(211);

		TextView number = (TextView) findViewById(R.id.card_number);
		number.setText(card.getType().getName() + " #" + card.getNumber());
		TextView name = (TextView) findViewById(R.id.card_title);
		name.setText(card.getName());

		TextView description = (TextView) findViewById(R.id.card_description);
		description.setText(card.getShortDescription());

		TextView assignee = (TextView) findViewById(R.id.card_assignee);
		assignee.setText(card.getAssignee());

	}
}