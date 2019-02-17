package com.ideas.uno.game.player.direction;

import com.ideas.uno.game.card.CardType;

public class DirectionManagerFactoryImpl implements DirectionManagerFactory{

	@Override
	public DirectionManager getDirection(CardType cardType) {

		if (CardType.REVERSE.equals(cardType)) {
			return new NextReverseDirectionPlayer();
		} else {
			return new NextDirectionPlayer();
		}
	}
}
