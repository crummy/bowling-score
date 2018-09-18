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
	public void tooManyMisses() {
		for (int i = 0; i < MAX_FRAMES * 2 + 2; ++i) {
			game.roll(0);
		}
	}

	@Test(expected = GameOverException.class)
	public void tooManyStrikes() {
		for (int i = 0; i < MAX_FRAMES + 1; ++i) {
			game.roll(10);
		}
	}

	@Test
	public void maxScore() {
		for (int i = 0; i < MAX_FRAMES; ++i) {
			game.roll(10);
		}

		int score = game.score();

		assertEquals(300, score);
	}

	@Test
	public void emptyGameScore() {
		int score = game.score();

		assertEquals(0, score);
	}

	@Test
	public void simpleGameScoreNoStrikes() {
		game.roll(1);
		game.roll(2);
		game.roll(3);

		int score = game.score();

		assertEquals(6, score);
	}

	@Test
	public void singleStrikeAddsNextScores() {
		game.roll(10);
		game.roll(1);
		game.roll(2);

		int score = game.score();

		assertEquals(16, score);
	}

	@Test
	public void consecutiveSpares() {
		game.roll(5);
		game.roll(5);
		game.roll(5);
		game.roll(5);

		int score = game.score();

		assertEquals(25, score);
	}

}