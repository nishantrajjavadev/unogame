package com.ideas.uno.game.card;

import java.util.Stack;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CardDeckTest {

	private static CardDeck cardDeck;

	@BeforeClass
	public static void beforeClass() {
		cardDeck = CardDeck.getInstance();
	}


	@Test
	public void shouldReturnDrawPlile() {
		Stack<Card> expectedDrawPile = cardDeck.getDrawPile();
		Assert.assertTrue(expectedDrawPile.size() > 0);
	}

	@Test
	public void shouldReturnCardSuccessfullyIfDrawPileIsNotEmpty() {
		Card firstCard = cardDeck.getFirstCard();
		Assert.assertTrue(firstCard != null);
	}
}
