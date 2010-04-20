package com.thoughtworks.mingle.domain;

import java.util.ArrayList;

public class Murmurs extends ArrayList<Murmur> {
	private static final long serialVersionUID = -1522983062499066238L;

	public String[] strings() {
		String[] result = new String[size()];

		for (int i = 0; i < size(); i++) {
			result[i] = get(i).getBody();
		}

		return result;

	}

}
