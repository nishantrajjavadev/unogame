package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;

public class Player {

	final private int age;

	final private String name;

	private final List<Card> myCards;

	private int score;

	public Player(final String name, final int age) {
		this.age = age;
		this.name = name;
		myCards = new ArrayList<Card>(20);
	}

	public int getAge() {
		return age;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void addCard(final Card card) {
		this.myCards.add(card);
		System.out.println(this.name + " Picked the card from draw pile : " + card);
	}

	public void removeCards(final Card card) {
		System.out.println(this.name + " Played " + card);
		this.myCards.remove(card);
		if (myCards.size() == 1) {
			System.out.println(this.name + " -------------UNO----------- ");
		}
	}

	public List<Card> getCards() {
		return Collections.unmodifiableList(this.myCards);
	}

	public Card turn(final Card discardPileCard, final CardManager cardManager) {
		Card myChance = null;
		if(discardPileCard == null){
			return null;
		}
		myChance = new PlayerSimpleTrick(myCards, discardPileCard).myTrick();
		if (myChance != null) {
			sendHandCardToDiscardPileCard(cardManager.getCardDeck(), myChance);
			if (this.myCards.size() == 0) {
				return null;
			}
			return myChance;
		} else {
			Card newDard = null;
			newDard = cardManager.getCardDeck().getDrawPile().pop();
			addCard(newDard);
			if (newDard.getCardColors().equals(discardPileCard.getCardColors()) || CardType.WILD.equals(newDard.getCardType()) || CardType.WILD_D4.equals(newDard.getCardType())) {
				sendHandCardToDiscardPileCard(cardManager.getCardDeck(), newDard);
				if (this.myCards.size() == 0) {
					return null;
				}
				return newDard;
			}
			return discardPileCard;
		}
	}

	/**
	 * @param cardDeck
	 */
	public void draw2CardPenalty(final CardManager cardManager) {
		for (int i = 0; i < 2; i++) {
			addCard(cardManager.getCardDeck().getDrawPile().pop());
		}
	}

	/**
	 * @param cardDeck
	 */
	public void wild4CardPenalty(final CardManager cardManager) {
		for (int i = 0; i < 4; i++) {
			addCard(cardManager.getCardDeck().getDrawPile().pop());
		}
	}

	private void sendHandCardToDiscardPileCard(CardDeck cardDeck, Card myChance) {
		removeCards(myChance);
		cardDeck.addCardToDiscardPile(myChance);
	}

	public Card getNextTrickyCard(final CardManager cardManager) {
		Card myTrickyCard = new PlayerWildCardTrick(myCards).myTrick();
		if (myTrickyCard != null) {
			sendHandCardToDiscardPileCard(cardManager.getCardDeck(), myTrickyCard);
			if (this.myCards.size() == 0) {
				return null;
			}
		}
		return myTrickyCard;
	}

	public void addScore(int score) {
		this.score = this.score + score;
	}

	public boolean reset() {
		this.myCards.clear();
		return true;
	}

	@Override
	public String toString() {
		return "Player [age=" + age + ", name=" + name + ", myCards=" + myCards
				+ ", score=" + score + "]";
	}
	
	
}