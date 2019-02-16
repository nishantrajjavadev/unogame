package com.ideas.uno.game.card;

import java.util.Collections;
import java.util.Stack;

/**
 *  Draw Pile list observer
 */
public class UpdateDrawPile implements DrawPileObserver {

	private Stack<Card> drawPile;

	private Stack<Card> discardPile;

	public UpdateDrawPile(Stack<Card> drawPile, Stack<Card> discardPile) {
		this.drawPile = drawPile;
		this.discardPile = discardPile;
	}

	@Override
	public void updateDrawPile() {
		while (true) {
			if (discardPile.size() == 0) {
				break;
			}
			drawPile.push(discardPile.pop());
		}
		Collections.shuffle(drawPile);
	}
}
