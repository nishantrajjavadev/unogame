package com.ideas.uno.game.player;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardManagerImpl;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;

public class PlayerManagerTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private static CardDeck cardDeck;
	CardManager cardManager;

	@BeforeClass
	public static void beforeClass(){
		cardDeck = CardDeck.getInstance();
	}
	
	@Before
	public void setUp() {
		cardManager = new CardManagerImpl(cardDeck);
		loadPlayers();
		playerManager = new PlayerManagerImpl(playersOfTheGame);
		playerManager.loadPlayer();
	}

	@Test
	public void shouldReturnFirstPlayer() {
		playerManager.getFirstPlayer();
		Assert.assertTrue("PLAYER_1".equals(playerManager.getFirstPlayer()
				.getName()));
	}

	@Test
	public void shouldNotReturnEmpty() {
		Assert.assertTrue(playerManager.getGamePlayers().size() == 4);
	}


	@Test
	public void shouldResetCards() {
		playerManager.distrubuteCards(cardManager);
		playerManager.resetCards();
		playerManager.getGamePlayers().forEach(
				player -> Assert.assertTrue(player.getCards().size() == 0));
	}


	@Test
	public void shouldReturnNextPlayerReverseCard() {
		playerManager.distrubuteCards(cardManager);
		Card currentCard = new Card(CardColor.GREEN, CardType.REVERSE, 20);
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getFirstPlayerIfNotNumberCard(currentCard, player,
				cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals(playerManager.getGamePlayers().get(3).getName()));
	}

	@Test
	public void shouldReturnNextPlayerSkipCard() {
		playerManager.distrubuteCards(cardManager);
		Card currentCard = new Card(CardColor.RED, CardType.SKIP, 20);
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getFirstPlayerIfNotNumberCard(currentCard, player,
				cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals("PLAYER_3"));
	}

	@Test
	public void shouldReturnSamePlayerNormalCard() {
		playerManager.distrubuteCards(cardManager);
		Card currentCard = new Card(CardColor.GREEN, CardType.NUMBER, 20);
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getFirstPlayerIfNotNumberCard(currentCard, player,
				cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName()
				.equals(player.getName()));
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
