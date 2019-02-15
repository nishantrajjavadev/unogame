package com.ideas.uno.game.player;

import java.util.List;
import java.util.stream.Collectors;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardType;

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
		List<Card> myFilterCards = myCards.stream().filter(myCard -> myCard.getCardColors().equals(discardPileCard.getCardColors()) && CardType.NUMBER.equals(myCard.getCardType()))
				.collect(Collectors.toList());

		if (myFilterCards.size() > 0) {
			return getMaxNumberCard(myFilterCards);
		} else {
			List<Card> specialCards = getSpecialCardList(myCards, discardPileCard);
			if (specialCards.size() > 0) {
				return specialCards.get(0);
			}
		}
		List<Card> myWildCards = myCards.stream().filter(wildCard -> CardType.WILD.equals(wildCard.getCardType()) || CardType.WILD_D4.equals(wildCard.getCardType())).collect(Collectors.toList());
		if (myWildCards.size() > 0) {
			return myWildCards.get(0);
		}
		return null;
	}

}
