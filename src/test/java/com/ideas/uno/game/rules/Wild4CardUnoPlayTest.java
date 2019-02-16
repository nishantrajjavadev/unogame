package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class Wild4CardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private Wild4CardUnoPlay wild4CardUnoPlay;
	private static CardDeck cardDeck;

	@BeforeClass
	public static void beforeClass() {
		cardDeck = CardDeck.getInstance();
	}
	@Before
	public void setUp(){
		loadPlayers();
		cardManager = new CardManager(cardDeck);
		playerManager = new PlayerManager(playersOfTheGame);
		wild4CardUnoPlay = new Wild4CardUnoPlay(playerManager, cardManager);
	}
	
	@Test
	public void shouldReturnPenalty2drawPlayer() {
		playerManager.distrubuteCards(cardManager);
		Player player = playerManager.getGamePlayers().get(0);
		wild4CardUnoPlay.play(playerManager.getGamePlayers().get(0).getCards().get(0), playerManager.getGamePlayers().get(0));
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		Assert.assertTrue(nextPlayer.getCards().size() == 11);
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
