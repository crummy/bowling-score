package com.malcolmcrum.diusbowling;

import java.util.ArrayList;
import java.util.List;

class BowlingGame {
	private List<Frame> frames = new ArrayList<>();

	BowlingGame() {
		frames.add(new StandardFrame());
	}

	int score() {
		return frames.stream()
				.mapToInt(Frame::getScore)
				.sum();
	}

	void roll(int numberOfPins) {
		if (getLatestFrame().isDeliveryAllowed() == false) {
			addFrame();
		}
		getLatestFrame().addDelivery(numberOfPins);
	}

	private void addFrame() {
		if (frames.size() >= 10) {
			throw new GameOverException();
		} else if (frames.size() == 9) {
			frames.add(new TenthFrame());
		} else {
			frames.add(new StandardFrame());
		}
	}

	private Frame getLatestFrame() {
		return frames.get(frames.size() - 1);
	}
}
