package com.deadbeat.powerball;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

@SuppressWarnings("serial")
public class PBFrame extends JFrame {
	private JTextField pbNum1;
	private JTextField pbNum2;
	private JTextField pbNum3;
	private JTextField pbNum4;
	private JTextField pbNum5;
	private JTextField pbNumPB;
	
	protected JLabel outTotalDraws = new JLabel("");
	protected JLabel outTimePlayed = new JLabel("");
	protected JLabel outSpent = new JLabel("");
	protected JLabel outWinnings = new JLabel("");
	protected JLabel outJackpot = new JLabel();
	
	protected JButton btnStartStop = new JButton();
	
	public PBFrame() {
		setResizable(false);
		setSize(800,480);
		setTitle("PowerBall Player");
		setFont(new Font("Arial", Font.PLAIN, 12));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel appTitleLabel = new JLabel("PowerBall Simulator");
		appTitleLabel.setBounds(6, 6, 390, 63);
		appTitleLabel.setFont(new Font("Arial Black", Font.PLAIN, 30));
		appTitleLabel.setVerticalAlignment(SwingConstants.TOP);
		appTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		getContentPane().add(appTitleLabel);
		
		JLabel lblEnterYourChosen = new JLabel("Enter Your Chosen Numbers");
		lblEnterYourChosen.setFont(new Font("Arial", Font.PLAIN, 15));
		lblEnterYourChosen.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourChosen.setBounds(240, 69, 318, 16);
		getContentPane().add(lblEnterYourChosen);
		
		JLabel label = new JLabel("#1");
		label.setFont(new Font("Arial", Font.PLAIN, 13));
		label.setBounds(101, 96, 16, 16);
		getContentPane().add(label);
		
		pbNum1 = new JTextField();
		pbNum1.setBackground(Color.LIGHT_GRAY);
		pbNum1.setHorizontalAlignment(SwingConstants.CENTER);
		pbNum1.setFont(new Font("Arial Black", Font.PLAIN, 22));
		pbNum1.setBounds(80, 109, 58, 54);
		pbNum1.setColumns(2);
		getContentPane().add(pbNum1);
		pbNum1.setDocument(new JTextFieldLimit(2));
		
		JLabel label_1 = new JLabel("#2");
		label_1.setFont(new Font("Arial", Font.PLAIN, 13));
		label_1.setBounds(199, 96, 16, 16);
		getContentPane().add(label_1);
		
		pbNum2 = new JTextField();
		pbNum2.setHorizontalAlignment(SwingConstants.CENTER);
		pbNum2.setFont(new Font("Arial Black", Font.PLAIN, 22));
		pbNum2.setColumns(2);
		pbNum2.setBackground(Color.LIGHT_GRAY);
		pbNum2.setBounds(178, 109, 58, 54);
		getContentPane().add(pbNum2);
		pbNum2.setDocument(new JTextFieldLimit(2));
		
		JLabel label_2 = new JLabel("#3");
		label_2.setFont(new Font("Arial", Font.PLAIN, 13));
		label_2.setBounds(292, 96, 16, 16);
		getContentPane().add(label_2);
		
		pbNum3 = new JTextField();
		pbNum3.setHorizontalAlignment(SwingConstants.CENTER);
		pbNum3.setFont(new Font("Arial Black", Font.PLAIN, 22));
		pbNum3.setColumns(2);
		pbNum3.setBackground(Color.LIGHT_GRAY);
		pbNum3.setBounds(272, 109, 58, 54);
		getContentPane().add(pbNum3);
		pbNum3.setDocument(new JTextFieldLimit(2));
		
		JLabel label_3 = new JLabel("#4");
		label_3.setFont(new Font("Arial", Font.PLAIN, 13));
		label_3.setBounds(386, 96, 16, 16);
		getContentPane().add(label_3);
		
		pbNum4 = new JTextField();
		pbNum4.setHorizontalAlignment(SwingConstants.CENTER);
		pbNum4.setFont(new Font("Arial Black", Font.PLAIN, 22));
		pbNum4.setColumns(2);
		pbNum4.setBackground(Color.LIGHT_GRAY);
		pbNum4.setBounds(364, 109, 58, 54);
		getContentPane().add(pbNum4);
		pbNum4.setDocument(new JTextFieldLimit(2));
		
		JLabel label_4 = new JLabel("#5");
		label_4.setFont(new Font("Arial", Font.PLAIN, 13));
		label_4.setBounds(481, 96, 16, 16);
		getContentPane().add(label_4);
		
		pbNum5 = new JTextField();
		pbNum5.setHorizontalAlignment(SwingConstants.CENTER);
		pbNum5.setFont(new Font("Arial Black", Font.PLAIN, 22));
		pbNum5.setColumns(2);
		pbNum5.setBackground(Color.LIGHT_GRAY);
		pbNum5.setBounds(461, 109, 58, 54);
		getContentPane().add(pbNum5);
		pbNum5.setDocument(new JTextFieldLimit(2));
		
		JLabel lblPowerball = new JLabel("PowerBall");
		lblPowerball.setFont(new Font("Arial", Font.PLAIN, 13));
		lblPowerball.setHorizontalAlignment(SwingConstants.CENTER);
		lblPowerball.setBounds(622, 96, 80, 16);
		getContentPane().add(lblPowerball);
		
		pbNumPB = new JTextField();
		pbNumPB.setHorizontalAlignment(SwingConstants.CENTER);
		pbNumPB.setFont(new Font("Arial Black", Font.PLAIN, 22));
		pbNumPB.setColumns(2);
		pbNumPB.setBackground(Color.YELLOW);
		pbNumPB.setBounds(634, 109, 58, 54);
		getContentPane().add(pbNumPB);
		pbNumPB.setDocument(new JTextFieldLimit(2));
		
//		JButton btnStartStop = new JButton("Play PowerBall!");
		btnStartStop.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnStartStop.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/media/Play24.gif")));
		btnStartStop.setText("Play PowerBall!");
		btnStartStop.addActionListener(new ActionListener(){

		    public void actionPerformed(ActionEvent e)
		    {
		        PBSim.actionButtonClicked(); 
		    }
		});
		btnStartStop.setBounds(64, 383, 185, 54);
		getContentPane().add(btnStartStop);
		
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Arial Black", Font.PLAIN, 15));
		btnReset.setIcon(new ImageIcon(PBFrame.class.getResource("/toolbarButtonGraphics/general/Refresh24.gif")));
		btnReset.setBounds(540, 383, 185, 54);
		getContentPane().add(btnReset);
		
//		public JLabel outTotalDraws = new JLabel("");
		outTotalDraws.setBounds(55, 258, 275, 29);
		getContentPane().add(outTotalDraws);
		
//		JLabel outTimePlayed = new JLabel("");
		outTimePlayed.setBounds(55, 289, 275, 29);
		getContentPane().add(outTimePlayed);
		
//		JLabel outSpent = new JLabel("");
		outSpent.setBounds(417, 258, 275, 29);
		getContentPane().add(outSpent);
		
//		JLabel outWinnings = new JLabel("");
		outWinnings.setBounds(417, 289, 275, 29);
		getContentPane().add(outWinnings);
		
//		JLabel outJackpot = new JLabel();
		outJackpot.setEnabled(false);
		outJackpot.setForeground(Color.GREEN);
		outJackpot.setHorizontalAlignment(SwingConstants.CENTER);
		outJackpot.setFont(new Font("Arial Black", Font.PLAIN, 40));
		outJackpot.setBounds(178, 175, 460, 71);
		getContentPane().add(outJackpot);
//		outJackpot.setText("JACKPOT!");
		
	}
	
	class JTextFieldLimit extends PlainDocument {
	  private int limit;
	  // optional uppercase conversion
	  private boolean toUppercase = false;
	  
	  JTextFieldLimit(int limit) {
	   super();
	   this.limit = limit;
	   }
	   
	  JTextFieldLimit(int limit, boolean upper) {
	   super();
	   this.limit = limit;
	   toUppercase = upper;
	   }
	 
	  public void insertString
	    (int offset, String  str, AttributeSet attr)
	      throws BadLocationException {
	   if (str == null) return;

	   if ((getLength() + str.length()) <= limit) {
	     if (toUppercase) str = str.toUpperCase();
	     super.insertString(offset, str, attr);
	     }
	   }
	}


	public JTextField getPbNum1() {
		return pbNum1;
	}


	public JTextField getPbNum2() {
		return pbNum2;
	}


	public JTextField getPbNum3() {
		return pbNum3;
	}


	public JTextField getPbNum4() {
		return pbNum4;
	}


	public JTextField getPbNum5() {
		return pbNum5;
	}


	public JTextField getPbNumPB() {
		return pbNumPB;
	}
}
