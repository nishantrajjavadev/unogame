package com.ideas.uno.game.player.direction;

import java.util.List;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

public class NextDirectionPlayer extends DirectionManager {

	public NextDirectionPlayer(Player player, PlayerManager playerManager) {
		super(player, playerManager);
	}

	@Override
	public Player getNextPlayer() {
		List<Player> players = this.playerManager.getGamePlayers();
		int index = getPlayerCurrentPostion(players);
		if (index == (players.size() - 1)) {
			return players.get(0);
		} else {
			return players.get(index + 1);
		}
	}

}
