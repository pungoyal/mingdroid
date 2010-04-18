package com.thoughtworks.mingle.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.thoughtworks.mingle.domain.Murmurs;
import com.thoughtworks.mingle.views.MurmurView;
import com.thoughtworks.mingle.web.MingleClient;

public class MurmurAdapter extends BaseAdapter {

	private final Context context;
	private Murmurs murmurs = new Murmurs();

	public MurmurAdapter(Context context) {
		this.context = context;
		murmurs = getMurmurs();
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
		MurmurView murmurView = new MurmurView(context, murmurs.get(position));
		return murmurView;
	}

	private Murmurs getMurmurs() {
		MingleClient client = new MingleClient(context);
		Murmurs murmurs = client.getMurmurs();
		return murmurs;
	}

}
