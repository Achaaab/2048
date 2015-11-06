package fr.guehenneux.puzzle2048;

import fr.guehenneux.alphabeta.DecisionAlgorithm;
import fr.guehenneux.alphabeta.MiniMax;
import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMoverAI extends TileMover {

	private DecisionAlgorithm decisionAlgorithm;

	/**
	 * @param puzzle
	 */
	public TileMoverAI(PuzzleModel puzzle) {

		super(puzzle);

		decisionAlgorithm = new MiniMax(puzzle, 7, false);
	}

	@Override
	public Move getMove() {
		return decisionAlgorithm.getBestMove();
	}
}