package fr.guehenneux.puzzle2048;

import java.util.Arrays;

import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.TwoPlayersZeroSumGame;

/**
 * @author Jonathan Guéhenneux
 */
public class PuzzleModel extends TwoPlayersZeroSumGame {

	private static final int[] SNAKE = { 0, 1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 15, 14, 13, 12 };
	private static final int[] WEIGHTS = { 8, 2, 2, 8, 2, 1, 1, 2, 2, 1, 1, 2, 8, 2, 2, 8 };

	private PuzzleGui gui;

	int[] tiles;

	/**
	 * 
	 */
	public PuzzleModel() {

		tiles = new int[16];

		player0 = new TileCreator(this);
		player1 = new TileMoverKeyboard(this);

		player0.getMove().play();
		player0.getMove().play();

		gui = null;
	}

	@Override
	public double getWinningMoveValue() {
		return Double.MAX_VALUE;
	}

	@Override
	public double getHeuristicValue(Player player) {

		double heuristicValue = (getWeightedTileTotal() + 2 * getSmoothness()) / (16 - getEmptyTileCount());

		if (player == player0) {
			heuristicValue = -heuristicValue;
		}

		return heuristicValue;
	}

	/**
	 * @return
	 */
	private int getTileTotal() {

		int tileTotal = 0;

		for (int k = 0; k < 16; k++) {
			tileTotal += 1 << tiles[k];
		}

		return tileTotal;
	}

	/**
	 * @return
	 */
	private int getWeightedTileTotal() {

		int weightedTileTotal = 0;

		for (int k = 0; k < 16; k++) {
			weightedTileTotal += WEIGHTS[k] << tiles[k];
		}

		return weightedTileTotal;
	}

	/**
	 * @return
	 */
	private int getSnakeCount() {

		int snakeCount = 0;

		int k0, k1;
		int t0, t1;

		int s = 0;

		boolean snakeEnded = false;

		while (s < 15 && !snakeEnded) {

			k0 = SNAKE[s];
			k1 = SNAKE[++s];

			t0 = tiles[k0];
			t1 = tiles[k1];

			if (t0 == 0) {

				snakeEnded = true;

			} else if (t0 >= t1) {

				snakeCount += t0;

			} else {

				snakeCount -= t1;
				snakeEnded = true;
			}
		}

		return snakeCount;
	}

	/**
	 * @return
	 */
	private int getEmptyTileCount() {

		int emptyTileCount = 0;

		for (int k = 0; k < 16; k++) {
			if (tiles[k] == 0) {
				emptyTileCount++;
			}
		}

		return emptyTileCount;
	}

	/**
	 * @return
	 */
	private double getSmoothness() {

		int smoothness = 0;

		int tile;
		int rightTile;
		int downTile;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {

				tile = tiles[4 * i + j];
				rightTile = tiles[4 * i + j + 1];

				smoothness -= 1 << Math.abs(tile - rightTile);

				tile = tiles[4 * j + i];
				downTile = tiles[4 * j + 4 + i];

				smoothness -= 1 << Math.abs(tile - downTile);
			}
		}

		return smoothness;
	}

	@Override
	public Player getWinner() {

		Player winner;

		if (player1.getMoves().isEmpty()) {
			winner = player0;
		} else {
			winner = null;
		}

		return winner;
	}

	@Override
	public boolean isDraw() {
		return false;
	}

	@Override
	public void updateGui() {

		if (gui != null) {

			int[] displayedTiles = Arrays.copyOf(tiles, 16);
			gui.display(displayedTiles);
		}
	}

	/**
	 * @param gui
	 */
	public void setGui(PuzzleGui gui) {

		this.gui = gui;

		updateGui();
	}

	/**
	 * 
	 * @return
	 */
	public PuzzleGui getGui() {
		return gui;
	}
}