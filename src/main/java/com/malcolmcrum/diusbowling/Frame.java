package com.malcolmcrum.diusbowling;

public interface Frame {
	void addDelivery(int numberOfPins);

	boolean isDeliveryAllowed();

	int getScore();
}
