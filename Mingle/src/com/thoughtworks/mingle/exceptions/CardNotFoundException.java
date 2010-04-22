package com.thoughtworks.mingle.exceptions;

public class CardNotFoundException extends Exception {

	private static final long serialVersionUID = 6489363883135831622L;
	private final int cardNumber;

	public CardNotFoundException(int cardNumber) {
		this.cardNumber = cardNumber;
	}

}
