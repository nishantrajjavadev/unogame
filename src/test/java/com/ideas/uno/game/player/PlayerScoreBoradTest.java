package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerScoreBorad;

public class PlayerScoreBoradTest {

	private PlayerScoreBorad playerScoreBorad;
	private PlayerTrickManageFactory trickManagerFactory;

	@Test
	public void shouldReturnListOfPlayers() {
		trickManagerFactory = new PlayerTrickManagerFactoryImpl();
		List<Player> players = populatePlayers();
		playerScoreBorad = new PlayerScoreBorad(players, 500);
		List<Player> actualList = playerScoreBorad.getPlayers();
		Assert.assertEquals(players, actualList);
		Assert.assertEquals(players.size(), actualList.size());
	}

	@Test
	public void shouldReturnNullWhenNullPlayersSet() {
		playerScoreBorad = new PlayerScoreBorad(null, 500);
		List<Player> actualList = playerScoreBorad.getPlayers();
		Assert.assertEquals(null, actualList);
	}

	@Test
	public void shouldReturnEmptyListIfEmpltyPlayersSet() {
		List<Player> players = new ArrayList<>();
		playerScoreBorad = new PlayerScoreBorad(players, 500);
		List<Player> actualList = playerScoreBorad.getPlayers();
		Assert.assertEquals(players, actualList);
		Assert.assertEquals(players.size(), actualList.size());
	}

	@Test
	public void shouldGetWinningScore() {
		List<Player> players = populatePlayers();
		playerScoreBorad = new PlayerScoreBorad(players, 500);
		Assert.assertEquals(500, playerScoreBorad.getWinningPoints());
	}

	private List<Player> populatePlayers() {
		Player playerOne = new Player("Nishant", 25, trickManagerFactory);
		playerOne.addScore(100);

		Player playerTwo = new Player("Shivranjani", 20, trickManagerFactory);
		playerTwo.addCard(new Card(CardColor.GREEN, CardType.NUMBER, 9));
		playerTwo.addScore(500);

		List<Player> players = new ArrayList<>();
		players.add(playerOne);
		players.add(playerTwo);
		return players;
	}
}
