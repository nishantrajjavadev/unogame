package com.ideas.uno.game.rules;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardManagerImpl;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.PlayerManagerImpl;
import com.ideas.uno.game.player.direction.DirectionManagerFactoryImpl;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;

@RunWith(MockitoJUnitRunner.class)
public class ReverseCardUnoPlayTest {

	private PlayerManager playerManager;
	private CardManager cardManager;
	private Player player;
	private DirectionManagerFactoryImpl directionManagerFactoryImpl;
	private NextDirectionPlayer nextDirectionPlayer;
	private ReverseCardUnoPlay reverseCardUnoPlay;
	
	@Before
	public void setUp(){
		cardManager = Mockito.mock(CardManagerImpl.class);
		playerManager = Mockito.mock(PlayerManagerImpl.class);
		player = Mockito.mock(Player.class);
		directionManagerFactoryImpl = Mockito.mock(DirectionManagerFactoryImpl.class);
		nextDirectionPlayer = Mockito.mock(NextDirectionPlayer.class);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		reverseCardUnoPlay = new ReverseCardUnoPlay(playerManager, cardManager, directionManagerFactoryImpl);
	}
	
	@Test
	public void shouldReturnReverseNextPlayer() {
		Card card= new Card(null, CardType.REVERSE, 10);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Turn turn = reverseCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
}
