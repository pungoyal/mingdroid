package com.thoughtworks.mingle.domain;

public class Author {
	private String name;
	private int id;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return name;
	}
}
