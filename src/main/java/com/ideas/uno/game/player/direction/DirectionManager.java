package com.ideas.uno.game.player.direction;

import java.util.List;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

@FunctionalInterface
public interface DirectionManager {

	public abstract Player getNextPlayer(final PlayerManager playerManager,
			final Player player);

	public default int getPlayerCurrentPostion(final List<Player> players, final Player player) {
		return players.indexOf(player);
	}
}
