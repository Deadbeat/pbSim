package com.deadbeat.powerball;

import java.util.Arrays;

public class PBThread implements Runnable {
	protected static Globals g;
	private static Integer[] rndNumbers = new Integer[5];
	private static int rndPB;
	private static String n = null;

	public PBThread(Globals g) {
		PBThread.g = g;
	}

	private int generateRandomNumber(int min, int max) {
		int i = g.getRnd().nextInt(max - min + 1) + min;
		return i;
	}

	private static boolean checkNumber(Integer[] in, int num) {
		return Arrays.asList(in).contains(num);
	}

	@Override
	public void run() {
		n = Thread.currentThread().getName();
		g.log(n + " - Starting");

		// Generate 5 random numbers for the main ticket
		for (int i = 0; i < 5; i++) {
			int r = generateRandomNumber(1, 69);

			/*
			 * Check generated number and make sure it's unique If not -
			 * generate a new one until we get a unique number
			 */
			while (checkNumber(rndNumbers, r)) {
				g.log(n + " : Non-unique number: " + r + ". Generating new pick");
				r = generateRandomNumber(1, 69);
			}

			rndNumbers[i] = r;
		}

		// Additional number for the power ball
		rndPB = generateRandomNumber(1, 26);

		g.log(n + " generated ticket: " + rndNumbers[0] + ", " + rndNumbers[1] + ", " + rndNumbers[2] + ", "
				+ rndNumbers[3] + ", " + rndNumbers[4] + " PB: " + rndPB);

		while (g.isRunning()) {
			doDraw();
		}

		g.log(n + " - Stopping");

	}

	private static void doDraw() {
		boolean pbMatch = false;
		int matches = 0;

		// First, check the powerball
		if (g.getpBall() == rndPB) {
			g.log(n + ": Matched PowerBall!");
			pbMatch = true;
		}

		// Next check all numbers for matches
		for (int thisNumber : g.getTicket()) {
			if (checkNumber(rndNumbers, thisNumber)) {
				g.log(n + ": Matched #: " + thisNumber);
				matches++;
			}
		}

	}

}
