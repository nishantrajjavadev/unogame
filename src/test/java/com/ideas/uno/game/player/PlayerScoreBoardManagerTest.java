package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardType;

@RunWith(MockitoJUnitRunner.class)
public class PlayerScoreBoardManagerTest {

	PlayerScoreBorad playerScoreBoard;
	Player currentTopPlayer;
	PlayerScoreBoardManager boardManager;

	@Before
	public void setUp() {
		playerScoreBoard = Mockito.mock(PlayerScoreBorad.class);
		boardManager = new PlayerScoreBoardManager(playerScoreBoard);
	}

	@Test
	public void shouldReturnTrueIfPlayerScoreEqualToWinningScore() {
		Mockito.when(playerScoreBoard.getPlayers()).thenReturn(
				populatePlayers());
		Mockito.when(playerScoreBoard.getWinningPoints()).thenReturn(500);
		boolean result = boardManager.isGameFinished();
		Assert.assertTrue(result);
	}

	@Test
	public void shouldReturnFalseIfPlayerScoreIsNotEqualToWinningScore() {
		Mockito.when(playerScoreBoard.getPlayers()).thenReturn(
				populatePlayersWithoutHighestScore());
		Mockito.when(playerScoreBoard.getWinningPoints()).thenReturn(500);
		boolean result = boardManager.isGameFinished();
		Assert.assertFalse(result);
	}

	@Test
	public void shouldCalculateScore() {
		currentTopPlayer = Mockito.mock(Player.class);
		Mockito.doNothing().when(currentTopPlayer).addScore(100);
		Mockito.when(playerScoreBoard.getPlayers()).thenReturn(
				populatePlayers());

		PlayerScoreBoardManager spyBoardManager = Mockito.spy(boardManager);
		Mockito.doNothing().when(spyBoardManager).calculateScore();

		spyBoardManager.calculateScore();

		Mockito.verify(spyBoardManager, Mockito.times(1)).calculateScore();
	}

	@Test
	public void shouldReturnCurrentTopPlayer() {
		currentTopPlayer = Mockito.mock(Player.class);
		Mockito.doNothing().when(currentTopPlayer).addScore(100);
		Mockito.when(playerScoreBoard.getPlayers()).thenReturn(
				populatePlayers());
		boardManager.calculateScore();
		Player topPlayer = boardManager.getCurrentTopPlayer();
		Assert.assertTrue(topPlayer != null);
	}

	private List<Player> populatePlayers() {
		Player playerOne = new Player("Nishant", 25);
		playerOne.addScore(100);

		Player playerTwo = new Player("Shivranjani", 20);
		playerTwo.addCard(new Card(CardColor.GREEN, CardType.NUMBER, 9));
		playerTwo.addScore(500);

		List<Player> players = new ArrayList<>();
		players.add(playerOne);
		players.add(playerTwo);
		return players;
	}

	private List<Player> populatePlayersWithoutHighestScore() {
		Player playerOne = new Player("Nishant", 25);
		playerOne.addCard(new Card(CardColor.RED, CardType.WILD, 2));
		playerOne.addScore(100);

		Player playerTwo = new Player("Shivranjani", 20);
		playerTwo.addCard(new Card(CardColor.GREEN, CardType.NUMBER, 9));
		playerTwo.addScore(200);

		List<Player> players = new ArrayList<>();
		players.add(playerOne);
		players.add(playerTwo);
		return players;
	}
}
