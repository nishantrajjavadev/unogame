package com.ideas.uno.game.card;

import com.ideas.uno.game.player.Player;

public interface CardManager {

	void draw(Player player, int noOfCards);

	CardDeck getCardDeck();

	void resetCardDeck();
}
