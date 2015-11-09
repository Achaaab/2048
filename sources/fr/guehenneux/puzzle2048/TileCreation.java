package fr.guehenneux.puzzle2048;

import fr.guehenneux.alphabeta.AbstractMove;

/**
 * @author Jonathan Guéhenneux
 */
public class TileCreation extends AbstractMove {

	private PuzzleModel puzzle;
	private int k;
	private int value;

	/**
	 * @param puzzle
	 * @param k
	 * @param value
	 */
	public TileCreation(PuzzleModel puzzle, int k, int value) {

		super(puzzle);

		this.puzzle = puzzle;
		this.k = k;
		this.value = value;
	}

	@Override
	public void play() {

		puzzle.tiles[k] = value;

		super.play();
	}

	@Override
	public void cancel() {

		puzzle.tiles[k] = 0;

		super.cancel();
	}

	/**
	 * 
	 */
	public String toString() {
		return value + " in " + k;
	}
}