package com.ideas.uno.game.player;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.ideas.uno.game.card.Card;

/*
 * PlayerSimpleTrick play cards based on the Number cards
 * If number cards not present play special card
 * if number cards is also not present play wild card 
 * 
 */
public class PlayerSimpleTrick implements Trick {

	final List<Card> myCards;
	final Card discardPileCard;

	public PlayerSimpleTrick(List<Card> myCards, Card discardPileCard) {
		this.myCards = myCards;
		this.discardPileCard = discardPileCard;
	}

	@Override
	public Card myTrick() {
		List<Card> myMatchingColorCards = getNumberCardsWithSameColor(this.myCards, this.discardPileCard);
		if (CollectionUtils.isNotEmpty(myMatchingColorCards)) 
			return getMaxNumberCard(myMatchingColorCards);
		
		List<Card> myMatchingNumberCards = getAnyNumberCardsWithSameColor(this.myCards, this.discardPileCard);
		if(CollectionUtils.isNotEmpty(myMatchingNumberCards)) 
			return myMatchingNumberCards.get(0);
		
		List<Card> actionCards = getActionCardList(this.myCards, this.discardPileCard);
		if(CollectionUtils.isNotEmpty(actionCards)) 
			return actionCards.get(0);
		
		List<Card> myWildCards = getAnyWildCard(this.myCards);
		if (CollectionUtils.isNotEmpty(myWildCards)) 
			return myWildCards.get(0);
		
		return null;
	}

}
