package com.ideas.uno.game.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardType;

public interface Trick {
	public Card myTrick();

	public default Card getMaxNumberCard(final List<Card> cards) {
		return cards.stream().max(Comparator.comparing(Card::getNumber)).get();
	}

	public default List<Card> getActionCardList(final List<Card> cards, Card matchingCard) {
		return cards.stream().filter(specialCard -> specialCard.getCardColors().equals(matchingCard.getCardColors())).collect(Collectors.toList());
	}
	
	public default List<Card> getNumberCardsWithSameColor(final List<Card> cards, final Card matchingCard){
		return cards.stream().filter(myCard -> myCard.getCardColors().equals(matchingCard.getCardColors()) && CardType.NUMBER.equals(myCard.getCardType()))
				.collect(Collectors.toList());
	}
	
	public default List<Card> getAnyNumberCardsWithSameColor(final List<Card> cards, final Card matchingCard){
		return cards.stream().filter(myCard -> myCard.getNumber() == matchingCard.getNumber()).collect(Collectors.toList());
	}
	
	public default List<Card> getAnyWildCard(final List<Card> cards){
		return cards.stream().filter(wildCard -> CardType.WILD.equals(wildCard.getCardType()) || CardType.WILD_D4.equals(wildCard.getCardType())).collect(Collectors.toList());
	}
}
