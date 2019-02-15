package com.ideas.uno.game.card;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardFactory;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.rules.Draw2CardUnoPlay;
import com.ideas.uno.game.rules.NormalCardUnoPlay;
import com.ideas.uno.game.rules.ReverseCardUnoPlay;
import com.ideas.uno.game.rules.Rule;
import com.ideas.uno.game.rules.SkipCardUnoPlay;
import com.ideas.uno.game.rules.Wild4CardUnoPlay;
import com.ideas.uno.game.rules.WildCardUnoPlay;

public class CardFactoryTest {

	CardFactory cardFactory = new CardFactory();
	
	@Test
	public void shouldReturnReverseCardUnoPlayInstanceWhenCardTypeIsReverse() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		CardManager cardManager = Mockito.mock(CardManager.class);
		Card currentCard = new Card(CardColor.GREEN, CardType.REVERSE, 1);
		Rule rule = cardFactory.getPlayingCard(currentCard, playerManager, cardManager);
		Assert.assertTrue(rule instanceof ReverseCardUnoPlay);
	}
	
	@Test
	public void shouldReturnWildCardUnoPlayInstanceWhenCardTypeIsWild() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		CardManager cardManager = Mockito.mock(CardManager.class);
		Card currentCard = new Card(CardColor.GREEN, CardType.WILD, 1);
		Rule rule = cardFactory.getPlayingCard(currentCard, playerManager, cardManager);
		Assert.assertTrue(rule instanceof WildCardUnoPlay);
	}
	
	@Test
	public void shouldReturnWild4CardUnoPlayInstanceWhenCardTypeIsWildFour() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		CardManager cardManager = Mockito.mock(CardManager.class);
		Card currentCard = new Card(CardColor.GREEN, CardType.WILD_D4, 1);
		Rule rule = cardFactory.getPlayingCard(currentCard, playerManager, cardManager);
		Assert.assertTrue(rule instanceof Wild4CardUnoPlay);
	}
	
	@Test
	public void shouldReturnDraw2CardUnoPlayInstanceWhenCardTypeIsDrawTwo() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		CardManager cardManager = Mockito.mock(CardManager.class);
		Card currentCard = new Card(CardColor.GREEN, CardType.DRAW_TWO, 1);
		Rule rule = cardFactory.getPlayingCard(currentCard, playerManager, cardManager);
		Assert.assertTrue(rule instanceof Draw2CardUnoPlay);
	}
	
	@Test
	public void shouldReturnSkipCardUnoPlayInstanceWhenCardTypeIsSkip() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		CardManager cardManager = Mockito.mock(CardManager.class);
		Card currentCard = new Card(CardColor.GREEN, CardType.SKIP, 1);
		Rule rule = cardFactory.getPlayingCard(currentCard, playerManager, cardManager);
		Assert.assertTrue(rule instanceof SkipCardUnoPlay);
	}
	
	@Test
	public void shouldReturnNormalCardUnoPlayInstanceForCardTypeNumber() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		CardManager cardManager = Mockito.mock(CardManager.class);
		Card currentCard = new Card(CardColor.GREEN, CardType.NUMBER, 1);
		Rule rule = cardFactory.getPlayingCard(currentCard, playerManager, cardManager);
		Assert.assertTrue(rule instanceof NormalCardUnoPlay);
	}
}
