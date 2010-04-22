package com.thoughtworks.mingle.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.views.MurmurView;

public class MurmurAdapter extends BaseAdapter {

	private final Activity activity;
	private Murmurs murmurs;

	public MurmurAdapter(Activity activity, Murmurs murmurs) {
		this.activity = activity;
		this.murmurs = murmurs;
	}

	public int getCount() {
		return murmurs.size();
	}

	public Object getItem(int position) {
		return murmurs.get(position);
	}

	public long getItemId(int position) {
		return murmurs.get(position).getId();
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		return new MurmurView(activity, murmurs.get(position));
	}
}
