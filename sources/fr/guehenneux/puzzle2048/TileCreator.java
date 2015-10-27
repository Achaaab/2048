package fr.guehenneux.puzzle2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.Player;

/**
 * @author Jonathan Guéhenneux
 */
public class TileCreator implements Player {

	private static final Random RANDOM = new Random();

	private PuzzleModel puzzle;

	/**
	 * @param puzzle
	 */
	public TileCreator(PuzzleModel puzzle) {
		this.puzzle = puzzle;
	}

	@Override
	public List<Move> getMoves() {

		List<Move> moves = new ArrayList<>();

		for (int k = 0; k < 16; k++) {

			if (puzzle.tiles[k] == 0) {

				moves.add(new TileCreation(puzzle, k, 1));
				moves.add(new TileCreation(puzzle, k, 2));
			}
		}

		return moves;
	}

	@Override
	public Move getMove() {

		List<Integer> positions = new ArrayList<>();

		int k;

		for (k = 0; k < 16; k++) {

			if (puzzle.tiles[k] == 0) {
				positions.add(k);
			}
		}

		k = positions.get(RANDOM.nextInt(positions.size()));

		int value = RANDOM.nextInt(10) == 0 ? 2 : 1;

		return new TileCreation(puzzle, k, value);
	}
}