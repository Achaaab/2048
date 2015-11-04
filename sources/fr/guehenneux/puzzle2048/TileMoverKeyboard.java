package fr.guehenneux.puzzle2048;

import java.util.List;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMoverKeyboard extends TileMover implements EventHandler<KeyEvent> {

	private Move move;
	private boolean listening;

	/**
	 * @param puzzle
	 */
	public TileMoverKeyboard(PuzzleModel puzzle) {

		super(puzzle);

		listening = false;
	}

	@Override
	public synchronized Move getMove() {

		if (!listening) {
			Platform.runLater(() -> listen());
		}

		move = null;

		try {
			wait();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}

		return move;
	}

	/**
	 * 
	 */
	private void listen() {

		PuzzleUI gui = puzzle.getGui();
		gui.setOnKeyPressed(this);
		gui.requestFocus();
		listening = true;
	}

	@Override
	public synchronized void handle(KeyEvent event) {

		KeyCode keyCode = event.getCode();

		Direction direction;

		switch (keyCode) {

		case LEFT:
			direction = Direction.LEFT;
			break;

		case UP:
			direction = Direction.UP;
			break;

		case RIGHT:
			direction = Direction.RIGHT;
			break;

		case DOWN:
			direction = Direction.DOWN;
			break;

		default:
			direction = null;
			break;
		}

		List<Move> moves = getMoves();

		TileMove tileMove;

		for (Move move : moves) {

			tileMove = (TileMove) move;

			if (tileMove.getDirection() == direction) {
				this.move = tileMove;
			}
		}

		if (move != null) {
			notify();
		}
	}
}