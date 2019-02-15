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

	private static Map<String, Integer> playersOfTheGame = new HashMap<String, Integer>();

	private static int maxScorePointToWin = 500;

	// Can be taken as input (hard coded the input)
	static {
		playersOfTheGame.put("Nishant", 7);
		playersOfTheGame.put("Arpan", 8);
		playersOfTheGame.put("Shivranjani", 9);
		playersOfTheGame.put("Sahil", 10);
	}

	public static void main(String[] args) {
		try {
			Game gameUno = new UnoGameExecutor(playersOfTheGame, maxScorePointToWin);
			new GameManager(gameUno).play();
			System.out.println("------------GAME OVER------------");
		} catch (Exception ex) {
			System.out.println("---------Error in Game Play---------" + ex.getMessage());
		}
	}

}
