package game;
import java.awt.event.ActionEvent;

/**
 * Listener interface to communicate between board and frame
 * @author Blake
 *
 */
public interface BoardListener {
	public void clickEventOccurred(Board board, ActionEvent e);
}