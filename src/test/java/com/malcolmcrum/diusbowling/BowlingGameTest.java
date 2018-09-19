package com.malcolmcrum.diusbowling;

import org.junit.Before;
import org.junit.Test;

import static com.malcolmcrum.diusbowling.Preconditions.*;
import static org.junit.Assert.assertEquals;

public class BowlingGameTest {

	private static int MAX_FRAMES = 10;
	private BowlingGame game;

	@Before
	public void setUp() {
		game = new BowlingGame();
	}

	@Test(expected = GameOverException.class)
	public void tooManyMisses() {
		repeat(22, () -> game.roll(0));
	}

	@Test(expected = GameOverException.class)
	public void tooManyStrikes() {
		repeat(13, () -> game.roll(10));
	}

	@Test
	public void maxScore() {
		repeat(12, () -> game.roll(10));

		int score = game.score();

		assertEquals(300, score);
	}

	@Test
	public void maxScore_missedLastStrike() {
		repeat(11, () -> game.roll(10));
		game.roll(9);

		int score = game.score();

		assertEquals(299, score);
	}

	@Test
	public void maxScore_missedSecondToLastStrike() {
		repeat(10, () -> game.roll(10));
		game.roll(9);
		game.roll(1);

		int score = game.score();

		assertEquals(289, score);
	}

	@Test
	public void maxScore_spareInTenthFrame() {
		repeat(9, () -> game.roll(10));
		game.roll(5);
		game.roll(5);
		game.roll(10);

		int score = game.score();

		assertEquals(275, score);
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
		repeat(4, () -> game.roll(5));

		int score = game.score();

		assertEquals(25, score);
	}

	@Test(expected = PreconditionFailed.class)
	public void impossibleSingleDelivery() {
		game.roll(11);
	}

	@Test(expected = PreconditionFailed.class)
	public void impossibleConsecutiveDelivery() {
		game.roll(5);
		game.roll(6);
	}

	private static void repeat(int times, Runnable runnable) {
		for (int i = 0; i < times; ++i ) {
			runnable.run();
		}
	}

}