package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.player.Player;
import com.ideas.uno.game.player.PlayerManager;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;

public class NextDirectionPlayerTest {

	private NextDirectionPlayer nextDirectionPlayer;

	private PlayerTrickManageFactory trickManagerFactory;
	
	@Before
	public void setUp(){
		nextDirectionPlayer = new NextDirectionPlayer();
		trickManagerFactory = new PlayerTrickManagerFactoryImpl();
	}
	@Test
	public void shouldReturnNextPlayerAsPerIterationWhenAnyPositionPlayerSearchedFromListOfPlayers() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		int position = 2;
		List<Player> players = populatePlayers();
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextDirectionPlayer.getNextPlayer(playerManager, players.get(position));
		Assert.assertTrue(nextPlayer.equals(players.get(position + 1)));
		Assert.assertEquals(nextPlayer.getName(), players.get(position + 1)
				.getName());
		Assert.assertEquals(nextPlayer.getAge(), players.get(position + 1)
				.getAge());
		Assert.assertEquals(nextPlayer.getCards(), players.get(position + 1)
				.getCards());
	}

	@Test
	public void shouldReturnNextPlayerAsFirstPlayerWhenLastPlayerSearchedFromListOfPlayers() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		List<Player> players = populatePlayers();
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextDirectionPlayer.getNextPlayer(playerManager, players.get(players
				.size() - 1));
		Assert.assertTrue(nextPlayer.equals(players.get(0)));
		Assert.assertEquals(nextPlayer.getName(), players.get(0).getName());
		Assert.assertEquals(nextPlayer.getAge(), players.get(0).getAge());
		Assert.assertEquals(nextPlayer.getCards(), players.get(0).getCards());
	}

	@Test
	public void shouldReturnNextPlayerWhenFristPlayerSearchedFromListOfPlayers() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		int position = 0;
		List<Player> players = populatePlayers();
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextDirectionPlayer.getNextPlayer(playerManager, players.get(position));
		Assert.assertTrue(nextPlayer.equals(players.get(position + 1)));
		Assert.assertEquals(nextPlayer.getName(), players.get(position + 1)
				.getName());
		Assert.assertEquals(nextPlayer.getAge(), players.get(position + 1)
				.getAge());
		Assert.assertEquals(nextPlayer.getCards(), players.get(position + 1)
				.getCards());
	}

	@Test
	public void shoudReturnFirstPlayerFromListIfSearchedPlayerIsNotInList() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		List<Player> players = populatePlayers();
		Player playerOne = new Player("PLAYER_1", 30, trickManagerFactory);
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextDirectionPlayer.getNextPlayer(playerManager, playerOne);
		Assert.assertTrue(nextPlayer.equals(players.get(0)));
		Assert.assertEquals(nextPlayer.getName(), players.get(0).getName());
		Assert.assertEquals(nextPlayer.getAge(), players.get(0).getAge());
		Assert.assertEquals(nextPlayer.getCards(), players.get(0).getCards());
	}

	private List<Player> populatePlayers() {
		List<Player> players = new ArrayList<>();
		players.add(new Player("Nishant", 25, trickManagerFactory));
		players.add(new Player("Shivranjani", 24, trickManagerFactory));
		players.add(new Player("Priya", 18, trickManagerFactory));
		players.add(new Player("Saurabh", 20, trickManagerFactory));
		return players;
	}
}
