package com.ideas.uno.game.card;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CardDeckTest {

	private CardDeck cardDeck;

	@Before
	public void setUp() {
		cardDeck = CardDeck.getInstance();
	}

	@Test
	public void shouldReturnDrawPlile() {
		Stack<Card> expectedDrawPile = cardDeck.getDrawPile();
		Assert.assertTrue(!expectedDrawPile.isEmpty());
	}

	@Test
	public void shouldReturnCardSuccessfullyIfDrawPileIsNotEmpty() {
		Card firstCard = cardDeck.getFirstCard();
		cardDeck.addCardToDiscardPile(firstCard);
		Assert.assertNotNull(firstCard);
		Assert.assertNotNull(!cardDeck.getDiscardPile().isEmpty());
	}

	@Test
	public void shouldResetCard() {
		cardDeck.reset();
		Assert.assertTrue(cardDeck.getDrawPile().size() == 108);
		Assert.assertTrue(cardDeck.getDiscardPile().isEmpty());
	}

}
