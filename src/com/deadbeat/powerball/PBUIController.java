package com.deadbeat.powerball;

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
		if (g.isRunning()) {
			frame.btnStartStop
					.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Stop24.gif")));
			frame.btnStartStop.setText("Stop Playing");
		} else {
			frame.btnStartStop
					.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Play24.gif")));
			frame.btnStartStop.setText("Play PowerBall!");
		}
	}

	private static void updateUI() {

		frame.outSpent.setText("Spent: " + g.getAmtSpent());
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

		updatePlayButton();
	}
}
