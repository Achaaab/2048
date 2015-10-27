package fr.guehenneux.puzzle2048;

import java.util.Arrays;

import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.ZeroSumGame;

/**
 * @author Jonathan Guéhenneux
 */
public class PuzzleModel extends ZeroSumGame {

	private TileCreator tileCreator;
	private TileMover tileMover;
	private Player currentPlayer;

	private PuzzleView view;

	int[] tiles;

	/**
	 * 
	 */
	public PuzzleModel() {

		tiles = new int[16];

		tileCreator = new TileCreator(this);
		tileMover = new TileMoverKeyboard(this);

		currentPlayer = tileCreator;

		view = null;
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

		if (player == tileCreator) {
			streak = -streak;
		}

		return streak;
	}

	@Override
	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * @return
	 */
	public TileMover getTileMoverKeyBoard() {
		return tileMover;
	}

	@Override
	public void nextPlayer() {

		if (currentPlayer == tileCreator) {
			currentPlayer = tileMover;
		} else {
			currentPlayer = tileCreator;
		}
	}

	@Override
	public void previousPlayer() {

		if (currentPlayer == tileCreator) {
			currentPlayer = tileMover;
		} else {
			currentPlayer = tileCreator;
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

	@Override
	public void updateView() {

		if (view != null) {
			view.update();
		}
	}

	/**
	 * @param view
	 */
	public void setView(PuzzleView view) {
		this.view = view;
	}
}