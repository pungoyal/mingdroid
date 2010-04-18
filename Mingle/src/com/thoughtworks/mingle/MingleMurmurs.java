package com.thoughtworks.mingle;

import com.thoughtworks.mingle.preferences.ConnectionPreferences;
import com.thoughtworks.mingle.preferences.ProjectPreferences;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

public class MingleMurmurs extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		View viewMurmursButton = findViewById(R.id.view_button);
		viewMurmursButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(MingleMurmurs.this, MurmursActivity.class);
				startActivity(intent);
			}
		});

		View aboutButton = findViewById(R.id.about_button);
		aboutButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent(MingleMurmurs.this, About.class));
			}
		});

		View exitButton = findViewById(R.id.exit_button);
		exitButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.settings:
			startActivity(new Intent(this, ConnectionPreferences.class));
			return true;
		case R.id.change_project:
			startActivity(new Intent(this, ProjectPreferences.class));
			return true;
		}
		return false;
	}

}