package com.ideas.uno.game.card;

import com.ideas.uno.game.player.Player;

public class CardManager {

	private final CardDeck cardDeck;

	public CardManager(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}
	
	public CardDeck getCardDeck() {
		return cardDeck;
	}

	public void resetCardDeck() {
		this.cardDeck.reset();
	}

	public void draw(Player player, int noOfCards) {
		for (int i = 0; i < noOfCards; i++) {
			if (cardDeck.getDrawPile().size() > 0) {
				player.addCard(cardDeck.getDrawPile().pop());
			}
		}
	}

}
