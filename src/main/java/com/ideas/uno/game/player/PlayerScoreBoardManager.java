package com.ideas.uno.game.player;

import java.util.Comparator;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.ideas.uno.game.card.Card;

/**
 * Manage the players score board 
 *
 */
public class PlayerScoreBoardManager {

	private Player currentTopPlayer;


	private PlayerScoreBorad playerScoreBorad;

	public PlayerScoreBoardManager(PlayerScoreBorad playerScoreBorad) {
		this.playerScoreBorad = playerScoreBorad;
	}

	// calculate score
	public void calculateScore() {
		List<Player> players = playerScoreBorad.getPlayers();
		int totalScore = 0;
		for (Player player : players) {
			totalScore = totalScore + player.getCards().stream().mapToInt(Card::getNumber).sum();
			if (CollectionUtils.isEmpty(player.getCards())) {
				this.currentTopPlayer = player;
			}
		}
		currentTopPlayer.addScore(totalScore);
	}

	// check if any player wins the game
	public boolean isGameFinished() {
		Player player = playerScoreBorad.getPlayers().stream().max(Comparator.comparing(Player::getScore)).get();
		return player.getScore() >= this.playerScoreBorad.getWinningPoints();
	}

	public Player getCurrentTopPlayer() {
		return currentTopPlayer;
	}

}
