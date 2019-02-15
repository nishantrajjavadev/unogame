package com.ideas.uno.game.player;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;


public class PlayerManagerTest {

	NextDirectionPlayer nextDirectionPlayer;
	Map<String, Integer> playersOfTheGame;
	PlayerManager playerManager;
	
	@Before
	public void setUp(){
		loadPlayers();
		playerManager = new PlayerManager(playersOfTheGame);
	}
	
	@Test
	public void shouldReturnFirstPlayer() {
		playerManager.getFirstPlayer();
		Assert.assertTrue("Nishant".equals(playerManager.getFirstPlayer().getName()));
	}
	
	@Test
	public void shouldNotReturnEmpty() {
		Assert.assertTrue(playerManager.getGamePlayers().size()  == 4);
	}
	
	@Test
	public void shoulddistrubuteCards() {
		playerManager.distrubuteCards(new CardManager());
		playerManager.getGamePlayers().forEach(player -> Assert.assertTrue(player.getCards().size() == 7));
	}
	
	@Test
	public void shouldResetCards() {
		playerManager.distrubuteCards(new CardManager());
		playerManager.resetCards();
		playerManager.getGamePlayers().forEach(player -> Assert.assertTrue(player.getCards().size() == 0));
	}
	
	@Test
	public void shouldReturnNextPlayerIfNotNumberCard() {
		CardManager cardManager = new CardManager();
		playerManager.distrubuteCards(cardManager);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.DRAW_TWO)).findFirst().get();
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getPlayerIfNotNumberCard(currentCard, player, cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(player.getCards().size() == 9);
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals("Arpan"));
	}
	
	@Test
	public void shouldReturnNewColorWildPlayerIfNotNumberCard() {
		CardManager cardManager = new CardManager();
		playerManager.distrubuteCards(cardManager);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.WILD)).findFirst().get();
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getPlayerIfNotNumberCard(currentCard, player, cardManager);
		Assert.assertTrue(!currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals("Arpan"));
	}
	
	@Test
	public void shouldReturnNewColorWILD_D4PlayerPenaltyIfNotNumberCard() {
		CardManager cardManager = new CardManager();
		playerManager.distrubuteCards(cardManager);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.WILD_D4)).findFirst().get();
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getPlayerIfNotNumberCard(currentCard, player, cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(player.getCards().size() == 11);
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals("Arpan"));
	}
	
	@Test
	public void shouldReturnNextPlayerReverseCard() {
		CardManager cardManager = new CardManager();
		playerManager.distrubuteCards(cardManager);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.REVERSE)).findFirst().get();
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getPlayerIfNotNumberCard(currentCard, player, cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals("Arpan"));
	}
	
	@Test
	public void shouldReturnNextPlayerSkipCard() {
		CardManager cardManager = new CardManager();
		playerManager.distrubuteCards(cardManager);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.SKIP)).findFirst().get();
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getPlayerIfNotNumberCard(currentCard, player, cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals("Arpan"));
	}
	
	@Test
	public void shouldReturnSamePlayerNormalCard() {
		CardManager cardManager = new CardManager();
		playerManager.distrubuteCards(cardManager);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.NUMBER)).findFirst().get();
		Player player = playerManager.getGamePlayers().get(0);
		Turn turn = playerManager.getPlayerIfNotNumberCard(currentCard, player, cardManager);
		Assert.assertTrue(currentCard.equals(turn.getPlayingCard()));
		Assert.assertTrue(turn.getCurrentPlayer().getName().equals(player.getName()));
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
