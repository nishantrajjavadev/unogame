package com.ideas.uno.game.card;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class CardTypeTest {

	@Test
	public void shouldReturnSizeOfEnumValues() {
		Assert.assertTrue(CardType.values().length == 6);
	}
	
	@Test
	public void shpuldCheckIfValueAvailableInEnum() {
		CardType[] values = CardType.values();
		List<String> names = new ArrayList<>();
		for (CardType cardType : values) {
			names.add(cardType.name());
		}
		Assert.assertTrue(names.contains("SKIP"));
	}


}
