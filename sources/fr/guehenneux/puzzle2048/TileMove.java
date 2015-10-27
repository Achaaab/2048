package fr.guehenneux.puzzle2048;

import java.util.Arrays;

import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMove implements Move {

	private PuzzleModel puzzle;
	private Direction direction;

	private int[] savedTiles;

	/**
	 * @param puzzle
	 * @param direction
	 */
	public TileMove(PuzzleModel puzzle, Direction direction) {

		this.puzzle = puzzle;
		this.direction = direction;
	}

	@Override
	public void play() {

		savedTiles = Arrays.copyOf(puzzle.tiles, 16);

		switch (direction) {

		case WEST:

			for (int i = 0; i < 4; i++) {

			}

			break;

		default:
			break;
		}
	}

	@Override
	public void cancel() {
		// TODO Auto-generated method stub

	}

}