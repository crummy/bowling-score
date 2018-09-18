package com.malcolmcrum.diusbowling;

import java.util.ArrayList;
import java.util.List;

public class StandardFrame implements Frame {
	private List<Integer> deliveries = new ArrayList<>();

	@Override
	public void addDelivery(int pinsKnockedDown) {
		Preconditions.check(pinsRemaining() >= pinsKnockedDown, "Tried to knock down more pins than are standing");
		Preconditions.check(deliveries.size() < 2, "Not allowed more than 2 deliveries");
		deliveries.add(pinsKnockedDown);
	}

	@Override
	public boolean isAnotherDeliveryAllowed() {
		return pinsRemaining() > 0 && deliveries.size() <= 1;
	}

	private int pinsRemaining() {
		return INITIAL_PINS - deliveries.stream().mapToInt(i -> i).sum();
	}

	@Override
	public int getScore() {
		return INITIAL_PINS - 10;
	}
}
