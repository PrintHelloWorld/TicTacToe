package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	private static final String TITLE = "TicTacToe";
	private static final int WIDTH = 250;
	private static final int HEIGHT = 250;
	private int xWins, oWins;
	
	private String player = "x";

	private Board board;

	public GameFrame() {
		board = new Board();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMinimumSize(new Dimension(400, 400));
		JOptionPane.showMessageDialog(GameFrame.this, "Welcome to TicTacToe!",
				"TicTacToe", JOptionPane.INFORMATION_MESSAGE);
		board.setButtonListener(new BoardListener() {
			@Override
			public void clickEventOccurred(Board board, ActionEvent e) {
				JButton[] buttons = board.getButtons();
				for (int i = 0; i < buttons.length; i++) {
					/* find clicked button */
					if (buttons[i].equals(e.getSource())) {
						if (buttons[i].getText().isEmpty()) {
							/* button has been clicked */
							placeMarker(buttons[i]);
							if (checkIfWin(buttons)) {
								incrementScore();
								JOptionPane.showMessageDialog(GameFrame.this,
										player + " won!\n\n# of 'X' wins: " + xWins
												+ "\n# of 'O' wins: " + oWins,
										"Game Won",
										JOptionPane.INFORMATION_MESSAGE);
								resetGame(buttons);
							}
							if (checkIfTie(buttons)) {
								JOptionPane.showMessageDialog(GameFrame.this,
										"Tie!\n\n# of 'X' wins: " + xWins
												+ "\n# of 'O' wins: " + oWins,
										"Game Tie",
										JOptionPane.INFORMATION_MESSAGE);
								resetGame(buttons);
							} else {
								/* Next turn */
								if (player.equals("x")) {
									player = "o";
								} else {
									player = "x";
								}
							}
						}
					}
				}
			}
		});
		initComponents();
		initLayout();
	}
	
	/**
	 * Places marker of current player on button
	 * @param button
	 */
	private void placeMarker(JButton button){
		if (player.equals("x")) {
			button.setForeground(Color.YELLOW);
			button.setText("x");
		} else if (player.equals("o")) {
			button.setForeground(Color.CYAN);
			button.setText("o");
		}
	}

	/**
	 * checks vertical & horizontal & diagonal lines for matches of 3
	 * 
	 * @param buttons
	 * @return if the game has been won
	 */
	private boolean checkIfWin(JButton[] buttons) {
		return (checkLineMatch(buttons[0], buttons[1], buttons[2])
				|| checkLineMatch(buttons[3], buttons[4], buttons[5])
				|| checkLineMatch(buttons[6], buttons[7], buttons[8])
				|| checkLineMatch(buttons[0], buttons[3], buttons[6])
				|| checkLineMatch(buttons[1], buttons[4], buttons[7])
				|| checkLineMatch(buttons[2], buttons[5], buttons[8])
				|| checkLineMatch(buttons[0], buttons[4], buttons[8]) || checkLineMatch(
					buttons[2], buttons[4], buttons[6]));
	}

	/**
	 * checks 3 buttons match
	 * 
	 * @param firstButton
	 * @param secondButton
	 * @param thirdButton
	 * @return if match or not
	 */
	private boolean checkLineMatch(JButton firstButton, JButton secondButton,
			JButton thirdButton) {
		return (firstButton.getText().equals(player)
				&& secondButton.getText().equals(player) && thirdButton
				.getText().equals(player));
	}

	/**
	 * If there is no empty buttons, then the game is a tie because of checking
	 * if it is a win first.
	 * 
	 * @param buttons
	 * @return boolean if it a tie or not
	 */
	private boolean checkIfTie(JButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i].getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Updates current players score by one
	 */
	private void incrementScore() {
		if (player.equals("x")) {
			xWins++;
		} else if (player.equals("o")) {
			oWins++;
		}
	}

	/**
	 * resets game back to it's initial state
	 * 
	 * @param buttons
	 */
	private void resetGame(JButton[] buttons) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setText("");
		}
		player = "x";
	}

	private void initComponents() {
		add(board);
	}

	private void initLayout() {
		setTitle(TITLE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(GameFrame.this);
		setVisible(true);
	}
}