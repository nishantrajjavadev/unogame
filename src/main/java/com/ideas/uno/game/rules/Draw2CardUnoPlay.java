/**
 * 
 */
package com.ideas.uno.game.rules;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.DirectionManager;
import com.ideas.uno.game.player.direction.DirectionManagerFactory;

/**
 * Draw card rule
 * Plenty to next player (Next player has to draw 2 cards) and skip next player turn
 */
public class Draw2CardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;
	
	private final DirectionManagerFactory directionManagerFactory;

	public Draw2CardUnoPlay(PlayerManager playerManager, CardManager cardManager, DirectionManagerFactory directionManagerFactory) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
		this.directionManagerFactory = directionManagerFactory;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		DirectionManager directionManager = directionManagerFactory.getDirection(myCurrentCard.getCardType());
		Player nextPlayer =directionManager.getNextPlayer(this.playerManager, player);
		nextPlayer.draw2CardPenalty(this.cardManager);
		directionManager = directionManagerFactory.getDirection(myCurrentCard.getCardType());
		nextPlayer = directionManager.getNextPlayer(this.playerManager, nextPlayer);
		return new Turn(nextPlayer, nextPlayer.turn(myCurrentCard, this.cardManager));
	}

}
