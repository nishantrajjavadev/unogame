package com.ideas.uno.game.card;

import com.ideas.uno.game.player.Player;

public class CardManagerImpl implements CardManager{

	private final CardDeck cardDeck;

	public CardManagerImpl(CardDeck cardDeck) {
		this.cardDeck = cardDeck;
	}
	
	@Override
	public CardDeck getCardDeck() {
		return cardDeck;
	}

	@Override
	public void resetCardDeck() {
		this.cardDeck.reset();
	}

	@Override
	public void draw(Player player, int noOfCards) {
		for (int i = 0; i < noOfCards; i++) {
			if (cardDeck.getDrawPile().isEmpty()) {
				player.addCard(cardDeck.getDrawPile().pop());
			}
		}
	}
}
