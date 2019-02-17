package com.ideas.uno.game.player;

import java.util.List;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.executor.Turn;


/**
 * Interface to Manage the player list
 *
 */
public interface PlayerManager {

	public List<Player> getGamePlayers();

	public void distrubuteCards(CardManager cardManager);

	public Player getFirstPlayer();

	public Turn getFirstPlayerIfNotNumberCard(Card currentCard, Player firstPlayer,
			CardManager cardManager);

	public void resetCards();

	public void loadPlayer();

}
