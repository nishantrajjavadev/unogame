package com.ideas.uno.game.card;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.ideas.uno.game.card.CardColor;

public class CardColorTest {

	@Test
	public void shouldReturnSizeOfEnumValues() {
		Assert.assertTrue(CardColor.values().length == 5);
	}
	
	@Test
	public void shpuldCheckIfValueAvailableInEnum() {
		CardColor[] values = CardColor.values();
		List<String> names = new ArrayList<>();
		for (CardColor cardColor : values) {
			names.add(cardColor.name());
		}
		Assert.assertTrue(names.contains("RED"));
	}
}
