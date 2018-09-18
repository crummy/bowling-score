package com.malcolmcrum.diusbowling;

public class StandardFrame implements Frame {
	private int deliveries = 0;
	private int pinsRemaining = INITIAL_PINS;

	@Override
	public void addDelivery(int numberOfPins) {
		Preconditions.check(pinsRemaining >= numberOfPins, "Tried to knock down more pins than are standing");
		Preconditions.check(deliveries < 2, "Not allowed more than 2 deliveries");
		pinsRemaining -= numberOfPins;
		deliveries++;
	}

	@Override
	public boolean isAnotherDeliveryAllowed() {
		return pinsRemaining >= 0 && deliveries <= 1;
	}

	@Override
	public int getScore() {
		return INITIAL_PINS - 10;
	}
}
