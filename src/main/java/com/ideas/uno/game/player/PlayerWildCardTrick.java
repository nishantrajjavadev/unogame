package com.ideas.uno.game.player;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.ideas.uno.game.card.Card;

public class PlayerWildCardTrick implements Trick {

	final List<Card> myCards;

	public PlayerWildCardTrick(final List<Card> myCards) {
		this.myCards = myCards;
	}

	@Override
	public Card myTrick() {
		return myCards.stream().collect(Collectors.maxBy(Comparator.comparing(Card::getCardColors))).orElse(myCards.stream().findAny().get());
	}

}
