package com.ideas.uno.game.player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardType;
import com.ideas.uno.game.executor.Turn;
import com.ideas.uno.game.player.direction.NextDirectionPlayer;
import com.ideas.uno.game.player.direction.NextReverseDirectionPlayer;

/**
 * Manage the player list
 *
 */
public class PlayerManager {

	private List<Player> players;

	public PlayerManager(final Map<String, Integer> playersOfTheGame) {
		// Initialize players of the game
		this.players = playersOfTheGame.entrySet().stream().map(m -> new Player(m.getKey(), m.getValue())).collect(Collectors.toList());
	}

	public List<Player> getGamePlayers() {
		return new ArrayList<>(this.players);
	}

	public Player getFirstPlayer() {
		return players.stream().min(Comparator.comparing(Player::getAge)).get();
	}

	public Turn getPlayerIfNotNumberCard(Card currentCard, Player player, CardManager cardManager) {
		if (CardType.DRAW_TWO.equals(currentCard.getCardType())) {
			player.draw2CardPenalty(cardManager);
			return new Turn(new NextDirectionPlayer(player, this).getNextPlayer(), currentCard);
		} else if (CardType.WILD.equals(currentCard.getCardType())) {
			return new Turn(new NextDirectionPlayer(player, this).getNextPlayer(), player.getNextTrickyCard(cardManager));
		} else if (CardType.WILD_D4.equals(currentCard.getCardType())) {
			player.wild4CardPenalty(cardManager);
			return new Turn(new NextDirectionPlayer(player, this).getNextPlayer(), currentCard);
		} else if (CardType.REVERSE.equals(currentCard.getCardType())) {
			return new Turn(new NextReverseDirectionPlayer(player, this).getNextPlayer(), currentCard);
		} else if (CardType.SKIP.equals(currentCard.getCardType())) {
			return new Turn(new NextDirectionPlayer(player, this).getNextPlayer(), currentCard);
		}
		return new Turn(player, currentCard);

	}

	public void resetCards() {
		this.players.stream().filter(player -> player.reset()).collect(Collectors.toList());
	}

	public void distrubuteCards(CardManager cardManager) {
		this.players.forEach((player) -> cardManager.draw(player, 7));
	}
}
