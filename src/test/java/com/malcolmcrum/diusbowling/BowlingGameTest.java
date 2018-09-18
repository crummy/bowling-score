package com.malcolmcrum.diusbowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BowlingGameTest {

	private static int MAX_FRAMES = 10;
	private BowlingGame game;

	@Before
	public void setUp() {
		game = new BowlingGame();
	}

	@Test(expected = GameOverException.class)
	public void testTooManyRolls() {
		for (int i = 0; i < MAX_FRAMES * 2 + 1; ++i) {
			game.roll(0);
		}
	}

}