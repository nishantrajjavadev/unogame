package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.player.direction.NextReverseDirectionPlayer;

public class NextReverseDirectionPlayerTest {

	NextReverseDirectionPlayer nextReverseDirectionPlayer;

	private PlayerTrickManageFactory trickManagerFactory;
	
	@Before
	public void setUp(){
		nextReverseDirectionPlayer = new NextReverseDirectionPlayer();
		trickManagerFactory = new PlayerTrickManagerFactoryImpl();
	}
	
	@Test
	public void shouldReturnPreviousPlayerAsPerIterationWhenAnyPositionPlayerSearchedFromListOfPlayers() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		int position = 2;
		List<Player> players = populatePlayers();
		System.out.println(players.toString());
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextReverseDirectionPlayer.getNextPlayer(playerManager, players.get(position));
		Collections.reverse(players);
		System.out.println(nextPlayer);
		Assert.assertTrue(nextPlayer.equals(players.get(position - 1)));
		Assert.assertEquals(nextPlayer.getName(), players.get(position - 1)
				.getName());
		Assert.assertEquals(nextPlayer.getAge(), players.get(position - 1)
				.getAge());
		Assert.assertEquals(nextPlayer.getCards(), players.get(position - 1)
				.getCards());
	}

	@Test
	public void shouldReturnPreviousPlayerAsFirstPlayerWhenLastPlayerSearchedFromListOfPlayers() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		List<Player> players = populatePlayers();
		int position = players.size() - 1;
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextReverseDirectionPlayer.getNextPlayer(playerManager, players.get(position));
		Collections.reverse(players);
		Assert.assertTrue(nextPlayer.equals(players.get(position - 1)));
		Assert.assertEquals(nextPlayer.getName(), players.get(position - 1)
				.getName());
		Assert.assertEquals(nextPlayer.getAge(), players.get(position - 1)
				.getAge());
		Assert.assertEquals(nextPlayer.getCards(), players.get(position - 1)
				.getCards());
	}

	@Test
	public void shouldReturnPlayerWhenFirstPlayerSearchedFromListOfPlayers() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		int position = 0;
		List<Player> players = populatePlayers();
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player nextPlayer = nextReverseDirectionPlayer.getNextPlayer(playerManager, players.get(position));
		Collections.reverse(players);
		Assert.assertTrue(nextPlayer.equals(players.get(players.size() - 1)));
		Assert.assertEquals(nextPlayer.getName(),
				players.get(players.size() - 1).getName());
		Assert.assertEquals(nextPlayer.getAge(), players
				.get(players.size() - 1).getAge());
		Assert.assertEquals(nextPlayer.getCards(),
				players.get(players.size() - 1).getCards());
	}

	@Test
	public void shoudReturnPlayerFromListIfSearchedPlayerIsNotInList() {
		PlayerManager playerManager = Mockito.mock(PlayerManager.class);
		List<Player> players = populatePlayers();
		Mockito.when(playerManager.getGamePlayers()).thenReturn(players);
		Player playerOne = new Player("Ramsi", 30,trickManagerFactory);
		Player nextPlayer = nextReverseDirectionPlayer.getNextPlayer(playerManager, playerOne);
		Collections.reverse(players);
		Assert.assertTrue(nextPlayer.equals(players.get(players.size() - 1)));
		Assert.assertEquals(nextPlayer.getName(),
				players.get(players.size() - 1).getName());
		Assert.assertEquals(nextPlayer.getAge(), players
				.get(players.size() - 1).getAge());
		Assert.assertEquals(nextPlayer.getCards(),
				players.get(players.size() - 1).getCards());
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
