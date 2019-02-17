package com.ideas.uno.game.player.direction;

import java.util.List;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

public class NextDirectionPlayer implements DirectionManager {

	@Override
	public Player getNextPlayer(final PlayerManager playerManager, final Player player) {
		List<Player> players = playerManager.getGamePlayers();
		int index = getPlayerCurrentPostion(players, player);
		if (index == (players.size() - 1)) {
			return players.get(0);
		} else {
			return players.get(index + 1);
		}
	}

}
