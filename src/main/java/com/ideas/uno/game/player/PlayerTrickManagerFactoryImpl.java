package com.ideas.uno.game.player;

import java.util.List;

import com.ideas.uno.game.card.Card;

public class PlayerTrickManagerFactoryImpl implements PlayerTrickManageFactory {

	@Override
	public Trick getPlayerTrick(PlayerTrickEnum trickType, List<Card> myCards, Card discardPileCard) {
		if(PlayerTrickEnum.SIMPLE_TRICK.equals(trickType)){
			return new PlayerSimpleTrick(myCards, discardPileCard);
		} else if(PlayerTrickEnum.WILD_CARD_TRICK.equals(trickType)){
			return new PlayerWildCardTrick(myCards);
		}
		 return new PlayerSimpleTrick(myCards, discardPileCard);
	}
}
