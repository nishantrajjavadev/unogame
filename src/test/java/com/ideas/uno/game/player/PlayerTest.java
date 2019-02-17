package com.ideas.uno.game.player;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ideas.uno.game.card.Card;
import com.ideas.uno.game.card.CardColor;
import com.ideas.uno.game.card.CardDeck;
import com.ideas.uno.game.card.CardManager;
import com.ideas.uno.game.card.CardManagerImpl;
import com.ideas.uno.game.card.CardType;

public class PlayerTest {

	Player player;

	CardManager cardManager;
	private CardDeck cardDeck;
	private PlayerTrickManageFactory trickManagerFactory;

	@Before
	public void setUp() {
		trickManagerFactory = new PlayerTrickManagerFactoryImpl();
		cardManager = Mockito.mock(CardManagerImpl.class);
		player = new Player("Nishant", 25, trickManagerFactory);
		cardDeck = CardDeck.getInstance();
	}

	@Test
	public void shouldAddCardSuccessfully() {
		Card card = new Card(CardColor.BLUE, CardType.DRAW_TWO, 1);
		player.addCard(card);
		Assert.assertTrue(player.getCards().size() == 1);
		Assert.assertTrue(player.getCards().get(0).equals(card));
	}

	@Test
	public void shouldRemoveCardSuccessfully() {
		Card card = new Card(CardColor.RED, CardType.DRAW_TWO, 1);
		player.addCard(card);
		player.removeCards(card);
		Assert.assertTrue(player.getCards().size() == 0);
	}

	@Test(expected = UnsupportedOperationException.class)
	public void shouldThrowExceptionWhenNewElementAddedToUnModifiyableList() {
		Card card = new Card(CardColor.BLUE, CardType.DRAW_TWO, 1);
		player.getCards().add(card);
	}

	@Test
	public void shouldAddScoreSuccessfully() {
		player.addScore(100);
		Assert.assertTrue(player.getScore() == 100);
	}

	@Test
	public void shouldCallWild4CardPenalty() {
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		player.wild4CardPenalty(this.cardManager);
		Assert.assertTrue(player.getCards().size() == 4);
	}

	@Test
	public void shouldCallDraw2CardPenalty() {
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		player.draw2CardPenalty(cardManager);
		Assert.assertTrue(player.getCards().size() == 2);
	}
	
	@Test
	public void shouldCallReturnNullIfCurrent() {
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card cardNull = player.turn(null, cardManager);
		Assert.assertNull(cardNull);
	}
	
	@Test
	public void shouldCallNullIfNOCardLeftToPlayer() {
		Card playerCard = new Card(CardColor.RED, CardType.NUMBER, 1);
		Card inputCard = new Card(CardColor.RED, CardType.NUMBER, 2);
		player.addCard(playerCard);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.turn(inputCard, cardManager);
		Assert.assertNull(returnCard);
		Assert.assertTrue(player.getCards().size() == 0);
	}
	
	@Test
	public void shouldCallNotReturnNullShouldReturnPlayerCard() {
		Card playerCard1 = new Card(CardColor.RED, CardType.NUMBER, 1);
		Card playerCard2 = new Card(CardColor.BLUE, CardType.NUMBER, 1);
		Card inputCard = new Card(CardColor.RED, CardType.NUMBER, 2);
		player.addCard(playerCard1);
		player.addCard(playerCard2);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.turn(inputCard, cardManager);
		Assert.assertEquals(playerCard1.getCardColors(), returnCard.getCardColors());
		Assert.assertEquals(playerCard1.getCardType(), returnCard.getCardType());
		Assert.assertTrue(playerCard1.getNumber() == returnCard.getNumber());
		Assert.assertNotNull(returnCard);
		Assert.assertTrue(player.getCards().size() == 1);
	}
	
	@Test
	public void shouldNotMatchTheCardandReturnNewdeckCard() {
		Card playerCard1 = new Card(CardColor.RED, CardType.NUMBER, 1);
		Card inputCard = new Card(CardColor.BLUE, CardType.NUMBER, 2);
		Card pushDoDeck = new Card(CardColor.YELLOW, CardType.NUMBER, 5);
		player.addCard(playerCard1);
		cardDeck.getDrawPile().push(pushDoDeck);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.turn(inputCard, cardManager);
		cardDeck.getDrawPile().pop();
		Assert.assertEquals(inputCard.getCardColors(), returnCard.getCardColors());
		Assert.assertEquals(inputCard.getCardType(), returnCard.getCardType());
		Assert.assertTrue(inputCard.getNumber() == returnCard.getNumber());
		Assert.assertNotNull(returnCard);
		Assert.assertTrue(player.getCards().size() == 2);
	}
	
	@Test
	public void shouldNotMatchTheCardandWildFROMDECK() {
		Card playerCard1 = new Card(CardColor.RED, CardType.NUMBER, 1);
		Card inputCard = new Card(CardColor.BLUE, CardType.NUMBER, 2);
		Card pushDoDeck = new Card(CardColor.NONE, CardType.WILD, 50);
		player.addCard(playerCard1);
		cardDeck.getDrawPile().push(pushDoDeck);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.turn(inputCard, cardManager);
		cardDeck.getDrawPile().pop();
		Assert.assertEquals(pushDoDeck.getCardColors(), returnCard.getCardColors());
		Assert.assertEquals(pushDoDeck.getCardType(), returnCard.getCardType());
		Assert.assertTrue(pushDoDeck.getNumber() == returnCard.getNumber());
		Assert.assertNotNull(returnCard);
		Assert.assertTrue(player.getCards().size() == 1);
	}
	
	@Test
	public void shouldNotMatchTheCardandWild4DECKCARD() {
		Card playerCard1 = new Card(CardColor.RED, CardType.NUMBER, 1);
		Card inputCard = new Card(CardColor.BLUE, CardType.NUMBER, 2);
		Card pushDoDeck = new Card(CardColor.NONE, CardType.WILD_D4, 50);
		player.addCard(playerCard1);
		cardDeck.getDrawPile().push(pushDoDeck);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.turn(inputCard, cardManager);
		cardDeck.getDrawPile().pop();
		Assert.assertEquals(pushDoDeck.getCardColors(), returnCard.getCardColors());
		Assert.assertEquals(pushDoDeck.getCardType(), returnCard.getCardType());
		Assert.assertTrue(pushDoDeck.getNumber() == returnCard.getNumber());
		Assert.assertNotNull(returnCard);
		Assert.assertTrue(player.getCards().size() == 1);
	}
	
	@Test
	public void shouldReturnNextTrickyCard() {
		Card playerCard1 = new Card(CardColor.NONE, CardType.WILD_D4, 50);
		player.addCard(playerCard1);
		player.addCard(playerCard1);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.getNextTrickyCard(cardManager);
		Assert.assertNotNull(returnCard);
		Assert.assertEquals(playerCard1.getCardColors(), returnCard.getCardColors());
		Assert.assertEquals(playerCard1.getCardType(), returnCard.getCardType());
		Assert.assertTrue(playerCard1.getNumber() == returnCard.getNumber());
		Assert.assertTrue(player.getCards().size() == 1);
	}
	
	@Test
	public void shouldReturnNullIfPLayerHaveOneCard() {
		Card playerCard1 = new Card(CardColor.NONE, CardType.WILD_D4, 50);
		player.addCard(playerCard1);
		Mockito.when(cardManager.getCardDeck()).thenReturn(cardDeck);
		Card returnCard = player.getNextTrickyCard(cardManager);
		Assert.assertNull(returnCard);
		Assert.assertTrue(player.getCards().size() == 0);
	}

}
