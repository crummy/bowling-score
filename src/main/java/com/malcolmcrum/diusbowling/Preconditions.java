package com.malcolmcrum.diusbowling;

class Preconditions {
	static void check(boolean check, String reason) {
		if (check == false) {
			throw new PreconditionFailed(reason);
		}
	}

	static class PreconditionFailed extends RuntimeException {

		public PreconditionFailed(String reason) {
			super(reason);
		}
	}
}
