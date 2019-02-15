package com.ideas.uno.game.executor;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.player.Player;

public class Turn {

	private final Player currentPlayer;

	private final Card playingCard;

	public Turn(Player player, Card playerCard) {
		this.currentPlayer = player;
		this.playingCard = playerCard;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public Card getPlayingCard() {
		return playingCard;
	}

	@Override
	public String toString() {
		return "Turn [player=" + currentPlayer + ", playerCard=" + playingCard + "]";
	}

}
