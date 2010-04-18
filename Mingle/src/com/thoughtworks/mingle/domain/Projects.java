package com.thoughtworks.mingle.domain;

import java.util.ArrayList;

public class Projects extends ArrayList<Project> {
	public CharSequence[] names() {
		CharSequence[] result = new CharSequence[size()];
		for (int i = 0; i < size(); i++) {
			result[i] = get(i).getName();
		}
		return result;
	}

	public CharSequence[] ids() {
		CharSequence[] result = new CharSequence[size()];
		for (int i = 0; i < size(); i++) {
			result[i] = get(i).getIdentifier();
		}
		return result;
	}
}
