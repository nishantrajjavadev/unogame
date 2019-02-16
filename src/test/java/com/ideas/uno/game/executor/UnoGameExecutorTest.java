package com.ideas.uno.game.executor;


import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

public class UnoGameExecutorTest {
	
	Map<String, Integer> playersOfTheGame = new HashMap<String, Integer>();
	private UnoGameExecutor unoGameExecutor;
	

	@Before
	public void setUp() throws Exception{
		loadPlayers();
		unoGameExecutor = new UnoGameExecutor(loadPlayers(), 200);
		unoGameExecutor.loadGame();
	}
	
	
	private Map<String, Integer> loadPlayers() {
		playersOfTheGame.put("PLAYER_1", 7);
		playersOfTheGame.put("PLAYER_2", 8);
		playersOfTheGame.put("PLAYER_3", 9);
		playersOfTheGame.put("PLAYER_4", 10);
		return playersOfTheGame;
	}
}
