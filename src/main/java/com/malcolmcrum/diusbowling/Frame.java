package com.malcolmcrum.diusbowling;

import java.util.Optional;

public interface Frame {
	int INITIAL_PINS = 10;

	void addDelivery(int numberOfPins);

	boolean isAnotherDeliveryAllowed();

	boolean isStrike();

	boolean isSpare();

	Optional<Integer> getFirstDeliveryScore();

	Optional<Integer> getSecondDeliveryScore();

	default int pinsRemaining() {
		return INITIAL_PINS - getFirstDeliveryScore().orElse(0) - getSecondDeliveryScore().orElse(0);
	}
}
