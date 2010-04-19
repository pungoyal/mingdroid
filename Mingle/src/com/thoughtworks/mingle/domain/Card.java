package com.thoughtworks.mingle.domain;

public class Card {
	private int number;
	private String name;
	private String description;
	private CardType type;
	private CardProperties properties;

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

	public void setType(CardType type) {
		this.type = type;
	}

	public CardType getType() {
		return type;
	}

	public void setProperties(CardProperties properties) {
		this.properties = properties;
	}

	public CardProperties getProperties() {
		return properties;
	}

	public String getAssignee() {
		return properties.getAssignee();
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public String getShortDescription() {
		if (description.length() < 100)
			return description;
		return description.substring(0, 100) + "....";
	}

}
