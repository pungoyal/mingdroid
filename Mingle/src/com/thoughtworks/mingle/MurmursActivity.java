package com.thoughtworks.mingle;

import android.app.ListActivity;
import android.os.Bundle;

import com.thoughtworks.mingle.adapters.MurmurAdapter;
import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.exceptions.ServerUnreachableException;
import com.thoughtworks.mingle.web.MingleClient;

public class MurmursActivity extends ListActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.murmurs);

		MingleClient client = new MingleClient(this);

		Murmurs murmurs = new Murmurs();
		try {
			murmurs = client.getMurmurs();
		} catch (ServerUnreachableException e) {
			e.printStackTrace();
		}

		setListAdapter(new MurmurAdapter(this, murmurs));

	}
}