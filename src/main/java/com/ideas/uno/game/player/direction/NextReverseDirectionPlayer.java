package com.ideas.uno.game.player.direction;

import java.util.Collections;
import java.util.List;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

public class NextReverseDirectionPlayer implements DirectionManager {

	@Override
	public Player getNextPlayer(PlayerManager playerManager, Player player) {
		List<Player> players = playerManager.getGamePlayers();
		Collections.reverse(players);
		int index = getPlayerCurrentPostion(players, player);
		if (index == (players.size() - 1)) {
			return players.get(0);
		} else {
			return players.get(index + 1);
		}
	}
}
