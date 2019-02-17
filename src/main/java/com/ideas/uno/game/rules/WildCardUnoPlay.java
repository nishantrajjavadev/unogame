/**
 * 
 */
package com.ideas.uno.game.rules;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.DirectionManager;
import com.ideas.uno.game.player.direction.DirectionManagerFactory;

/**
 * Wild card rule
 * Current player must change color
 */
public class WildCardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;

	private final DirectionManagerFactory directionManagerFactory;
	
	public WildCardUnoPlay(PlayerManager playerManager, CardManager cardManager, DirectionManagerFactory directionManagerFactory) {
			this.playerManager = playerManager;
			this.cardManager = cardManager;
			this.directionManagerFactory = directionManagerFactory;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		Card playerChanceCard = player.getNextTrickyCard(this.cardManager);
		if(playerChanceCard == null){
			return new Turn(player, null);
		}
		CardType cardType = playerChanceCard.getCardType();
		DirectionManager directionManager = directionManagerFactory.getDirection(cardType);
		Player nextPlayer = null;
		if (CardType.WILD_D4.equals(cardType)) {
			nextPlayer = directionManager.getNextPlayer(this.playerManager, player);
			nextPlayer.wild4CardPenalty(this.cardManager);
		} else if (CardType.DRAW_TWO.equals(cardType)) {
			nextPlayer = directionManager.getNextPlayer(this.playerManager, player);
			nextPlayer.draw2CardPenalty(this.cardManager);
		} else if (CardType.WILD.equals(cardType)) {
			playerChanceCard = player.getNextTrickyCard(this.cardManager);
		}
		if (nextPlayer == null) {
			nextPlayer = player;
		} 
		nextPlayer = directionManager.getNextPlayer(this.playerManager, nextPlayer);
		Card nextPlayerCard = nextPlayer.turn(playerChanceCard, this.cardManager);
		return new Turn(directionManager.getNextPlayer(this.playerManager, nextPlayer), nextPlayerCard);
	}

}
