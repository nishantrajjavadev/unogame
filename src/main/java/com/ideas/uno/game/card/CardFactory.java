package com.ideas.uno.game.card;

import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.rules.Rule;

/**
 * CardFactory used to drive the card (base upon current card input) which need to play
 */
public interface CardFactory {

	public Rule getPlayingCard(Card currentCard, PlayerManager playerManager, CardManager cardManager);

}
