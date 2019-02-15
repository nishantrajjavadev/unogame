package com.ideas.uno.game.card;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.player.Player;

public class CardManagerTest {

	CardManager cardManager = new CardManager();
	CardDeck cardDeck;
	Player player;
	
	@Before
	public void init() {
		player = new Player("Ranjani", 20);
		cardDeck = new CardDeck();
	}
	
	@Test
	public void shoudNotAddCardToPlayIfNoOfCardsAreLessThanZero() throws Exception {
		cardManager.draw(player, 0);
		Assert.assertTrue(player.getCards().size() == 0);
	}
	
	@Test
	public void shouldAddNoOfCardsToPlayer() throws Exception {
		int noOfCards = 2;
		cardManager.draw(player, noOfCards);
		Assert.assertTrue(player.getCards().size() == noOfCards);
	}
}
