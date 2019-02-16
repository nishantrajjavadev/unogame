package com.ideas.uno.game.rules;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.PlayerManager;

@Ignore
public class ReverseCardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private ReverseCardUnoPlay reverseCardUnoPlay;
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
		reverseCardUnoPlay = new ReverseCardUnoPlay(playerManager, cardManager);
	}
	
	@Test
	public void shouldReturnReverseNextPlayer() {
		playerManager.distrubuteCards(cardManager);
		Turn turn = reverseCardUnoPlay.play(new Card(CardColor.BLUE, CardType.REVERSE, 20), playerManager.getGamePlayers().get(0));
		Assert.assertTrue(turn.getCurrentPlayer().equals(playerManager.getGamePlayers().get(3)));
	}
	
	private Map<String, Integer> loadPlayers() {
		playersOfTheGame = new HashMap<String, Integer>();
		playersOfTheGame.put("PLAYER_1", 7);
		playersOfTheGame.put("PLAYER_2", 8);
		playersOfTheGame.put("PLAYER_3", 9);
		playersOfTheGame.put("PLAYER_4", 10);
		return playersOfTheGame;
	}
}
