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
 * Normal card rule
 * Just next player turns or if card is special then current play will be play
 */
public class NormalCardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;

	private final DirectionManagerFactory directionManagerFactory;
	
	public NormalCardUnoPlay(PlayerManager playerManager, CardManager cardManager, DirectionManagerFactory directionManagerFactory) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
		this.directionManagerFactory = directionManagerFactory;
	}

	@Override
	public Turn play(final Card discardPileCard, final Player player) {
		Card myChanceCard = player.turn(discardPileCard, this.cardManager);
		if(myChanceCard == null){
			return new Turn(player, null);
		}
		if (isActionCard(myChanceCard)) {
			return new Turn(player, myChanceCard);
		}
		DirectionManager directionManager = directionManagerFactory.getDirection(myChanceCard.getCardType());
		Player nextPlayer = directionManager.getNextPlayer(this.playerManager, player);
		return new Turn(nextPlayer, myChanceCard);
	}

	/**
	 * @param myChanceCard
	 * @return
	 */
	public boolean isActionCard(Card myChanceCard) {
		return CardType.WILD.equals(myChanceCard.getCardType()) || CardType.WILD_D4.equals(myChanceCard.getCardType()) || CardType.DRAW_TWO.equals(myChanceCard.getCardType())
				|| CardType.REVERSE.equals(myChanceCard.getCardType()) || CardType.SKIP.equals(myChanceCard.getCardType());
	}
}
