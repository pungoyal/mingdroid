package com.thoughtworks.mingle.domain;

import java.util.HashMap;

public class CardProperties extends HashMap<String, String> {

	public String getAssignee() {
		return get("assignee");
	}

	public void setAssignee(String assignee) {
		put("assignee", assignee);
	}
}