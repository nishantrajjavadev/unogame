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
public class NormalCardUnoPlayTest {

	private NormalCardUnoPlay normalCardUnoPlay;
	private PlayerManager playerManager;
	private CardManager cardManager;
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
		normalCardUnoPlay = new NormalCardUnoPlay(playerManager, cardManager, directionManagerFactoryImpl);
		
	}
	
	@Test
	public void shouldReturnNormalCARD() {
		Card card= new Card(null, CardType.NUMBER, 10);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Mockito.when(directionManagerFactoryImpl.getDirection(card.getCardType())).thenReturn(nextDirectionPlayer);
		Turn turn = normalCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(directionManagerFactoryImpl).getDirection(card.getCardType());
		Mockito.verify(nextDirectionPlayer).getNextPlayer(playerManager, player);
		Mockito.verify(player).turn(card, cardManager);
	}
	
	@Test
	public void shouldReturnNULL_NUMBER_CARD_NULL_NextPlayer() {
		Card card= new Card(null, CardType.NUMBER, 10);
		Mockito.when(player.turn(card, cardManager)).thenReturn(null);
		Turn turn = normalCardUnoPlay.play(card, player);
		Assert.assertNull(turn.getPlayingCard());
		Mockito.verify(player).turn(card, cardManager);
	}
	
	@Test
	public void shouldReturn_SAME_NUMBER_CARD_RETURN_ACTION_NextPlayer() {
		Card card= new Card(null, CardType.SKIP, 10);
		Mockito.when(player.turn(card, cardManager)).thenReturn(card);
		Turn turn = normalCardUnoPlay.play(card, player);
		Assert.assertEquals(turn.getPlayingCard(), card);
		Mockito.verify(player).turn(card, cardManager);
	}
	
	@Test
	public void shouldReturnTrueWildCardPlayer() {
		Assert.assertTrue(normalCardUnoPlay.isActionCard(new Card(null, CardType.WILD, 0)));
	}
	
	@Test
	public void shouldReturnTrueWild4CardPlayer() {
		Assert.assertTrue(normalCardUnoPlay.isActionCard(new Card(null, CardType.WILD_D4, 0)));
	}
	
	@Test
	public void shouldReturnTrueDrawCardPlayer() {
		Assert.assertTrue(normalCardUnoPlay.isActionCard(new Card(null, CardType.DRAW_TWO, 0)));
	}
	
	@Test
	public void shouldReturnTrueSkipCardPlayer() {
		Assert.assertTrue(normalCardUnoPlay.isActionCard(new Card(null, CardType.SKIP, 0)));
	}
	
	@Test
	public void shouldReturnTrueREVERSECardPlayer() {
		Assert.assertTrue(normalCardUnoPlay.isActionCard(new Card(null, CardType.REVERSE, 0)));
	}
}
