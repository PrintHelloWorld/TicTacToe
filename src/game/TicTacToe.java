package game;
import javax.swing.SwingUtilities;

public class TicTacToe {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new GameFrame()); /* Lambda Java 8 version shortcutting an anonymous class, can leave out block statement due to single line expression */
	}
}
