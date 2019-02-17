package com.ideas.uno.game.executor;


import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.Player;

public class UnoGameExecutorTest {
	
	private UnoGameExecutor unoGameExecutor;
	private Player player;
	Map<String, Integer> playersOfTheGame = new HashMap<String, Integer>();
	

	@Before
	public void setUp() throws Exception{
		loadPlayer();
		player = Mockito.mock(Player.class);
		unoGameExecutor = new UnoGameExecutor(playersOfTheGame, 500);
		unoGameExecutor.loadGame();

	}
	
	@Test
	public void shouldLoadGameNormally(){
		Card card = new Card(CardColor.BLUE, CardType.NUMBER, 8);
		Turn turnResult = unoGameExecutor.getFirstDrawPileCard(player, card);
		Assert.assertNotNull(turnResult);
	}
	
	@Test
	public void shouldResetGame(){
		unoGameExecutor.resetGameState();
	}
	
	
	public void loadPlayer(){
		playersOfTheGame.put("PLAYER_1", 7);
		playersOfTheGame.put("PLAYER_2", 8);
		playersOfTheGame.put("PLAYER_3", 9);
		playersOfTheGame.put("PLAYER_4", 9);
	}
	
}
