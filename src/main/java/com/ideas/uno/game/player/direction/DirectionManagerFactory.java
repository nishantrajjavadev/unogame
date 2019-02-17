package com.ideas.uno.game.player.direction;

import com.ideas.uno.game.card.CardType;

public interface DirectionManagerFactory {

	public DirectionManager getDirection(CardType cardType);
}
