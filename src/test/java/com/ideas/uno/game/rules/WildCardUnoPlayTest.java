package com.ideas.uno.game.rules;

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


public class WildCardUnoPlayTest {

	private Map<String, Integer> playersOfTheGame;
	private PlayerManager playerManager;
	private CardManager cardManager;
	private WildCardUnoPlay wildCardUnoPlay;
	
	@Before
	public void setUp(){
		loadPlayers();
		cardManager = new CardManager();
		playerManager = new PlayerManager(playersOfTheGame);
		wildCardUnoPlay = new WildCardUnoPlay(playerManager, cardManager);
	}
	
	@Test
	public void shouldReturnFirstPlayer() {
		playerManager.distrubuteCards(cardManager);
		Player player = playerManager.getGamePlayers().get(0);
		Card currentCard = cardManager.getCardDeck().getDrawPile().stream().filter(card -> card.getCardType().equals(CardType.NUMBER)).findFirst().get();
		Turn turn = wildCardUnoPlay.play(currentCard, playerManager.getGamePlayers().get(0));
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		Assert.assertTrue(nextPlayer.getName().equals(turn.getCurrentPlayer().getName()));
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
