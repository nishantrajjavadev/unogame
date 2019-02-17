package com.ideas.uno.game.rules;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

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


public class WildCardUnoPlayTest {

	private PlayerManager playerManager;
	private CardManager cardManager;
	private WildCardUnoPlay wildCardUnoPlay;
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
		wildCardUnoPlay = new WildCardUnoPlay(playerManager, cardManager, directionManagerFactoryImpl);
		
	}
	
	@Test
	public void shouldReturnFirstPlayer() {
		Card card= new Card(null, CardType.NUMBER, 10);
		Mockito.when(player.getNextTrickyCard(cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Turn turn = wildCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player).getNextTrickyCard(cardManager);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(2)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
	@Test
	public void shouldReturnSameCard_WILD_4_NEXTDIRECTION_SHOULD_CALL_3() {
		Card card= new Card(null, CardType.WILD_D4, 10);
		Mockito.when(player.getNextTrickyCard(cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.doNothing().when(player).wild4CardPenalty(cardManager);
		Turn turn = wildCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player).getNextTrickyCard(cardManager);
		Mockito.verify(player).wild4CardPenalty(cardManager);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(3)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
	@Test
	public void shouldReturnSameCard_DRAW_2_NEXTDIRECTION_SHOULD_CALL_3() {
		Card card= new Card(null, CardType.DRAW_TWO, 10);
		Mockito.when(player.getNextTrickyCard(cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.doNothing().when(player).draw2CardPenalty(cardManager);
		Turn turn = wildCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player).getNextTrickyCard(cardManager);
		Mockito.verify(player).draw2CardPenalty(cardManager);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(3)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
	@Test
	public void shouldReturnSameCard_WILD_NEXTDIRECTION_SHOULD_CALL_2() {
		Card card= new Card(null, CardType.WILD, 10);
		Mockito.when(player.getNextTrickyCard(cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Mockito.when(nextDirectionPlayer.getNextPlayer(playerManager, player)).thenReturn(player);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.doNothing().when(player).draw2CardPenalty(cardManager);
		Turn turn = wildCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player, Mockito.times(2)).getNextTrickyCard(cardManager);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer, Mockito.times(2)).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
}