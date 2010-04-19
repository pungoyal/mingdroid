package com.thoughtworks.mingle.domain;

public class Card {
	private int number;
	private String name;
	private CardType type;

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
}
