package com.deadbeat.powerball;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class Globals {
	private final boolean DEBUG = false;
	private final int numThreads = 2;
	private final Random rnd = new Random();

	private boolean running = false;
	
	private static AtomicInteger numDraws = new AtomicInteger(0);
	private static AtomicInteger numWins = new AtomicInteger(0);
	private static BigInteger amtSpent = BigInteger.valueOf(0);
	private static AtomicInteger amtWon = new AtomicInteger(0);
	private static AtomicInteger match4 = new AtomicInteger(0);
	private static AtomicInteger match5 = new AtomicInteger(0);
	
	private static int pBall;
	private static Set<Integer> ticket = new HashSet<>(5);
	private static boolean wonJackpot = false;
	
	private static PBFrame frame;
	
	// This doesn't belong here really, but it's my app so i don't give a shit
	public void log(String msg) {
		if(this.isDEBUG())
			System.out.println(msg);
	}
	
	public void reset() {
		running = false;
		
		numDraws.set(0);
		numWins.set(0);
		amtSpent = BigInteger.valueOf(0);
		amtWon.set(0);
		match4.set(0);
		match5.set(0);
		
		wonJackpot = false;
	}
	public boolean isDEBUG() {
		return DEBUG;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public int getNumThreads() {
		return numThreads;
	}

	public Random getRnd() {
		return rnd;
	}
	
	public int getNumDraws() {
		return numDraws.get();
	}

	public void incrementMatch4() {
		Globals.match4.incrementAndGet();
	}

	
	public int getMatch4() {
		return match4.get();
	}

	public void incrementMatch5() {
		Globals.match5.incrementAndGet();
	}

	
	public int getMatch5() {
		return match5.get();
	}

	
	public void incrementDraws() {
		Globals.numDraws.incrementAndGet();
	}

	
	public static int getNumWins() {
		return numWins.get();
	}

	
	public static void incrementWins() {
		Globals.numWins.incrementAndGet();
	}
	
	
	public int getAmtSpent() {
		return amtSpent.intValue();
	}


	public void addAmtSpent(int amtSpent) {
		Globals.amtSpent = Globals.amtSpent.add(BigInteger.valueOf(amtSpent));
	}


	public AtomicInteger getAmtWon() {
		return amtWon;
	}


	public void addAmtWon(int amtWon) {
		Globals.amtWon.addAndGet(amtWon);
	}

	public int getpBall() {
		return pBall;
	}

	public void setpBall(int pBall) {
		Globals.pBall = pBall;
	}

	public boolean addTicketNumber(int in) {
		return ticket.add(in);
	}
	
	public Set<Integer> getTicket() {
		return ticket;
	}
	
	public void clearTicket() {
		ticket.clear();
	}
	
	public boolean hasWonJackpot() {
		return wonJackpot;
	}

	public void setWonJackpot(boolean wonJackpot) {
		Globals.wonJackpot = wonJackpot;
	}

	public PBFrame getFrame() {
		return frame;
	}

	public void setFrame(PBFrame frame) {
		Globals.frame = frame;
	}


	
}
