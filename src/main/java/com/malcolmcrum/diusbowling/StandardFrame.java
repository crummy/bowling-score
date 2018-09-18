package com.malcolmcrum.diusbowling;

import java.util.Optional;

public class StandardFrame implements Frame {
	private Integer firstDelivery = null;
	private Integer secondDelivery = null;

	@Override
	public void addDelivery(int pinsKnockedDown) {
		Preconditions.check(pinsRemaining() >= pinsKnockedDown, "Tried to knock down more pins than are standing");
		Preconditions.check(firstDelivery == null || secondDelivery == null, "Not allowed more than 2 deliveries");
		if (firstDelivery == null) {
			firstDelivery = pinsKnockedDown;
		} else {
			secondDelivery = pinsKnockedDown;
		}
	}

	@Override
	public boolean isAnotherDeliveryAllowed() {
		return pinsRemaining() > 0 && secondDelivery == null;
	}

	private int pinsRemaining() {
		return INITIAL_PINS - getFirstDeliveryScore().orElse(0) - getSecondDeliveryScore().orElse(0);
	}

	@Override
	public boolean isStrike() {
		return firstDelivery != null && firstDelivery == 10;
	}

	@Override
	public boolean isSpare() {
		return !isStrike() && secondDelivery != null && firstDelivery + secondDelivery == 10;
	}

	@Override
	public Optional<Integer> getFirstDeliveryScore() {
		return Optional.ofNullable(firstDelivery);
	}

	@Override
	public Optional<Integer> getSecondDeliveryScore() {
		return Optional.ofNullable(secondDelivery);
	}

	@Override
	public String toString() {
		String firstScore = firstDelivery != null ? firstDelivery.toString() : "?";
		String secondScore = secondDelivery != null ? secondDelivery.toString() : "?";
		return String.format("%s/%s", firstScore, secondScore);
	}
}
