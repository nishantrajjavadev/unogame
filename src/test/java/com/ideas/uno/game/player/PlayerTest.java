package com.ideas.uno.game.player;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.Player;

public class PlayerTest {

	Player player;
	Player spyPlayer;
	
	@Mock
	CardManager cardManager;
	
	@Before
	public void setUp() {
		player = new Player("Nishant", 25);
		spyPlayer = Mockito.spy(player);
	}
	
	@Test
	public void shouldAddCardSuccessfully()  {
		Card card = new Card(CardColor.BLUE, CardType.DRAW_TWO, 1);
		Mockito.doNothing().when(spyPlayer).addCard(card);
		spyPlayer.addCard(card);
		Mockito.verify(spyPlayer, Mockito.times(1)).addCard(card);
	}
	
	@Test
	public void shouldRemoveCardSuccessfully() {
		Card card = new Card(CardColor.RED, CardType.DRAW_TWO, 1);
		Mockito.doNothing().when(spyPlayer).removeCards(card);
		spyPlayer.removeCards(card);
		Mockito.verify(spyPlayer, Mockito.times(1)).removeCards(card);
	}
	
	@Test(expected=UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenNewElementAddedToUnModifiyableList() {
		Card card = new Card(CardColor.BLUE, CardType.DRAW_TWO, 1);
		player.getCards().add(card);
	}
	
	@Test
	public void shouldAddScoreSuccessfully() {
		Mockito.doNothing().when(spyPlayer).addScore(100);
		spyPlayer.addScore(100);
		Mockito.verify(spyPlayer, Mockito.times(1)).addScore(100);
	}
	
	@Test
	public void shouldCallWild4CardPenalty() {
		Mockito.doNothing().when(spyPlayer).wild4CardPenalty(cardManager);
		spyPlayer.wild4CardPenalty(cardManager);
		Mockito.verify(spyPlayer, Mockito.times(1)).wild4CardPenalty(cardManager);
	}
	
	@Test
	public void shouldCallDraw2CardPenalty() {
		Mockito.doNothing().when(spyPlayer).draw2CardPenalty(cardManager);
		spyPlayer.draw2CardPenalty(cardManager);
		Mockito.verify(spyPlayer, Mockito.times(1)).draw2CardPenalty(cardManager);
	}
		
}
