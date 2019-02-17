package com.ideas.uno.game.executor;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class GameManageTest {

	private GameManager gameManager;
	private UnoGameExecutor unoGameExecutor;
	
	@Before
	public void setUp(){
		unoGameExecutor = Mockito.mock(UnoGameExecutor.class);
		gameManager = new GameManager(unoGameExecutor);
	}
	
	@Test
	public void shouldCallLoadWithMock(){
		gameManager.loadGame();
		Mockito.verify(unoGameExecutor).loadGame();
	}
	
	@Test
	public void shouldCallPlayWithMock(){
		gameManager.play();
		Mockito.verify(unoGameExecutor).play();
	}
}
