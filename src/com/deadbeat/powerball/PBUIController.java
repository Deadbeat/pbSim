package com.deadbeat.powerball;

import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.ImageIcon;

public class PBUIController implements Runnable {
	protected static Globals g;
	private static PBFrame frame;

	public PBUIController(Globals g) {
		PBUIController.g = g;
		PBUIController.frame = g.getFrame();

	}

	protected static void updatePlayButton() {
		g.log("UI: Updating Play Button - Play = " + g.isRunning());
		if(!g.hasWonJackpot()) {

			if (g.isRunning()) {
				frame.btnStartStop
						.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Stop24.gif")));
				frame.btnStartStop.setText("Stop Playing");
			} else {
				frame.btnStartStop
						.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Play24.gif")));
				frame.btnStartStop.setText("Play PowerBall!");
			}
			
		} else {
			frame.btnStartStop.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Play24.gif")));
			frame.btnStartStop.setText("Play Again");
		}
	}

	private static void updateUI() {

		frame.outSpent.setText("Spent: $" + NumberFormat.getNumberInstance(Locale.US).format(g.getAmtSpent()));
		frame.outWinnings.setText("Winnings: $" + NumberFormat.getNumberInstance(Locale.US).format(g.getAmtWon()));
		frame.outTotalDraws.setText("Total Draws Completed: " + NumberFormat.getNumberInstance().format(g.getNumDraws()));
		
		int played = g.getNumDraws() / 104;
		frame.outTimePlayed.setText("Total Time Played: " + NumberFormat.getNumberInstance().format(played) + " years");
		if(g.hasWonJackpot()) {
			frame.outJackpot.setText("JACKPOT!");
		} 
		frame.outMatch4.setText("Matched 4: " + NumberFormat.getNumberInstance().format(g.getMatch4()));
		frame.outMatch5.setText("Matched 5: " + NumberFormat.getNumberInstance().format(g.getMatch5()));
	}

	@Override
	public void run() {
		while (g.isRunning()) {
			updateUI();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		updateUI(); // One final update when stopping
		updatePlayButton();
	}

}
