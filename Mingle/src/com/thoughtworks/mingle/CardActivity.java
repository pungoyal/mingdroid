package com.thoughtworks.mingle;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.thoughtworks.mingle.domain.Card;
import com.thoughtworks.mingle.web.MingleClient;

public class CardActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);

		showDialog(1);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		LayoutInflater factory = LayoutInflater.from(this);
		final View getCardNumberView = factory.inflate(R.layout.card_dialog, null);
		return new AlertDialog.Builder(this).setTitle(R.string.get_card_title).setView(getCardNumberView)
				.setPositiveButton(R.string.get_card_ok_label, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						EditText cardNumber = (EditText) getCardNumberView.findViewById(R.id.get_card_number);
						int number = Integer.parseInt(cardNumber.getText().toString());
						displayCardProperties(number);
					}
				}).setNegativeButton(R.string.get_card_cancel_label, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
					}
				}).create();
	}

	private void displayCardProperties(int cardNumber) {
		MingleClient mingleClient = new MingleClient(this);
		Card card = mingleClient.getCard(cardNumber);

		TextView number = (TextView) findViewById(R.id.card_number);
		number.setText(card.getType() + " #" + cardNumber);
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