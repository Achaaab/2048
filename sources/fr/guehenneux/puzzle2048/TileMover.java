package fr.guehenneux.puzzle2048;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.Player;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMover implements Player {

	private PuzzleModel puzzle;

	private Move left;
	private Move up;
	private Move right;
	private Move down;

	/**
	 * @param puzzle
	 */
	public TileMover(PuzzleModel puzzle) {

		this.puzzle = puzzle;

		left = new TileMove(puzzle, Direction.LEFT);
		up = new TileMove(puzzle, Direction.UP);
		right = new TileMove(puzzle, Direction.RIGHT);
		down = new TileMove(puzzle, Direction.DOWN);
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		int[] tiles = puzzle.tiles;
		int k;
		boolean possibleMove;

		// try left

		k = 0;
		possibleMove = false;

		while (k != 16 && !possibleMove) {

			possibleMove = tiles[k] == 0 || tiles[k] == tiles[k + 1];

			k++;

			if (k % 4 == 3) {
				k++;
			}
		}

		if (possibleMove) {
			moves.add(left);
		}

		// try up

		k = 0;
		possibleMove = false;

		while (k != 4 && !possibleMove) {

			possibleMove = tiles[k] == 0 || tiles[k] == tiles[k + 4];

			k += 4;

			if (k > 11) {
				k -= 11;
			}
		}

		if (possibleMove) {
			moves.add(up);
		}

		// try right

		k = 0;
		possibleMove = false;

		while (k != 16 && !possibleMove) {

			possibleMove = tiles[k + 1] == 0 || tiles[k] == tiles[k + 1];

			k++;

			if (k % 4 == 3) {
				k++;
			}
		}

		if (possibleMove) {
			moves.add(right);
		}

		// try down

		k = 0;
		possibleMove = false;

		while (k != 4 && !possibleMove) {

			possibleMove = tiles[k + 4] == 0 || tiles[k] == tiles[k + 4];

			k += 4;

			if (k > 11) {
				k -= 11;
			}
		}

		if (possibleMove) {
			moves.add(down);
		}

		return moves;
	}

	@Override
	public Move getMove() {
		return puzzle.getBestMove();
	}
}