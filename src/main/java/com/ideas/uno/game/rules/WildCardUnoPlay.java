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
import com.ideas.uno.game.player.direction.NextDirectionPlayer;

/**
 * Wild card rule
 * Current player must change color
 */
public class WildCardUnoPlay implements Rule {

	private final PlayerManager playerManager;

	private final CardManager cardManager;

	public WildCardUnoPlay(PlayerManager playerManager, CardManager cardManager) {
		this.playerManager = playerManager;
		this.cardManager = cardManager;
	}

	@Override
	public Turn play(final Card myCurrentCard, final Player player) {
		Card playerChanceCard = player.getNextTrickyCard(this.cardManager);
		Player nextPlayer = null;
		if (CardType.WILD_D4.equals(playerChanceCard.getCardType())) {
			nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
			nextPlayer.wild4CardPenalty(this.cardManager);
		} else if (CardType.DRAW_TWO.equals(playerChanceCard.getCardType())) {
			nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
			nextPlayer.draw2CardPenalty(this.cardManager);
		} else if (CardType.WILD.equals(playerChanceCard.getCardType())) {
			playerChanceCard = player.getNextTrickyCard(this.cardManager);
		}
		if (nextPlayer != null) {
			nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		} else {
			nextPlayer = new NextDirectionPlayer(player, this.playerManager).getNextPlayer();
		}
		Card nextPlayerCard = nextPlayer.turn(playerChanceCard, this.cardManager);
		nextPlayer = new NextDirectionPlayer(nextPlayer, this.playerManager).getNextPlayer();
		return new Turn(nextPlayer, nextPlayerCard);
	}

}
