package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.PlayerManager;

@RunWith(MockitoJUnitRunner.class)
public class NormalCardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private NormalCardUnoPlay normalCardUnoPlay;
	CardManager cardManager;
	
	@Before
	public void setUp(){
		loadPlayers();
		playerManager = Mockito.mock(PlayerManager.class);
		cardManager = Mockito.mock(CardManager.class);
		normalCardUnoPlay = new NormalCardUnoPlay(playerManager, cardManager);
		
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
	
	private Map<String, Integer> loadPlayers() {
		playersOfTheGame = new HashMap<String, Integer>();
		playersOfTheGame.put("Nishant", 7);
		playersOfTheGame.put("Arpan", 8);
		playersOfTheGame.put("Shivranjani", 9);
		playersOfTheGame.put("Sahil", 10);
		return playersOfTheGame;
	}
}
