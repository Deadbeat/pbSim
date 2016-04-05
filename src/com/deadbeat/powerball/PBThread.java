package com.deadbeat.powerball;

import java.util.Arrays;

public class PBThread implements Runnable {
	protected static Globals g;
	private static Integer[] rndNumbers = new Integer[5];
	private static int rndPB;
	private static String n = null;
	private static int localDraws = 0;
	
	public PBThread(Globals g) {
		PBThread.g = g;
	}

	private static int generateRandomNumber(int min, int max) {
		int i = g.getRnd().nextInt(max - min + 1) + min;
		return i;
	}

	private static boolean checkNumber(Integer[] in, int num) {
		return Arrays.asList(in).contains(num);
	}

	private static void generateDrawTicket() {
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
		
		g.addAmtSpent(30);
		
	}
	
	@Override
	public void run() {
		n = Thread.currentThread().getName();
		g.log(n + " - Starting");

		while (g.isRunning()) {
			if(localDraws % 10 == 0) {
				generateDrawTicket();
			}
			
			doDraw();
			localDraws++;
			
		}

		g.log(n + " - Stopping");

	}

	private static void doDraw() {
		// TODO: Random roll powerplay modifier and react accordingly
		
		boolean pbMatch = false;
		int matches = 0;

		g.incrementDraws();
		
		// First, check the powerball
		if (g.getpBall() == rndPB) {
			g.log(n + ": Matched PowerBall!");
			pbMatch = true;
		}

		// Next check all numbers for matches
		for (int thisNumber : g.getTicket()) {
			if (checkNumber(rndNumbers, thisNumber)) {
				g.log(n + ": Draw " + g.getNumDraws() + " Matched #: " + thisNumber + " in ticket: " + Arrays.toString(rndNumbers));
				matches++;
			}
		}
		
		/*
		 * Match winnings table
		 * 
		 * Match PB:
		 * 0 or 1 = $4
		 * 2 = $7
		 * 3 = $100
		 * 4 = $50,000
		 * 5 = JACKPOT
		 * 
		 * No PB:
		 * 3 = $7
		 * 4 = $100
		 * 5 = $1,000,000 (count as $2,000,000)
		 * 
		 */
		
		switch(matches) {
		case 1:
			if(pbMatch) 
				g.addAmtWon(4);
			break;
		
		case 2:
			if(pbMatch) 
				g.addAmtWon(7);
			break;
		
		case 3:
			if(pbMatch) {
				g.addAmtWon(100);
			} else {
				g.addAmtWon(7);
			}
			break;
			
		case 4:
			if(pbMatch) {
				g.addAmtWon(50000);
				g.incrementMatch4();
			} else {
				g.addAmtWon(100);
			}
			break;
			
		case 5:
			if(pbMatch) {
				g.setWonJackpot(true);
				g.setRunning(false); // Stop - we win!
			} else {
				g.addAmtWon(2000000);
				g.incrementMatch5();
			}
		}

	}

}
