package fr.guehenneux.puzzle2048;

import fr.guehenneux.alphabeta.DecisionAlgorithm;
import fr.guehenneux.alphabeta.Move;
import fr.guehenneux.alphabeta.PrincipalVariationSearch;

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

		decisionAlgorithm = new PrincipalVariationSearch(puzzle, 8);
	}

	@Override
	public Move getMove() {
		return decisionAlgorithm.getBestMove();
	}
}