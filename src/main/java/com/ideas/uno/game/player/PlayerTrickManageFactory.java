package com.ideas.uno.game.player;

import java.util.List;

import com.ideas.uno.game.card.Card;

@FunctionalInterface
public interface PlayerTrickManageFactory {
	
	public Trick getPlayerTrick(PlayerTrickEnum trickType, List<Card> myCards, Card discardPileCard);
}
