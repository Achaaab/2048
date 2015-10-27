package fr.guehenneux.puzzle2048;

import java.util.ArrayList;
import java.util.List;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.Player;

/**
 * @author Jonathan Guéhenneux
 */
public abstract class TileMover implements Player {

	protected PuzzleModel puzzle;

	/**
	 * @param puzzle
	 */
	public TileMover(PuzzleModel puzzle) {
		this.puzzle = puzzle;
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		int[] tiles = puzzle.tiles;
		int k;
		boolean emptyTile;
		boolean possibleMove;

		// try left

		k = 0;
		possibleMove = false;
		emptyTile = false;

		while (k != 16 && !possibleMove) {

			emptyTile |= tiles[k] == 0;
			possibleMove = tiles[k + 1] != 0 && (emptyTile || tiles[k] == tiles[k + 1]);

			k++;

			if (k % 4 == 3) {

				k++;
				emptyTile = false;
			}
		}

		if (possibleMove) {
			moves.add(new TileMove(puzzle, Direction.LEFT));
		}

		// try up

		k = 0;
		possibleMove = false;
		emptyTile = false;

		while (k != 15 && !possibleMove) {

			emptyTile |= tiles[k] == 0;
			possibleMove = tiles[k + 4] != 0 && (emptyTile || tiles[k] == tiles[k + 4]);

			k += 4;

			if (k == 12 || k == 13 || k == 14) {

				k -= 11;
				emptyTile = false;
			}
		}

		if (possibleMove) {
			moves.add(new TileMove(puzzle, Direction.UP));
		}

		// try right

		k = 0;
		possibleMove = false;
		emptyTile = false;

		while (k != 16 && !possibleMove) {

			emptyTile |= tiles[k + 1] == 0;
			possibleMove = tiles[k] != 0 && (emptyTile || tiles[k] == tiles[k + 1]);

			k++;

			if (k % 4 == 3) {

				k++;
				emptyTile = false;
			}
		}

		if (possibleMove) {
			moves.add(new TileMove(puzzle, Direction.RIGHT));
		}

		// try down

		k = 0;
		possibleMove = false;
		emptyTile = false;

		while (k != 15 && !possibleMove) {

			emptyTile |= tiles[k + 4] == 0;
			possibleMove = tiles[k] != 0 && (emptyTile || tiles[k] == tiles[k + 4]);

			k += 4;

			if (k == 12 || k == 13 || k == 14) {

				k -= 11;
				emptyTile = false;
			}
		}

		if (possibleMove) {
			moves.add(new TileMove(puzzle, Direction.DOWN));
		}

		return moves;
	}
}