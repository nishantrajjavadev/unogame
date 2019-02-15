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
 * Draw card rule
 * Plenty to next player (Next player has to draw 2 cards) and skip next player turn
 */
public class Draw2CardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;

	public Draw2CardUnoPlay(PlayerManager playerManager, CardManager cardManager) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		nextPlayer.draw2CardPenalty(this.cardManager);
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		return new Turn(nextPlayer, nextPlayer.turn(myCurrentCard, this.cardManager));
	}

}
