package com.malcolmcrum.diusbowling;

import java.util.Random;

public class App {
	private static Random random = new Random();

	public static void main(String... args) {
		BowlingGame bowlingGame = new BowlingGame();
		while (bowlingGame.canStillRoll()) {
			int numberOfPins = random.nextInt(6);
			bowlingGame.roll(numberOfPins);
			System.out.println("Rolled a " + numberOfPins);
		}
		System.out.println("Scorecard: " + bowlingGame.score());
		System.out.println(bowlingGame.getScoreCard());
	}
}
