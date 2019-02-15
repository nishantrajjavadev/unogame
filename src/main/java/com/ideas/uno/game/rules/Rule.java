package com.ideas.uno.game.rules;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.Player;

public interface Rule {
	Turn play(Card myCurrentCard, Player player);
}
