package com.thoughtworks.mingle.views;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.thoughtworks.mingle.domain.Murmur;

public class MurmurView extends LinearLayout {

	public MurmurView(Context context, Murmur murmur) {
		super(context);

		setOrientation(VERTICAL);

		TextView author = new TextView(context);
		author.setText(murmur.getAuthor().getName());
		author.setTextColor(Color.argb(255, 102, 0, 0));
		addView(author, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		TextView body = new TextView(context);
		body.setText(murmur.getBody());
		body.setTextColor(Color.BLACK);
		body.setTextSize(18);
		addView(body, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));

		TextView date = new TextView(context);
		date.setText(murmur.getCreatedAtString());
		date.setTextColor(Color.argb(255, 136, 136, 136));
		date.setTextSize(10);
		addView(date, new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
	}
}
