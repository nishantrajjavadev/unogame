package com.ideas.uno.game.card;


public class Card {

	private CardColor cardColors;

	private CardType cardType;

	private int number;

	public Card(CardColor cardColors, CardType cardType, int number) {
		this.cardColors = cardColors;
		this.cardType = cardType;
		this.number = number;
	}

	public CardColor getCardColors() {
		return cardColors;
	}

	public CardType getCardType() {
		return cardType;
	}

	public int getNumber() {
		return number;
	}

	@Override
	public String toString() {
		return "Card [cardColors=" + cardColors + ", cardType=" + cardType
				+ ", number=" + number + "]";
	}

}
