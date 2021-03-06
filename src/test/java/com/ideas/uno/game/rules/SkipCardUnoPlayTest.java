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
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.PlayerManagerImpl;
import com.ideas.uno.game.player.direction.DirectionManagerFactoryImpl;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class SkipCardUnoPlayTest {

	private PlayerManager playerManager;
	private CardManagerImpl cardManager;
	private SkipCardUnoPlay skipCardUnoPlay;
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
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		skipCardUnoPlay = new SkipCardUnoPlay(playerManager, cardManager, directionManagerFactoryImpl);
	}
	
	@Test
	public void shouldReturnNextSkipPlayer() {
		Card card= new Card(null, CardType.SKIP, 10);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Turn turn = skipCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(2)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
}
