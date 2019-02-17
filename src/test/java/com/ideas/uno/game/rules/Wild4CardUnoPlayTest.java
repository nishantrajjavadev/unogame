package com.ideas.uno.game.rules;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManagerImpl;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManagerImpl;
import com.ideas.uno.game.player.direction.DirectionManagerFactoryImpl;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class Wild4CardUnoPlayTest {

	private PlayerManagerImpl playerManager;
	private CardManagerImpl cardManager;
	private Wild4CardUnoPlay wild4CardUnoPlay;
	private Player player;
	private DirectionManagerFactoryImpl directionManagerFactoryImpl;
	private NextDirectionPlayer nextDirectionPlayer;

	@Before
	public void setUp(){
		cardManager = Mockito.mock(CardManagerImpl.class);
		playerManager = Mockito.mock(PlayerManagerImpl.class);
		player = Mockito.mock(Player.class);
		directionManagerFactoryImpl = Mockito.mock(DirectionManagerFactoryImpl.class);
		nextDirectionPlayer = Mockito.mock(NextDirectionPlayer.class);	
		wild4CardUnoPlay = new Wild4CardUnoPlay(playerManager, cardManager, directionManagerFactoryImpl);	
	}
	
	@Test
	public void shouldReturnPenalty4drawPlayer() {
		Card card= new Card(null, CardType.NUMBER, 10);
		Mockito.when(player.getNextTrickyCard(cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.doNothing().when(player).wild4CardPenalty(cardManager);;
		Turn turn = wild4CardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player).getNextTrickyCard(cardManager);
		Mockito.verify(player).wild4CardPenalty(cardManager);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(3)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
}
