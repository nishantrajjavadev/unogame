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
 * Skip card rule
 * Plenty to next player (skip next player turn)
 */
public class SkipCardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;

	public SkipCardUnoPlay(PlayerManager playerManager, CardManager cardManager) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		Player nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		return new Turn(nextPlayer, nextPlayer.turn(myCurrentCard, this.cardManager));
	}

}
