package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class SkipCardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private SkipCardUnoPlay skipCardUnoPlay;
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
		skipCardUnoPlay = new SkipCardUnoPlay(playerManager, cardManager);
	}
	
	@Test
	public void shouldReturnNextSkipPlayer() {
		playerManager.distrubuteCards(cardManager);
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = skipCardUnoPlay.play(playerManager.getGamePlayers().get(0).getCards().get(0), playerManager.getGamePlayers().get(0));
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
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
