package com.thoughtworks.mingle;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.web.MingleClient;

public class CardActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);

		View cardNumber = findViewById(R.id.get_card_number);
		cardNumber.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText editText = (EditText) v;
				editText.selectAll();
				editText.setTextColor(R.color.card_description);
			}
		});

		cardNumber.setOnFocusChangeListener(new OnFocusChangeListener() {

			public void onFocusChange(View v, boolean hasFocus) {
				Toast.makeText(CardActivity.this, "focus changes", Toast.LENGTH_SHORT);
			}
		});

		View getCardButton = findViewById(R.id.get_card_button);
		getCardButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Editable cardNumber = ((EditText) findViewById(R.id.get_card_number)).getText();
				displayCardProperties(Integer.parseInt(cardNumber.toString()));
			}
		});
	}

	private void displayCardProperties(int cardNumber) {
		MingleClient mingleClient = new MingleClient(this);
		Card card = mingleClient.getCard(cardNumber);

		TextView number = (TextView) findViewById(R.id.card_number);
		number.setText(card.getType().getName() + " #" + cardNumber);
		TextView name = (TextView) findViewById(R.id.card_title);
		name.setText(card.getName());

		TextView description = (TextView) findViewById(R.id.card_description);
		description.setText(card.getShortDescription());

		TextView assignee = (TextView) findViewById(R.id.card_assignee);
		assignee.setText(card.getAssignee());

		TextView status = (TextView) findViewById(R.id.card_status);
		status.setText(card.getStatus());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.card_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.card_refresh:
			return true;
		case R.id.open_card:
			return true;
		}
		return false;
	}
}