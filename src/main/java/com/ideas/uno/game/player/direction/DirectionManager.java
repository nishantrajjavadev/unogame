package com.ideas.uno.game.player.direction;

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

	protected int getPlayerCurrentPostion() {
		return playerManager.getGamePlayers().indexOf(this.player);
	}
}
