package fr.guehenneux.puzzle2048;

import java.util.Arrays;

import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.ZeroSumGame;

/**
 * @author Jonathan Guéhenneux
 */
public class PuzzleModel extends ZeroSumGame {

	private Player tileGenerator;
	private Player player;
	private Player currentPlayer;

	int[] tiles;

	/**
	 * 
	 */
	public PuzzleModel() {

		tiles = new int[16];

		tileGenerator = new TileCreator(this);
		player = new TileCreator(this);

		currentPlayer = tileGenerator;
	}

	@Override
	public PuzzleModel clone() {

		PuzzleModel clone = new PuzzleModel();
		clone.tiles = Arrays.copyOf(tiles, 16);
		return clone;
	}

	@Override
	public double getHeuristicValue(Player player) {

		int previousTile = tiles[0];
		int currentTile;
		int k = 1;
		int dk = 1;
		int streak = 0;
		boolean streakEnd = false;

		// snake streak : 0, 1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 15, 14

		while (k < 16 && !streakEnd) {

			currentTile = tiles[k];

			if (currentTile <= previousTile) {

				streak += previousTile;

				if (k % 4 == 3) {

					k += 4;
					dk = -dk;

				} else {

					k += dk;
				}

				previousTile = currentTile;

			} else {

				streakEnd = true;
			}
		}

		if (player == tileGenerator) {
			streak = -streak;
		}

		return streak;
	}

	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public void nextPlayer() {

		if (currentPlayer == tileGenerator) {
			currentPlayer = player;
		} else {
			currentPlayer = tileGenerator;
		}
	}

	@Override
	public void previousPlayer() {

		if (currentPlayer == tileGenerator) {
			currentPlayer = player;
		} else {
			currentPlayer = tileGenerator;
		}
	}

	@Override
	public Player getWinner() {
		return null;
	}

	@Override
	public double getVictoryValue() {
		return Double.MAX_VALUE;
	}
}