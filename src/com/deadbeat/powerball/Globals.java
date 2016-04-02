package com.deadbeat.powerball;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

class Globals {
	private final boolean DEBUG = true;
	private final int numThreads = 4;
	private final Random rnd = new Random();

	private boolean running = false;
	
	private final int MATCH3_PB = 100;
	private final int MATCH4_PB = 50000;
	private final int MATCH1 = 4;
	private final int MATCH3 = 7;
	private final int MATCH4 = 100;
	private final int MATCH5 = 1000000;
	
	private static AtomicInteger numDraws = new AtomicInteger(0);
	private static AtomicInteger numWins = new AtomicInteger(0);
	private static AtomicInteger amtSpent = new AtomicInteger(0);
	private static AtomicInteger amtWon = new AtomicInteger(0);
	
	private static int pBall;
	private static Set<Integer> ticket = new HashSet<>(5);
	private static boolean wonJackpot = false;
	
	private static PBFrame frame;
	
	// This doesn't belong here really, but it's my app so i don't give a shit
	public void log(String msg) {
		if(this.isDEBUG())
			System.out.println(msg);
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
	
	public static int getNumDraws() {
		return numDraws.get();
	}

	
	public static void incrementDraws() {
		Globals.numDraws.incrementAndGet();
	}

	
	public static int getNumWins() {
		return numWins.get();
	}

	
	public static void incrementWins() {
		Globals.numWins.incrementAndGet();
	}
	
	
	public int getAmtSpent() {
		return amtSpent.get();
	}


	public void addAmtSpent(int amtSpent) {
		Globals.amtSpent.addAndGet(amtSpent);
	}


	public static AtomicInteger getAmtWon() {
		return amtWon;
	}


	public static void addAmtWon(int amtWon) {
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
	
	public static boolean hasWonJackpot() {
		return wonJackpot;
	}

	public static void setWonJackpot(boolean wonJackpot) {
		Globals.wonJackpot = wonJackpot;
	}

	public PBFrame getFrame() {
		return frame;
	}

	public void setFrame(PBFrame frame) {
		Globals.frame = frame;
	}


	
}
