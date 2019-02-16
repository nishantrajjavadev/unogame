package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;


public class WildCardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private WildCardUnoPlay wildCardUnoPlay;
	private Player player;
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
		player = Mockito.mock(Player.class);
	}
	
	@Test
	public void shouldReturnFirstPlayer() {
		Card card= new Card(null, CardType.NUMBER, 10);
		Mockito.when(player.getNextTrickyCard(cardManager)).thenReturn(card);
		wildCardUnoPlay = new WildCardUnoPlay(playerManager, cardManager);
		wildCardUnoPlay.play(card, player);
		Mockito.verify(player).getNextTrickyCard(cardManager);
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
