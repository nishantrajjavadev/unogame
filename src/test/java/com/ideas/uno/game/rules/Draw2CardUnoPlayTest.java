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


public class Draw2CardUnoPlayTest {

	private PlayerManagerImpl playerManager;
	private CardManagerImpl cardManager;
	private Player player;
	private DirectionManagerFactoryImpl directionManagerFactoryImpl;
	private NextDirectionPlayer nextDirectionPlayer;
	private Draw2CardUnoPlay draw2CardUnoPlay;

	@Before
	public void setUp(){
		cardManager = Mockito.mock(CardManagerImpl.class);
		playerManager = Mockito.mock(PlayerManagerImpl.class);
		player = Mockito.mock(Player.class);
		directionManagerFactoryImpl = Mockito.mock(DirectionManagerFactoryImpl.class);
		nextDirectionPlayer = Mockito.mock(NextDirectionPlayer.class);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		draw2CardUnoPlay = new Draw2CardUnoPlay(playerManager, cardManager ,directionManagerFactoryImpl);
	}
	
	
	@Test
	public void shouldHavePenalty2drawcardPlayer() {
		Card card= new Card(null, CardType.DRAW_TWO, 10);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.doNothing().when(player).draw2CardPenalty(cardManager);
		Turn turn = draw2CardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player).draw2CardPenalty(cardManager);
		Mockito.verify(directionManagerFactoryImpl, Mockito.times(2)).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(2)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
}
