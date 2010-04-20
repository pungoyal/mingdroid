package com.thoughtworks.mingle.domain;

public class Card {
	private int number;
	private String name;
	private String description = "";
	private String type = "";
	private CardProperties properties = new CardProperties();

	public void setNumber(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProperties(CardProperties properties) {
		this.properties = properties;
	}

	public CardProperties getProperties() {
		return properties;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getShortDescription() {
		if (description.length() < 150)
			return description;
		return description.substring(0, 150) + "....";
	}

	public String getAssignee() {
		return properties.getAssignee();
	}

	public String getStatus() {
		return properties.getStatus();
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}