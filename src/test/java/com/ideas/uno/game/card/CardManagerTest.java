package com.ideas.uno.game.card;

import org.junit.Before;
import org.junit.BeforeClass;
/*
 * Test pending
 */
public class CardManagerTest {

	private static CardDeck cardDeck;
	@BeforeClass
	public static void beforeClass(){
		cardDeck = CardDeck.getInstance();
	}
	@Before
	public void init() {
		new CardManagerImpl(cardDeck);
	}
	
}
