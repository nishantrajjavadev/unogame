package com.ideas.uno.game.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ideas.uno.game.card.Card;

public interface Trick {
	public Card myTrick();

	public default Card getMaxNumberCard(List<Card> cards) {
		return cards.stream().max(Comparator.comparing(Card::getNumber)).get();
	}

	public default List<Card> getSpecialCardList(List<Card> cards, Card matchingCard) {
		return cards.stream().filter(specialCard -> specialCard.getCardColors().equals(matchingCard.getCardColors())).collect(Collectors.toList());
	}
}
