/**
 * 
 */
package com.ideas.uno.game.rules;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;

/**
 * Wild4 card rule
 * Plenty to next player (Next player has to draw 4 cards) and skip next player turn
 */
public class Wild4CardUnoPlay implements Rule {

	private final PlayerManager playerManager;
	
	private final CardManager cardManager;

	public Wild4CardUnoPlay(PlayerManager playerManager, CardManager cardManager) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		Card playerChanceCard = player.getNextTrickyCard(this.cardManager);
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		nextPlayer.wild4CardPenalty(this.cardManager);
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		playerChanceCard = nextPlayer.turn(playerChanceCard, this.cardManager);
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		return new Turn(nextPlayer, playerChanceCard);
	}

}
