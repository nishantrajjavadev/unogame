package com.ideas.uno.game.card;

import java.util.Stack;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class UpdateDrawPileTest {

	
	UpdateDrawPile updateDrawPile;
	Stack<Card> drawPile;
	Stack<Card> discardPile;
	
	@Before
	public void init() {
		
		drawPile = populateDrawPile();
		discardPile = populateDiscardPile();
		updateDrawPile = new UpdateDrawPile(drawPile, discardPile);
	}
	
	private Stack<Card> populateDiscardPile() {
		Stack<Card> discardPile = new Stack<>();
		discardPile.push(new Card(CardColor.BLUE, CardType.WILD, 1));
		discardPile.push(new Card(CardColor.RED, CardType.SKIP, 1));
		discardPile.push(new Card(CardColor.GREEN, CardType.WILD, 1));
		discardPile.push(new Card(CardColor.YELLOW, CardType.SKIP, 1));
		return discardPile;
	}

	@Test
	public void shouldInvokeUpdateDrawPile() {
		discardPile = new Stack<>();
		updateDrawPile = new UpdateDrawPile(drawPile, discardPile);
		UpdateDrawPile updatePile = Mockito.spy(updateDrawPile);

	     // mock a call with an expected input
	     Mockito.doNothing().when(updatePile).updateDrawPile();

	     updatePile.updateDrawPile();

	     // test that there was a call
	     Mockito.verify(updatePile, Mockito.times(1)).updateDrawPile();
		
	}
	
	private Stack<Card> populateDrawPile() {
		Stack<Card> drawPile = new Stack<>();
		drawPile.push(new Card(CardColor.BLUE, CardType.NUMBER, 1));
		drawPile.push(new Card(CardColor.RED, CardType.NUMBER, 1));
		drawPile.push(new Card(CardColor.GREEN, CardType.NUMBER, 1));
		drawPile.push(new Card(CardColor.YELLOW, CardType.NUMBER, 1));
		return drawPile;
	}
}
