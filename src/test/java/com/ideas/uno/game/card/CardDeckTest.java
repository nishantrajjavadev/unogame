package com.ideas.uno.game.card;

import java.util.Stack;

import org.junit.Assert;
import org.junit.Test;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardType;

public class CardDeckTest {
	
	CardDeck cardDeck = new CardDeck();
	
	@Test
	public void shouldReturnDrawPlile() {
		Stack<Card> expectedDrawPile = cardDeck.getDrawPile();
		Assert.assertTrue(expectedDrawPile.size() > 0);
	}
	
	@Test
	public void shouldReturnCardSuccessfullyIfDrawPileIsNotEmpty() {
		Card firstCard = cardDeck.getFirstCard();
		Assert.assertTrue(firstCard !=null);
	}
	
	@Test
	public void shouldReturnDiscardPileSuccessfully() {
		Stack<Card> discardPile = cardDeck.getDiscardPile();
		Assert.assertTrue(discardPile.size() == 0);
	}
	
	@Test
	public void shouldAddCardToDiscardPl() {
		Card card = new Card(CardColor.RED, CardType.NUMBER, 1);
		cardDeck.addCardToDiscardPile(card);
		Assert.assertTrue(cardDeck.getDiscardPile().size() == 1);
	}
}
