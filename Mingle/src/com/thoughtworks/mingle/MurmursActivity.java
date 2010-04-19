package com.thoughtworks.mingle;

import android.app.ListActivity;
import android.os.Bundle;

import com.thoughtworks.mingle.adapters.MurmurAdapter;

public class MurmursActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.murmurs);

		setListAdapter(new MurmurAdapter(this));
	}
}