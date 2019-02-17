package com.ideas.uno.game.executor;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardFactory;
import com.ideas.uno.game.card.CardFactoryImpl;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardManagerImpl;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.PlayerManagerImpl;
import com.ideas.uno.game.player.PlayerScoreBoardManager;
import com.ideas.uno.game.player.PlayerScoreBorad;
import com.ideas.uno.game.player.direction.DirectionManagerFactory;
import com.ideas.uno.game.player.direction.DirectionManagerFactoryImpl;
import com.ideas.uno.game.rules.Rule;

/**
 * This is main UnoGame executor. Responsible for complete unoGame life cycle:
 * - Game initialization
 * - Game load
 * - Game play
 * - Announcing the result
 */
public class UnoGameExecutor implements Game {

	private PlayerScoreBoardManager scoreBoardManager;

	private AtomicInteger maxNoOfGameRounds = new AtomicInteger(1);

	private PlayerManager playerManager;

	private int maxScorePointToWin;

	private CardManager cardManager;
	
	private Map<String, Integer> playersOfTheGame;
	
	private CardFactory cardFactory;
	
	private DirectionManagerFactory directionManagerFactory;
	
	/**
	 * Initialize players,cardDeck and player score board with default values
	 * @param playersOfTheGame
	 * @param maxScorePointToWin
	 */
	public UnoGameExecutor(final Map<String, Integer> playersOfTheGame, final int maxScorePointToWin) {
		this.maxScorePointToWin = maxScorePointToWin;
		this.playersOfTheGame = playersOfTheGame;
	}

	/**
	 * load the games (Loads the cards and distribute cards to the players)
	 */
	@Override
	public void loadGame() {
		directionManagerFactory = new DirectionManagerFactoryImpl();
		cardFactory = new CardFactoryImpl(directionManagerFactory);
		this.playerManager = new PlayerManagerImpl(playersOfTheGame);
		this.playerManager.loadPlayer();
		this.cardManager = new CardManagerImpl(CardDeck.getInstance());
		this.scoreBoardManager = new PlayerScoreBoardManager(new PlayerScoreBorad(this.playerManager.getGamePlayers(), this.maxScorePointToWin));
		System.out.println("-------------------Dealing Cards---------------");
		this.playerManager.distrubuteCards(cardManager);
		System.out.println("-------------------7 cards to each player---------------");
		System.out.println("-------------------Lets Play---------------");
	}

	/**
	 * This is the entry point of the game start
	 * This method covers the whole life cycle of the game (Start till end)
	 */
	@Override
	public void play() {
		CardDeck cardDeck = this.cardManager.getCardDeck();
		Player firstPlayer = this.playerManager.getFirstPlayer();
		Card currentCard = cardDeck.getFirstCard();
		cardDeck.addCardToDiscardPile(currentCard);
		Turn currentTurn = getFirstDrawPileCard(firstPlayer, currentCard);
		while (true) {
			if (currentTurn == null || currentTurn.getPlayingCard() == null) {
				System.out.println("-----Round-------" + maxNoOfGameRounds);
				maxNoOfGameRounds.incrementAndGet();
				this.scoreBoardManager.calculateScore();
				break;
			}
			Player currentPlayer = currentTurn.getCurrentPlayer();
			Card playingCard = currentTurn.getPlayingCard();
			// break game in case of no card left to one player
			
			Rule unoPlay = this.cardFactory.getPlayingCard(playingCard, this.playerManager, this.cardManager);
			currentTurn = unoPlay.play(playingCard, currentPlayer);
		}

		if (this.scoreBoardManager.isGameFinished()) {
			System.out.println("-----Maximum score point to win-------" + maxScorePointToWin);
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
	 * @param firstPlayer
	 * @param currentCard
	 * @return
	 */
	public Turn getFirstDrawPileCard(Player firstPlayer, Card currentCard) {
		Turn currentTurn =  this.playerManager.getFirstPlayerIfNotNumberCard(currentCard, firstPlayer, this.cardManager);
		return currentTurn;
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
		Player player = scoreBoardManager.getCurrentTopPlayer();
		System.out.println(" Top score Player : " + player.getName() + " Player Point : " + player.getScore());
	}

	/**
	 * reset the complete game when new game round starts
	 * 
	 */
	public void resetGameState() {
		playerManager.resetCards();
		cardManager.resetCardDeck();
		this.playerManager.distrubuteCards(cardManager);
	}
}
