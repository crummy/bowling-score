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
		for (int frame = 0; frame < frames.size(); ++frame) {
			if (frame < 9) {
				score += calculateStandardFrameScore(frame);
			} else if (frame == 9) {
				score += calculateTenthFrameScore();
			}
		}
		return score;
	}

	private int calculateStandardFrameScore(int frameIndex) {
		StandardFrame frame = (StandardFrame)frames.get(frameIndex);
		if (frame.isStrike()) {
			return 10 + strikeBonus(frameIndex);
		} else if (frame.isSpare()) {
			return 10 + spareBonus(frameIndex);
		} else {
			return frame.getFirstDeliveryScore().orElse(0) + frame.getSecondDeliveryScore().orElse(0);
		}
	}

	private int calculateTenthFrameScore() {
		TenthFrame frame = (TenthFrame)frames.get(9);
		return frame.getFirstDeliveryScore().orElse(0) +
				frame.getSecondDeliveryScore().orElse(0) +
				frame.getThirdDeliveryScore().orElse(0);
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
		} else if (frames.size() == 9) {
			frames.add(new TenthFrame());
		} else {
			frames.add(new StandardFrame());
		}
	}

	private Frame getLatestFrame() {
		return frames.get(frames.size() - 1);
	}

	String getScoreCard() {
		String prettyScore = "";
		for (int frame = 0; frame < frames.size(); ++frame) {
			prettyScore += "Frame " + (frame + 1) + ": " + frames.get(frame) + "\n";
		}
		return prettyScore;
	}

	boolean canStillRoll() {
		return frames.size() < 10 || frames.get(9).isAnotherDeliveryAllowed();
	}
}
