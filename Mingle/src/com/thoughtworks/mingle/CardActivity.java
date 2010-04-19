package com.thoughtworks.mingle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.web.MingleClient;

public class CardActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);

		View button = findViewById(R.id.get_card_button);
		button.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				MingleClient mingleClient = new MingleClient(CardActivity.this);
				Card card = mingleClient.getCard(203);

				TextView cardNumber = (TextView) findViewById(R.id.card_number);
				cardNumber.setText(card.getType().getName() + " #" + card.getNumber());
				TextView cardName = (TextView) findViewById(R.id.card_title);
				cardName.setText(card.getName());
			}
		});

	}
}