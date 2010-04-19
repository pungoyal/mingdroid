package com.thoughtworks.mingle;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class CardActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Toast.makeText(this, "hellllo", Toast.LENGTH_LONG).show();
	}
}
