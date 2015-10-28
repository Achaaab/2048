package fr.guehenneux.puzzle2048;

import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileCreation implements Move {

	private PuzzleModel puzzle;
	private int k;
	private int value;

	/**
	 * @param puzzle
	 * @param k
	 * @param value
	 */
	public TileCreation(PuzzleModel puzzle, int k, int value) {

		this.puzzle = puzzle;
		this.k = k;
		this.value = value;
	}

	@Override
	public void play() {

		puzzle.tiles[k] = value;
		puzzle.nextPlayer();
	}

	@Override
	public void cancel() {

		puzzle.tiles[k] = 0;
		puzzle.previousPlayer();
	}

	/**
	 * 
	 */
	public String toString() {
		return value + " in " + k;
	}
}