package com.ideas.uno.game.player.direction;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

public class NextDirectionPlayer implements DirectionManager {

	@Override
	public Player getNextPlayer(final PlayerManager playerManager, final Player player) {
		return getPlayerNextPosition(player, playerManager.getGamePlayers());
	}

}
