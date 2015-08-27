package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Game Board panel for player to interface with
 * @author Blake
 *
 */
@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {
	private Font font;
	private JButton[] buttons;
	private BoardListener buttonListener;

	public Board() {
		setBackground(Color.WHITE);
		font = new Font("Times New Roman", Font.PLAIN, 120);
		buttons = new JButton[9];
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setForeground(Color.YELLOW);
			buttons[i].setBackground(Color.DARK_GRAY);
			buttons[i].setFont(font);
			buttons[i].addActionListener(this);
		}
		initComponents();
	}

	/**
	 * add swing components to JPanel
	 */
	private void initComponents() {
		GridLayout gridLayout = new GridLayout(3,3);
		setLayout(gridLayout);
		add(buttons[0]);
		add(buttons[1]);
		add(buttons[2]);
		add(buttons[3]);
		add(buttons[4]);
		add(buttons[5]);
		add(buttons[6]);
		add(buttons[7]);
		add(buttons[8]);
	}
	
	public JButton[] getButtons(){
		return buttons;
	}

	public void setButtonListener(BoardListener buttonListener) {
		this.buttonListener = buttonListener;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		buttonListener.clickEventOccurred(this, e);
	}
}