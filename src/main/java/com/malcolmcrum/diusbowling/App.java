package com.malcolmcrum.diusbowling;

public class App {
	public static void main(String... args) {
		BowlingGame bowlingGame = new BowlingGame();
		bowlingGame.roll(1);
		bowlingGame.score();
	}
}
