package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class ReverseCardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private ReverseCardUnoPlay reverseCardUnoPlay;
	
	@Before
	public void setUp(){
		loadPlayers();
		cardManager = new CardManager();
		playerManager = new PlayerManager(playersOfTheGame);
		reverseCardUnoPlay = new ReverseCardUnoPlay(playerManager, cardManager);
	}
	
	@Test
	public void shouldReturnReverseNextPlayer() {
		playerManager.distrubuteCards(cardManager);
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = reverseCardUnoPlay.play(playerManager.getGamePlayers().get(0).getCards().get(0), playerManager.getGamePlayers().get(0));
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		Assert.assertTrue(turn.getCurrentPlayer().equals(nextPlayer));
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
