package com.ideas.uno.game.card;

import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.DirectionManagerFactory;
import com.ideas.uno.game.rules.Draw2CardUnoPlay;
import com.ideas.uno.game.rules.NormalCardUnoPlay;
import com.ideas.uno.game.rules.ReverseCardUnoPlay;
import com.ideas.uno.game.rules.Rule;
import com.ideas.uno.game.rules.SkipCardUnoPlay;
import com.ideas.uno.game.rules.Wild4CardUnoPlay;
import com.ideas.uno.game.rules.WildCardUnoPlay;

/**
 * CardFactory used to drive the card (base upon current card input) which need to play
 */
public class CardFactoryImpl implements CardFactory{

	private final DirectionManagerFactory directionManagerFactory;
	
	public CardFactoryImpl(DirectionManagerFactory directionManagerFactory) {
		this.directionManagerFactory = directionManagerFactory;
	}

	@Override
	public Rule getPlayingCard(Card currentCard, PlayerManager playerManager, CardManager cardManager) {
		
		if (CardType.REVERSE.equals(currentCard.getCardType())) {
			return new ReverseCardUnoPlay(playerManager, cardManager, directionManagerFactory);
		} else if (CardType.WILD.equals(currentCard.getCardType())) {
			return new WildCardUnoPlay(playerManager, cardManager, directionManagerFactory);
		} else if (CardType.WILD_D4.equals(currentCard.getCardType())) {
			return new Wild4CardUnoPlay(playerManager, cardManager, directionManagerFactory);
		} else if (CardType.DRAW_TWO.equals(currentCard.getCardType())) {
			return new Draw2CardUnoPlay(playerManager, cardManager, directionManagerFactory);
		} else if (CardType.SKIP.equals(currentCard.getCardType())) {
			return new SkipCardUnoPlay(playerManager, cardManager, directionManagerFactory);
		} else {
			return new NormalCardUnoPlay(playerManager, cardManager, directionManagerFactory);
		}
	}

}
