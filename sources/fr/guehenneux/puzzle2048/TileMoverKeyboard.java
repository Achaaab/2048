package fr.guehenneux.puzzle2048;

import java.util.List;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMoverKeyboard extends TileMover implements EventHandler<KeyEvent> {

	/**
	 * @param puzzle
	 */
	public TileMoverKeyboard(PuzzleModel puzzle) {
		super(puzzle);
	}

	private Move move;

	@Override
	public synchronized Move getMove() {

		move = null;

		try {
			wait();
		} catch (InterruptedException interruptedException) {
			interruptedException.printStackTrace();
		}

		return move;
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