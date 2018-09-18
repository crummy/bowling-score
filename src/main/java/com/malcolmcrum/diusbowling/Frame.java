package com.malcolmcrum.diusbowling;

public interface Frame {
	int INITIAL_PINS = 10;

	void addDelivery(int numberOfPins);

	boolean isAnotherDeliveryAllowed();

	int getScore();
}
