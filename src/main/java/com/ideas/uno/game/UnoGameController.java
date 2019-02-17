package com.ideas.uno.game;

import java.util.HashMap;
import java.util.Map;

import com.ideas.uno.game.executor.Game;
import com.ideas.uno.game.executor.GameManager;
import com.ideas.uno.game.executor.UnoGameExecutor;

/**
 *  UnoGameController this class will start the Uno Game 
 *  after taking parameters 
 */
public class UnoGameController {

	private static Map<String, Integer> playersOfTheGame = new HashMap<>();

	private static int maxScorePointToWin = 500;

	// Can be taken as input (hard coded the input)
	static {
		playersOfTheGame.put("PLAYER_1", 7);
		playersOfTheGame.put("PLAYER_2", 8);
		playersOfTheGame.put("PLAYER_3", 9);
		playersOfTheGame.put("PLAYER_4", 9);

	}

	public static void main(String[] args) {
		try {
			
			Game gameUno = new UnoGameExecutor(playersOfTheGame, maxScorePointToWin);
			GameManager gameManager = new GameManager(gameUno);
			gameManager.loadGame();
			gameManager.play();
			System.out.println("------------GAME OVER------------");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("---------Error in Game Play---------" + ex);
		}
	}

}
