package com.ideas.uno.game.player;

import java.util.List;

public class PlayerScoreBorad {

	private final List<Player> players;

	private final int winningPoints;

	public PlayerScoreBorad(final List<Player> players, final int winningPoints) {
		if(winningPoints <= 0){
			throw new IllegalArgumentException("Match points should in greater than 0 : " + winningPoints);
		}
		this.players = players;
		this.winningPoints = winningPoints;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public int getWinningPoints() {
		return winningPoints;
	}
}
