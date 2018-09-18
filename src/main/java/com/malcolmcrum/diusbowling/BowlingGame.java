package com.malcolmcrum.diusbowling;

import java.util.ArrayList;
import java.util.List;

class BowlingGame {
	private List<Frame> frames = new ArrayList<>();

	BowlingGame() {
		frames.add(new StandardFrame());
	}

	int score() {
		int score = 0;
		for (int i = 0; i < frames.size(); ++i) {
			Frame frame = frames.get(i);
			if (frame.isStrike()) {
				score += 10 + strikeBonus(i);
			} else if (frame.isSpare()) {
				score += 10 + spareBonus(i);
			} else {
				score += frame.getFirstDeliveryScore().orElse(0) + frame.getSecondDeliveryScore().orElse(0);
			}
		}
		return score;
	}

	private int spareBonus(int frameIndex) {
		if (frames.size() > frameIndex + 1) {
			Frame nextFrame = frames.get(frameIndex + 1);
			return nextFrame.getFirstDeliveryScore().orElse(0);
		} else {
			return 0;
		}
	}

	private int strikeBonus(int frameIndex) {
		int bonus = 0;
		if (frames.size() > frameIndex + 1) {
			Frame nextFrame = frames.get(frameIndex + 1);
			bonus += nextFrame.getFirstDeliveryScore().orElse(0);
			if (nextFrame.isStrike() && frames.size() > frameIndex + 2) {
				Frame frameAfterNext = frames.get(frameIndex + 2);
				bonus += frameAfterNext.getFirstDeliveryScore().orElse(0);
			} else {
				bonus += nextFrame.getSecondDeliveryScore().orElse(0);
			}
		}
		return bonus;
	}

	void roll(int numberOfPins) {
		if (getLatestFrame().isAnotherDeliveryAllowed() == false) {
			addFrame();
		}
		getLatestFrame().addDelivery(numberOfPins);
	}

	private void addFrame() {
		if (frames.size() >= 10) {
			throw new GameOverException();
		} else {
			frames.add(new StandardFrame());
		}
	}

	private Frame getLatestFrame() {
		return frames.get(frames.size() - 1);
	}
}
