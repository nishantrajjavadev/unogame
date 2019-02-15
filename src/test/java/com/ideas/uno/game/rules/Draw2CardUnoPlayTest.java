package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class Draw2CardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private Draw2CardUnoPlay draw2CardUnoPlay;
	
	@Before
	public void setUp(){
		loadPlayers();
		cardManager = new CardManager();
		playerManager = new PlayerManager(playersOfTheGame);
		draw2CardUnoPlay = new Draw2CardUnoPlay(playerManager, cardManager);
	}
	
	
	@Test
	public void shouldHavePenalty2drawcardPlayer() {
		playerManager.distrubuteCards(cardManager);
		Player player = playerManager.getGamePlayers().get(0);
		draw2CardUnoPlay.play(playerManager.getGamePlayers().get(0).getCards().get(0), playerManager.getGamePlayers().get(0));
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		Assert.assertTrue(nextPlayer.getCards().size() == 9);
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
