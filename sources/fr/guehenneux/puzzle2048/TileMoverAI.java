package fr.guehenneux.puzzle2048;

import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMoverAI extends TileMover {

	/**
	 * @param puzzle
	 */
	public TileMoverAI(PuzzleModel puzzle) {
		super(puzzle);
	}

	@Override
	public Move getMove() {
		return puzzle.getBestMove();
	}
}