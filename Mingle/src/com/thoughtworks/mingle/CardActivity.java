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
import android.widget.Toast;

import com.thoughtworks.mingle.tasks.FetchCardTask;
import com.thoughtworks.mingle.web.MingleClient;

public class CardActivity extends Activity {
	private int cardNumber;
	private MingleClient mingleClient;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.card);
		mingleClient = new MingleClient(this);
		
		Toast makeText = Toast.makeText(null, "", Toast.LENGTH_LONG);
		showDialog(1);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		LayoutInflater factory = LayoutInflater.from(this);
		final View getCardNumberView = factory.inflate(R.layout.card_dialog, null);
		return new AlertDialog.Builder(this).setTitle(R.string.get_card_title).setView(getCardNumberView)
				.setPositiveButton(R.string.get_card_ok_label, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						EditText enteredNumber = (EditText) getCardNumberView.findViewById(R.id.get_card_number);
						cardNumber = Integer.parseInt(enteredNumber.getText().toString());
						FetchCardTask task = new FetchCardTask(CardActivity.this, mingleClient);
						task.execute(cardNumber);
					}
				}).setNegativeButton(R.string.get_card_cancel_label, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int whichButton) {
						dialog.dismiss();
						CardActivity.this.finish();
					}
				}).create();
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
			FetchCardTask task = new FetchCardTask(CardActivity.this, mingleClient);
			task.execute(cardNumber);

			return true;
		case R.id.open_card:
			showDialog(1);
			return true;
		}
		return false;
	}
}