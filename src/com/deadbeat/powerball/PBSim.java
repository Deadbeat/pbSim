package com.deadbeat.powerball;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JTextField;

public class PBSim implements Runnable {
	protected static Globals g = new Globals();
	private static PBFrame frame;

	public PBSim() {
		g.log("DEBUG MODE - SPAM, FUCK YEA!");

		// Create and display UI
		g.setFrame(new PBFrame());
		PBSim.frame = g.getFrame();

		g.log("Setting frame visible");
		frame.setVisible(true);
	}

	private static void stopEverything() {
		g.log("---> STOP!!!");
		// frame.btnStartStop.setIcon(new
		// ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Play24.gif")));
		// frame.btnStartStop.setText("Play PowerBall!");
		g.setRunning(false);
	}

	private static void runSimulation() {
		g.log("---> STARTING SIMULATION: Here goes nothin'");

		if (validateUIInput() != true) {
			g.log("Invalid Input");
			return;
		}

		// Reset counters if we just came off a jackpot
		// and are running a second time
		if(g.hasWonJackpot()) {
			g.reset();
			frame.outJackpot.setText("");
		}
		
		g.setRunning(true);

		Thread uiThread = new Thread(new PBUIController(g));
		uiThread.start();
		PBUIController.updatePlayButton();

		Runnable pThread = new PBThread(g);
		Thread list[] = new Thread[g.getNumThreads()];

		// Start threads, stagger them by 5ms for random generator
		for (int i = 0; i < g.getNumThreads(); i++) {
			Thread t = new Thread(pThread);
			list[i] = t;
			t.start();
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// Validate all text fields in the UI contain valid integers
	private static boolean validateUIInput() {
		g.log("Validating Input");
		g.clearTicket(); // Flush out the ticket list just in case we've
							// previously failed

		boolean valid = true;

		// Validate Powerball field
		JTextField pbField = frame.getPbNumPB();
		try {
			int pb = Integer.parseInt(pbField.getText());
			if (pb > 0 && pb < 27) {
				pbField.setBackground(Color.LIGHT_GRAY);
				g.setpBall(pb);
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			pbField.setBackground(Color.RED);
			valid = false;
		}

		// Validate all other fields
		// List of standard number fields
		ArrayList<JTextField> fields = new ArrayList<>();
		fields.add(frame.getPbNum1());
		fields.add(frame.getPbNum2());
		fields.add(frame.getPbNum3());
		fields.add(frame.getPbNum4());
		fields.add(frame.getPbNum5());

		for (JTextField field : fields) {
			try {
				int num = Integer.parseInt(field.getText());
				field.setBackground(Color.LIGHT_GRAY);

				// Check number in valid range
				if (num > 0 && num < 70) {
					// Attempt to add to ticket set (will fail if duplicate)
					if (!g.addTicketNumber(num)) {
						g.log("Duplicate Number : " + num);
						throw new NumberFormatException();
					}
				} else {
					throw new NumberFormatException();
				}
			} catch (NumberFormatException e) {
				// Invalid text found in one of the fields, so red it out
				field.setBackground(Color.RED);
				valid = false;
			}
		}

		return valid;

	}

	protected static void actionButtonClicked() {
		if (g.isRunning()) {
			stopEverything();
		} else {
			// frame.btnStartStop.setIcon(new
			// ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Stop24.gif")));
			// frame.btnStartStop.setText("Stop Playing");
			runSimulation();
		}
	}
	
	protected static void resetButtonClicked() {
		if(!g.isRunning()) {
			frame.outSpent.setText("");
			frame.outWinnings.setText("");
			frame.outTotalDraws.setText("");
			frame.outTimePlayed.setText("");
			frame.outJackpot.setText("");
			frame.outMatch4.setText("");
			frame.outMatch5.setText("");
				
			g.reset();
		}
	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		PBSim sim = new PBSim();
	}

	@Override
	public void run() {
		main(null);
	}

}
