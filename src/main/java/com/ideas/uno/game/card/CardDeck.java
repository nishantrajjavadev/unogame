package com.ideas.uno.game.card;

import java.util.Collections;
import java.util.Stack;

/**
 * Consist of card list (Draw pile + discard card list)
 */
public class CardDeck {

	private Stack<Card> drawPile;

	private Stack<Card> discardPile;

	private DrawPileObserver observer;

	private volatile static CardDeck cardDeck;

	private CardDeck() {

	}

	private void init() {
		drawPile = new Stack<Card>();
		discardPile = new Stack<Card>();
		observer = new UpdateDrawPile(drawPile, discardPile);
		for (int i = 0; i <= 9; i++) {
			for (int j = 0; j < (i == 0 ? 1 : 2); j++) {
				fillNumberCards(i);
			}
		}

		for (int j = 0; j < 2; j++) {
			fillActionCards();
		}
		for (int i = 0; i < 4; i++) {
			drawPile.push(new Card(CardColor.NONE, CardType.WILD, 50));
		}
		for (int i = 0; i < 4; i++) {
			drawPile.push(new Card(CardColor.NONE, CardType.WILD_D4, 50));
		}
		Collections.shuffle(drawPile);
		if (drawPile.size() <= 0 || drawPile.size() > 108) {
			throw new IllegalStateException("Uno game cards are not loaded");
		}
	}

	/**
	 * 
	 */
	private void fillActionCards() {
		drawPile.push(new Card(CardColor.RED, CardType.SKIP, 20));
		drawPile.push(new Card(CardColor.YELLOW, CardType.SKIP, 20));
		drawPile.push(new Card(CardColor.GREEN, CardType.SKIP, 20));
		drawPile.push(new Card(CardColor.BLUE, CardType.SKIP, 20));
		drawPile.push(new Card(CardColor.RED, CardType.REVERSE, 20));
		drawPile.push(new Card(CardColor.YELLOW, CardType.REVERSE, 20));
		drawPile.push(new Card(CardColor.GREEN, CardType.REVERSE, 20));
		drawPile.push(new Card(CardColor.BLUE, CardType.REVERSE, 20));
		drawPile.push(new Card(CardColor.RED, CardType.DRAW_TWO, 20));
		drawPile.push(new Card(CardColor.YELLOW, CardType.DRAW_TWO, 20));
		drawPile.push(new Card(CardColor.GREEN, CardType.DRAW_TWO, 20));
		drawPile.push(new Card(CardColor.BLUE, CardType.DRAW_TWO, 20));
	}

	/**
	 * @param i
	 */
	private void fillNumberCards(int i) {
		drawPile.push(new Card(CardColor.RED, CardType.NUMBER, i));
		drawPile.push(new Card(CardColor.YELLOW, CardType.NUMBER, i));
		drawPile.push(new Card(CardColor.BLUE, CardType.NUMBER, i));
		drawPile.push(new Card(CardColor.GREEN, CardType.NUMBER, i));
	}

	public Stack<Card> getDrawPile() {
		if (drawPile.size() == 0) {
			observer.updateDrawPile();
		}
		return drawPile;
	}

	public Stack<Card> getDiscardPile() {
		return discardPile;
	}

	public void addCardToDiscardPile(Card card) {
		this.discardPile.push(card);
	}

	public Card getFirstCard() {
		Card card = drawPile.pop();
		if (CardType.WILD.equals(card.getCardType())
				|| CardType.WILD_D4.equals(card.getCardType())) {
			drawPile.push(card);
			Collections.shuffle(drawPile);
			getFirstCard();
		}
		return card;
	}

	public void reset() {
		drawPile.clear();
		discardPile.clear();
		init();
	}

	public static CardDeck getInstance() {
		if (cardDeck == null) {
			synchronized (CardDeck.class) {
				if (cardDeck == null) {
					cardDeck = new CardDeck();
					cardDeck.init();
				}
			}
		}
		return cardDeck;
	}
}
