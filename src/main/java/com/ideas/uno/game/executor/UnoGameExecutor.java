package com.ideas.uno.game.executor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardFactory;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.PlayerScoreBoardManager;
import com.ideas.uno.game.player.PlayerScoreBorad;
import com.ideas.uno.game.rules.Rule;

/**
 * This is main UnoGame executor. Responsible for complete unoGame life cycle:
 * - Game initialization
 * - Game load
 * - Game play
 * - Announcing the result
 */
public class UnoGameExecutor implements Game {

	private PlayerScoreBoardManager scoreManager;

	private AtomicInteger maxNoOfGameRounds = new AtomicInteger(1);

	private final PlayerManager playerManager;

	private int maxScorePointToWin;

	private final CardManager cardManager;

	/**
	 * Initialize players,cardDeck and player score board with default values
	 * @param playersOfTheGame
	 * @param maxScorePointToWin
	 */
	public UnoGameExecutor(final Map<String, Integer> playersOfTheGame, final int maxScorePointToWin) {
		this.maxScorePointToWin = maxScorePointToWin;
		this.playerManager = new PlayerManager(playersOfTheGame);
		this.cardManager = new CardManager();
		this.scoreManager = new PlayerScoreBoardManager(new PlayerScoreBorad(this.playerManager.getGamePlayers(), this.maxScorePointToWin));
		loadGame(this.cardManager);
	}

	/**
	 * load the games (Loads the cards and distribute cards to the players)
	 */
	private void loadGame(CardManager cardManager) {
		this.playerManager.distrubuteCards(cardManager);
	}

	/**
	 * This is the entry point of the game start
	 * This method covers the whole life cycle of the game (Start till end)
	 */
	@Override
	public void play() {
		Player firstPlayer = this.playerManager.getFirstPlayer();
		Card currentCard = cardManager.getCardDeck().getFirstCard();
		cardManager.getCardDeck().addCardToDiscardPile(currentCard);
		Turn currentTurn = CardType.NUMBER.equals(currentCard.getCardType()) ? new Turn(firstPlayer, currentCard) : this.playerManager.getPlayerIfNotNumberCard(currentCard, firstPlayer, cardManager);
		while (true) {
			Player currentPlayer = currentTurn.getCurrentPlayer();
			Card playingCard = currentTurn.getPlayingCard();
			// break game in case of no card left to one player
			if (playingCard == null) {
				System.out.println("-----Round-------" + maxNoOfGameRounds);
				maxNoOfGameRounds.incrementAndGet();
				break;
			}
			Rule unoPlay = new CardFactory().getPlayingCard(playingCard, playerManager, cardManager);
			currentTurn = unoPlay.play(playingCard, currentPlayer);
		}

		scoreManager.calculateScore();
		if (scoreManager.isGameFinished()) {
			printLog();
			return;
		}
		System.out.println("-----Game Continue-------");
		printLog();

		resetGameState();
		// Continue game as player is not reached to winning score
		play();
	}

	/**
	 * Print logs for players score
	 * can make it better to using logging
	 * 
	 */
	private void printLog() {
		playerManager.getGamePlayers().forEach(logPlayer -> {
			System.out.println("----- Each Player score-------");
			System.out.println(" Player, Score : " + logPlayer.getName() + ", " + logPlayer.getScore());
		});
		System.out.println(" Top score Player : " + scoreManager.getCurrentTopPlayer().getName() + " Player Point : " + scoreManager.getCurrentTopPlayer().getScore());
	}

	/**
	 * reset the complete game when new game round starts
	 * 
	 */
	private void resetGameState() {
		playerManager.resetCards();
		cardManager.resetCardDeck();
		loadGame(cardManager);
	}

}
