package com.thoughtworks.mingle.domain;

public class Project {
	private String identifier;
	private String name;

	public Project(String id, String name) {
		this.setIdentifier(id);
		this.setName(name);
	}

	public Project() {
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
