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
 * Wild4 card rule
 * Plenty to next player (Next player has to draw 4 cards) and skip next player turn
 */
public class Wild4CardUnoPlay implements Rule {

	private final PlayerManager playerManager;
	
	private final CardManager cardManager;

	private final DirectionManagerFactory directionManagerFactory;
		
	public Wild4CardUnoPlay(PlayerManager playerManager, CardManager cardManager, DirectionManagerFactory directionManagerFactory) {
			this.playerManager = playerManager;
			this.cardManager = cardManager;
			this.directionManagerFactory = directionManagerFactory;
		}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		DirectionManager directionManager = directionManagerFactory.getDirection(myCurrentCard.getCardType());
		Card playerChanceCard = player.getNextTrickyCard(this.cardManager);
		Player nextPlayer = directionManager.getNextPlayer(this.playerManager, player);
		nextPlayer.wild4CardPenalty(this.cardManager);
		nextPlayer = directionManager.getNextPlayer(this.playerManager, nextPlayer);
		playerChanceCard = nextPlayer.turn(playerChanceCard, this.cardManager);
		nextPlayer = directionManager.getNextPlayer(this.playerManager, nextPlayer);
		return new Turn(nextPlayer, playerChanceCard);
	}

}
