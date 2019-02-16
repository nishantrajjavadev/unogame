package com.ideas.uno.game.executor;

public class GameManager {

	private Game game;

	public GameManager(Game game) {
		this.game = game;
	}

	public void play() {
		this.game.play();
	}
	
	public void loadGame() {
		this.game.loadGame();
	}
}
