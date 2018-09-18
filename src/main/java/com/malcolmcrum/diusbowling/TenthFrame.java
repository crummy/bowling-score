package com.malcolmcrum.diusbowling;

import java.util.ArrayList;
import java.util.List;

public class TenthFrame implements Frame {
	private List<Integer> deliveries = new ArrayList<>();

	@Override
	public void addDelivery(int pinsKnockedDown) {
		Preconditions.check(pinsRemaining() >= pinsKnockedDown, "Tried to knock down more pins than are standing");
		Preconditions.check(deliveries.size() < 3, "Not allowed more than 3 deliveries");
		deliveries.add(pinsKnockedDown);
	}

	@Override
	public boolean isAnotherDeliveryAllowed() {
		if (deliveries.isEmpty()) {
			return true;
		} else if (deliveries.get(0) == 10) {
			return true;
		} else {
			// TODO
			return false;
		}
	}

	private int pinsRemaining() {
		return deliveries.stream().mapToInt(i -> i).sum();
	}

	@Override
	public int getScore() {
		return INITIAL_PINS - 10;
	}
}
