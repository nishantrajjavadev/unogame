package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.MapUtils;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.direction.DirectionManager;
import com.ideas.uno.game.player.direction.DirectionManagerFactoryImpl;

public class PlayerManagerImpl implements PlayerManager{

	private List<Player> players;

	private final Map<String, Integer> playersOfTheGame;
	
	private final PlayerTrickManageFactory trickManageFactory;
	
	public PlayerManagerImpl(final Map<String, Integer> playersOfTheGame, PlayerTrickManageFactory trickManageFactory) {
		this.playersOfTheGame = playersOfTheGame;
		this.trickManageFactory = trickManageFactory;
	}

	@Override
	public List<Player> getGamePlayers() {
		return new ArrayList<>(this.players);
	}

	@Override
	public Player getFirstPlayer() {
		return players.stream().min(Comparator.comparing(Player::getAge)).get();
	}

	@Override
	public Turn getFirstPlayerIfNotNumberCard(Card currentCard, Player player, CardManager cardManager) {
		CardType cardType = currentCard.getCardType();
		if(CardType.NUMBER.equals(cardType)){
			return new Turn(player, currentCard);
		}
		if (CardType.DRAW_TWO.equals(cardType)) {
			player.draw2CardPenalty(cardManager);
		} 
		DirectionManager directionManager = new DirectionManagerFactoryImpl().getDirection(cardType);
		return new Turn(directionManager.getNextPlayer(this, player), currentCard);

	}

	@Override
	public void resetCards() {
		this.players.stream().filter(Player::reset).collect(Collectors.toList());
	}

	@Override
	public void distrubuteCards(CardManager cardManager) {
		this.players.forEach(player -> cardManager.draw(player, 7));
	}
	// Initialize players of the game
	@Override
	public void loadPlayer() {
		if(MapUtils.isEmpty(playersOfTheGame) || (playersOfTheGame.size() < 2 || playersOfTheGame.size() > 10)){
			throw new IllegalArgumentException("Players details not correct");
		}
		this.players = playersOfTheGame.entrySet().stream().map(m -> {
			if(m.getValue() < 7){
				throw new IllegalArgumentException(m.getKey() + " , You are to too small to play this game ");
			}
			return new Player(m.getKey(), m.getValue(), trickManageFactory);
		}).collect(Collectors.toList());
		
	}
}
