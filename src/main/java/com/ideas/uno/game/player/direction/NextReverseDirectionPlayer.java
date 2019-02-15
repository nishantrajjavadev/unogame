package com.ideas.uno.game.player.direction;

import java.util.Collections;
import java.util.List;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

public class NextReverseDirectionPlayer extends DirectionManager {

	public NextReverseDirectionPlayer(Player player, PlayerManager playerManager) {
		super(player, playerManager);
	}

	@Override
	public Player getNextPlayer() {
		List<Player> players = this.playerManager.getGamePlayers();
		Collections.reverse(this.playerManager.getGamePlayers());
		int index = getPlayerCurrentPostion();
		if (index == (players.size() - 1)) {
			return players.get(0);
		} else {
			return players.get(index + 1);
		}
	}
}
