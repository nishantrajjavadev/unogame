/**
 * 
 */
package com.ideas.uno.game.rules;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextReverseDirectionPlayer;

/**
 * Reverse card rule
 * Reverse the turn
 */
public class ReverseCardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;

	public ReverseCardUnoPlay(PlayerManager playerManager, CardManager cardManager) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		Player nextPlayer = new NextReverseDirectionPlayer(player, this.playerManager).getNextPlayer();
		return new Turn(nextPlayer, nextPlayer.turn(myCurrentCard, cardManager));
	}

}
