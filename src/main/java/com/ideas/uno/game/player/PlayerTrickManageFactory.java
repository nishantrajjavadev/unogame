package com.ideas.uno.game.player;

import java.util.List;

import com.ideas.uno.game.card.Card;

@FunctionalInterface
public interface PlayerTrickManageFactory {
	
	public String SIMPLE_TRICK = "SimpleTrick";
	
	public String WILD_CARD_TRICK = "WildCardTrick";
	
	public Trick getPlayerTrick(String trickType, List<Card> myCards, Card discardPileCard);
}
