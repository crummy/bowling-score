package com.malcolmcrum.diusbowling;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StandardFrameTest {

	private Frame frame;

	@Before
	public void setUp() {
		frame = new StandardFrame();
	}

	@Test
	public void canDeliverWhenEmpty() {
		boolean canDeliver = frame.isAnotherDeliveryAllowed();

		assertTrue(canDeliver);
	}

	@Test
	public void canDeliverAfterOneMiss() {
		frame.addDelivery(0);

		boolean canDeliver = frame.isAnotherDeliveryAllowed();

		assertTrue(canDeliver);
	}

	@Test
	public void canNotDeliverAfterTwoMisses() {
		frame.addDelivery(0);
		frame.addDelivery(0);

		boolean canDeliver = frame.isAnotherDeliveryAllowed();

		assertFalse(canDeliver);
	}

	@Test
	public void canNotDeliverAfterStrike() {
		frame.addDelivery(10);

		boolean canDeliver = frame.isAnotherDeliveryAllowed();

		assertFalse(canDeliver);
	}

	@Test
	public void canNotDeliverAfterSpare() {
		frame.addDelivery(5);
		frame.addDelivery(5);

		boolean canDeliver = frame.isAnotherDeliveryAllowed();

		assertFalse(canDeliver);
	}
}