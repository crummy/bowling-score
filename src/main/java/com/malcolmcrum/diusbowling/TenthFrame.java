package com.malcolmcrum.diusbowling;

import java.util.Optional;

public class TenthFrame implements Frame {
	private Integer firstDelivery = null;
	private Integer secondDelivery = null;
	private Integer thirdDelivery = null;
	private int pinsRemaining = INITIAL_PINS;

	@Override
	public void addDelivery(int pinsKnockedDown) {
		Preconditions.check(pinsRemaining() >= pinsKnockedDown, "Tried to knock down more pins than are standing");
		Preconditions.check(firstDelivery == null || secondDelivery == null || thirdDelivery == null, "Not allowed more than 3 deliveries");
		if (firstDelivery == null) {
			firstDelivery = pinsKnockedDown;
		} else if (secondDelivery == null) {
			secondDelivery = pinsKnockedDown;
		} else if (thirdDelivery == null) {
			thirdDelivery = pinsKnockedDown;
		}
		pinsRemaining -= pinsKnockedDown;
		if (pinsRemaining == 0) {
			pinsRemaining = INITIAL_PINS;
		}
	}

	@Override
	public boolean isAnotherDeliveryAllowed() {
		if (firstDelivery == null) {
			return true;
		} else if (secondDelivery == null) {
			return true;
		} else if (thirdDelivery == null) {
			return firstDelivery == 10 || firstDelivery + secondDelivery == 10;
		} else {
			return false;
		}
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

	public Optional<Integer> getThirdDeliveryScore() {
		return Optional.ofNullable(thirdDelivery);
	}

	@Override
	public int pinsRemaining() {
		return pinsRemaining;
	}

	@Override
	public String toString() {
		String firstScore = firstDelivery != null ? firstDelivery.toString() : "?";
		String secondScore = secondDelivery != null ? secondDelivery.toString() : "?";
		String thirdScore = thirdDelivery != null ? thirdDelivery.toString() : "?";

		return String.format("%s/%s/%s", firstScore, secondScore, thirdScore);
	}
}
