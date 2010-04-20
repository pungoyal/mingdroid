package com.thoughtworks.mingle.domain;

import java.util.HashMap;

import com.thoughtworks.mingle.Constants;

public class CardProperties extends HashMap<String, String> {

	private static final long serialVersionUID = -22967138106377994L;

	public CardProperties() {
		put(Constants.ASSIGNEE, "NOT SET");
		put(Constants.STATUS, "NOT SET");
	}

	public String getAssignee() {
		return get(Constants.ASSIGNEE);
	}

	public void setAssignee(String assignee) {
		put(Constants.ASSIGNEE, assignee);
	}

	public String getStatus() {
		return get(Constants.STATUS);
	}

	public void setStatus(String status) {
		put(Constants.STATUS, status);
	}
}