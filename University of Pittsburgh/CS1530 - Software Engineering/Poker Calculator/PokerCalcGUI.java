/**
 * The PokerCalcGUI class creates a GUI to allow the user to enter input easily. 
 * This GUI was built using the Eclipse GUI building software. Custom code was added to the event handlers.`
 * @author pokerCalc team
 * Updated 7/6/2015
 */

import java.util.*;
import java.text.NumberFormat;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.JScrollPane;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PokerCalcGUI {

	// HandType translations
	private static final int ROYAL_FLUSH = 1, STRAIGHT_FLUSH = 2, FOUR_OF_A_KIND = 3, FULL_HOUSE = 4, FLUSH = 5, STRAIGHT = 6, THREE_OF_A_KIND = 7, TWO_PAIR = 8, PAIR = 9, HIGH_CARD = 10;
    protected double playerWinPct = 0.0, enemyWinPct = 0.0, tiePct = 0.0;

	//components of the GUI
	private JFrame frmEquityCalculator;
	protected JTextField playerTextField;
	protected JTextField enemyTextField;
	protected JTextField flopTextField1;
	protected JTextField flopTextField2;
	protected JTextField flopTextField3;
	protected JTextField turnTextField;
	private JLabel riverLabel;
	protected JTextField riverTextField;
	private JButton clearButton;
	private JButton calculateButton;
	private JTextArea resultsTextArea;
	private JScrollPane resultsScroll;
	private JButton selectPlayerButton;
	private JButton selectEnemyHand;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PokerCalcGUI window = new PokerCalcGUI();
					window.frmEquityCalculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PokerCalcGUI() {
		initialize();
	}
	
	/**
	 * Function that will reset the text fields/areas
	 */
	protected void resetFields() {
		//reset text fields
		playerTextField.setText("");
		enemyTextField.setText("");
		flopTextField1.setText("");
		flopTextField2.setText("");
		flopTextField3.setText("");
		turnTextField.setText("");
		riverTextField.setText("");
		resultsTextArea.setText("");
		
		//return focus to top of form
		playerTextField.requestFocus();
	}
	
	/**
	 * Displays an error message to the user if the community card string lengths are incorrect
	 */
	protected void displayTableCardError() {
		JOptionPane.showMessageDialog(null, "Please enter valid data for the community cards.", "Input Error", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Validate that the community cards are the correct length.
	 * @return
	 */
	protected int checkFieldLengths() {
		//return values for error or success
		final int ERROR = 0, SUCCESS = 1;
		
		//check that the text fields for community cards have valid lengths
		String flop1 = flopTextField1.getText().trim();
		if(flop1.length() != 2) {
			displayTableCardError();
			
			//highlight text in offending field
			flopTextField1.requestFocus();
			flopTextField1.selectAll();
			return ERROR;
		}
		String flop2 = flopTextField2.getText().trim();
		if(flop2.length() != 2) {
			displayTableCardError();
			
			//highlight text in offending field
			flopTextField2.requestFocus();
			flopTextField2.selectAll();
			return ERROR;
		}
		String flop3 = flopTextField3.getText().trim();
		if(flop3.length() != 2) {
			displayTableCardError();
			
			//highlight text in offending field
			flopTextField3.requestFocus();
			flopTextField3.selectAll();
			return ERROR;
		}
		String turn = turnTextField.getText().trim();
		if(turn.length() != 2 && turn.length() != 0) {
			displayTableCardError();
			
			//highlight text in offending field
			turnTextField.requestFocus();
			turnTextField.selectAll();
			return ERROR;
		}
		String river = riverTextField.getText().trim();
		if(river.length() != 2 && river.length() != 0) {
			displayTableCardError();
			
			//highlight text in offending field
			riverTextField.requestFocus();
			riverTextField.selectAll();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	/**
	 * Display calculations using user input.
	 */
	protected void displayCalculations() {
		//get info from the user and translate
		ArrayList<String> playerHands = Translator.translate(playerTextField.getText());
		ArrayList<String> enemyHands = Translator.translate(enemyTextField.getText());
		
		//get the hole hands for the players
		ArrayList<HoleHand> playerHoleHands = HandGenerator.generateHoleHands(playerHands);
		ArrayList<HoleHand> enemyHoleHands = HandGenerator.generateHoleHands(enemyHands);

		// Store the handtype for easy reference in tiebreaks
		int handT;

		// Kicker values for player and enemy.
		int playerKicker, enemyKicker;

		// Kicker loop bool
		boolean kickerTieBreak;
		
		//get the community cards
		Card flop1 = new Card(flopTextField1.getText());
		Card flop2 = new Card(flopTextField2.getText());
		Card flop3 = new Card(flopTextField3.getText());
		Card turn, river;
		if(turnTextField.getText().length() == 0) {
			turn = null;
		} else {
			turn = new Card(turnTextField.getText());
		}
		if(riverTextField.getText().length() == 0) {
			river = null;
		} else {
			river = new Card(riverTextField.getText());
		}		
		
		//create the poker table
		PokerTable table = new PokerTable(flop1, flop2, flop3, turn, river);
		
		//generate complete hands for the players
		ArrayList<CompleteHand> playerComplete = CompleteHandGenerator.generateHands(playerHoleHands, table);
		ArrayList<CompleteHand> enemyComplete = CompleteHandGenerator.generateHands(enemyHoleHands, table);
		
		//calculate wins, losses, ties
		int playerWins = 0, enemyWins = 0, ties = 0;
		for(CompleteHand player : playerComplete) {
			System.out.println("Player Hand: \n" + player);
			HandVal playerHandVal = new HandVal(player);
			for(CompleteHand enemy : enemyComplete) {
				System.out.println("\t" + enemy);
				HandVal enemyHandVal = new HandVal(enemy);

				// If our hand value is lower than the enemy, we win
				if(playerHandVal.getHandValue() < enemyHandVal.getHandValue()) {
					playerWins++;
				}

				// If enemy handval is lower than ours, they win
				else if(playerHandVal.getHandValue() > enemyHandVal.getHandValue()) {
					enemyWins++;
				}

				// We have the same handval type, determine if we need to perform a tiebreaker.
				else {

					/**
					 * Possible tiebreak scenarios:
					 * 1. One Pair - If highVal match, the strongest kicker wins; tie if all kickers identical, otherwise highVal wins.
					 * 2. Two Pair - If highVal match, compare lowVal; if lowVal match, compare kicker; tie if kicker identical, otherwise highVal wins.
					 * 3. Three of a Kind - If highVal match, tie; otherwise highVal wins.
					 * 4. Straight - If highVal match, tie; otherwise highVal wins.
					 * 5. Flush - If highVal match, compare all kickers; if they all match, compare lowVal, if they match tie, otherwise highVal wins.
					 * 6. Full House - highVal is only consideration.
					 * 7. Four of a Kind - highVal is only consideration.
					 * 8. Straight Flush - highVal is only consideration.
					 * 9. Royal Flush - always split the pot.
					 */

					handT = playerHandVal.getHandValue();

					if(playerHandVal.getHighVal() < enemyHandVal.getHighVal()) {
						playerWins++;
					} else if(playerHandVal.getHighVal() > enemyHandVal.getHighVal()) {
						enemyWins++;
					}

					// Both hands are of the same type, and their high values match: tie break.
					else {
						kickerTieBreak = false;

						// One Pair
						if (handT == PAIR) {

							// Compare all kickers; whenever one is lower than the other, that is a winner.
							while(!kickerTieBreak) {
								playerKicker = playerHandVal.getNextKicker();
								enemyKicker = enemyHandVal.getNextKicker();

								// If we get a -1, we've run out of kickers: tie
								if (playerKicker == -1) {
									ties++;
									kickerTieBreak = true;
								}
								// If the player has the better kicker, player wins
								else if (playerKicker < enemyKicker) {
									playerWins++;
									kickerTieBreak = true;
								}

								// If the enemy has the better kicker, enemy wins
								else if (enemyKicker < playerKicker) {
									enemyWins++;
									kickerTieBreak = true;
								}
							}
						}

						// Two Pair
						else if (handT == TWO_PAIR) {

							// First check the lowVal; the lower valued pair wins.
							if (playerHandVal.getLowVal() < enemyHandVal.getLowVal()) {
								playerWins++;
							}
							else if (enemyHandVal.getLowVal() < playerHandVal.getLowVal()) {
								enemyWins++;
							}
							// We have match high and low vals: tie breaker using the kicker
							else {
								playerKicker = playerHandVal.getNextKicker();
								enemyKicker = enemyHandVal.getNextKicker();

								// If our kicker is lower (higher valued), player wins
								if (playerKicker < enemyKicker) {
									playerWins++;
								}
								// If the enemy kicker is lower, enemy wins
								else if (enemyKicker < playerKicker) {
									enemyWins++;
								}
								// Kicker is equal, we have a tie; split pot.
								else {
									ties++;
								}
							}
						}

						// Flush
						else if (handT == FLUSH) {

							// First we compare the values of the kickers; if they all match we finally compare lowVal
							for (int i = 0; i < 3 && !kickerTieBreak; i++) {
								playerKicker = playerHandVal.getNextKicker();
								enemyKicker = enemyHandVal.getNextKicker();

								// If we have the lower valued kicker, player wins
								if (playerKicker < enemyKicker) {
									playerWins++;
									kickerTieBreak = true;
								}
								// If enemy has the lower valued kicker, enemy wins
								else if (enemyKicker < playerKicker) {
									enemyWins++;
									kickerTieBreak = true;
								}
								// We have matching kicker, keep looping until we're out
							}

							// If found a tiebreaker above, we don't need to check lowVal
							if (!kickerTieBreak) {
								playerKicker = playerHandVal.getLowVal();
								enemyKicker = enemyHandVal.getLowVal();

								// If our lowest card is better, player wins
								if (playerKicker < enemyKicker) {
									playerWins++;
								}
								// If enemy lowest card is better, enemy wins
								else if (enemyKicker < playerKicker) {
									enemyWins++;
								}
								// Every card is a match: tie; split pot.
								else {
									ties++;
								}
							}
						}

						// We don't have a pair, two pair, or flush.
						else {
							ties++;
						}
					}
				}
			}
		}
		
		playerWinPct = playerWins / ((playerWins + enemyWins + ties) * 1.0);
		enemyWinPct = enemyWins / ((playerWins + enemyWins + ties) * 1.0);
		tiePct = ties / ((playerWins + enemyWins + ties) * 1.0);
		
		
		StringBuilder outMessage = new StringBuilder("");
		NumberFormat df = NumberFormat.getPercentInstance();
		df.setMinimumFractionDigits(3);
		outMessage.append("Player's chance to win: " + df.format(playerWinPct));
		outMessage.append("\nEnemy's chance to win: " + df.format(enemyWinPct));
		outMessage.append("\nChance to tie: " + df.format(tiePct));
	
		//display output in textArea
		resultsTextArea.setText(outMessage.toString());
	}
	
	/**
	 * Display card selection grid
	 */
	protected void displayCardSelectionGrid(JTextField field) {
		GridGUI gui = new GridGUI(frmEquityCalculator, field);
		gui.main(new String[1]);
	}

	/**
	 * Initialize the contents of the frame.
	 * This code was generated using the Eclipse GUI builder. 
	 * Custom code was added to the methods above.
	 */
	protected void initialize() {
		frmEquityCalculator = new JFrame();
		frmEquityCalculator.setTitle("Equity Calculator");
		frmEquityCalculator.setResizable(false);
		frmEquityCalculator.setBounds(100, 100, 654, 380);
		frmEquityCalculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmEquityCalculator.getContentPane().setLayout(null);
		
		JLabel playerLabel = new JLabel("Player Hand Range:");
		playerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		playerLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		playerLabel.setBounds(10, 24, 120, 30);
		frmEquityCalculator.getContentPane().add(playerLabel);
		
		JLabel enemyLabel = new JLabel("Enemy Hand Range:");
		enemyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		enemyLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		enemyLabel.setBounds(10, 65, 120, 30);
		frmEquityCalculator.getContentPane().add(enemyLabel);
		
		playerTextField = new JTextField();
		playerTextField.setToolTipText("Enter a hand or range of hands for the player.");
		playerTextField.setBounds(140, 30, 399, 20);
		frmEquityCalculator.getContentPane().add(playerTextField);
		playerTextField.setColumns(10);
		
		enemyTextField = new JTextField();
		enemyTextField.setToolTipText("Enter a hand or range of hands for the enemy.");
		enemyTextField.setColumns(10);
		enemyTextField.setBounds(140, 71, 399, 20);
		frmEquityCalculator.getContentPane().add(enemyTextField);
		
		JLabel flopLabel = new JLabel("Flop Cards:");
		flopLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		flopLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		flopLabel.setBounds(10, 106, 120, 30);
		frmEquityCalculator.getContentPane().add(flopLabel);
		
		flopTextField1 = new JTextField();
		flopTextField1.setToolTipText("Enter the first card of the flop.");
		flopTextField1.setBounds(140, 112, 26, 20);
		frmEquityCalculator.getContentPane().add(flopTextField1);
		flopTextField1.setColumns(10);
		
		flopTextField2 = new JTextField();
		flopTextField2.setToolTipText("Enter the second card of the flop.");
		flopTextField2.setColumns(10);
		flopTextField2.setBounds(176, 112, 26, 20);
		frmEquityCalculator.getContentPane().add(flopTextField2);
		
		flopTextField3 = new JTextField();
		flopTextField3.setToolTipText("Enter the third card of the flop.");
		flopTextField3.setColumns(10);
		flopTextField3.setBounds(212, 112, 26, 20);
		frmEquityCalculator.getContentPane().add(flopTextField3);
		
		JLabel turnLabel = new JLabel("Turn Card:");
		turnLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		turnLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		turnLabel.setBounds(10, 147, 120, 30);
		frmEquityCalculator.getContentPane().add(turnLabel);
		
		turnTextField = new JTextField();
		turnTextField.setToolTipText("Enter the turn card.");
		turnTextField.setColumns(10);
		turnTextField.setBounds(140, 153, 26, 20);
		frmEquityCalculator.getContentPane().add(turnTextField);
		
		riverLabel = new JLabel("River Card:");
		riverLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		riverLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		riverLabel.setBounds(10, 188, 120, 30);
		frmEquityCalculator.getContentPane().add(riverLabel);
		
		riverTextField = new JTextField();
		riverTextField.setToolTipText("Enter the river card.");
		riverTextField.setColumns(10);
		riverTextField.setBounds(140, 194, 26, 20);
		frmEquityCalculator.getContentPane().add(riverTextField);
		
    	resultsTextArea = new JTextArea();
    	resultsTextArea.setBackground(SystemColor.control);
		resultsTextArea.setEditable(false);
		resultsTextArea.setBounds(10, 229, 327, 111);
		resultsScroll = new JScrollPane(resultsTextArea);
		resultsScroll.setBounds(10, 229, 628, 111);		
		resultsScroll.setBorder(new TitledBorder(null, "Results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		frmEquityCalculator.getContentPane().add(resultsScroll);
		
		clearButton = new JButton("Clear");
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				resetFields();
			}
		});
		clearButton.setToolTipText("Clear data fields.");
		clearButton.setBounds(549, 152, 89, 23);
		clearButton.setFocusable(false);
		frmEquityCalculator.getContentPane().add(clearButton);
		
		calculateButton = new JButton("Calculate");
		calculateButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		calculateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//make sure the cards entered for the community cards are not over length
				//exit function if there was an error
				if(checkFieldLengths() == 0)
					return;
				
				//use user input to calculate and display results
				displayCalculations();				
			}
		});
		calculateButton.setBounds(549, 193, 89, 23);
		frmEquityCalculator.getContentPane().add(calculateButton);
		
		selectPlayerButton = new JButton("Select Hand");
		selectPlayerButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		selectPlayerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayCardSelectionGrid(playerTextField);
			}
		});
		selectPlayerButton.setBounds(549, 29, 89, 23);
		frmEquityCalculator.getContentPane().add(selectPlayerButton);
		
		selectEnemyHand = new JButton("Select Hand");
		selectEnemyHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				displayCardSelectionGrid(enemyTextField);
			}
		});
		selectEnemyHand.setFont(new Font("Tahoma", Font.PLAIN, 10));
		selectEnemyHand.setBounds(549, 70, 89, 23);
		frmEquityCalculator.getContentPane().add(selectEnemyHand);
	}
}