package com.ideas.uno.game.player.direction;

import java.util.List;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;

public abstract class DirectionManager {

	protected Player player;

	protected PlayerManager playerManager;

	public DirectionManager(Player player, PlayerManager playerManager) {
		this.player = player;
		this.playerManager = playerManager;
	}

	protected abstract Player getNextPlayer();

	protected int getPlayerCurrentPostion(List<Player> players) {
		return players.indexOf(this.player);
	}
}
