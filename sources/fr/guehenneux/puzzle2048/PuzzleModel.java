package fr.guehenneux.puzzle2048;

import java.util.Arrays;

import fr.guehenneux.alphabeta.Player;
import fr.guehenneux.alphabeta.ZeroSumGame;

/**
 * @author Jonathan Guéhenneux
 */
public class PuzzleModel extends ZeroSumGame {

	private static final int[] SNAKE = { 0, 1, 2, 3, 7, 6, 5, 4, 8, 9, 10, 11, 15, 14, 13, 12 };
	private static final int[] WEIGHTS = { 8, 2, 2, 8, 2, 1, 1, 2, 2, 1, 1, 2, 8, 2, 2, 8 };

	private TileCreator tileCreator;
	private TileMover tileMover;
	private Player currentPlayer;

	private PuzzleView view;

	int[] tiles;

	/**
	 * 
	 */
	public PuzzleModel() {

		super(6);

		tiles = new int[16];

		tileCreator = new TileCreator(this);
		tileMover = new TileMoverAI(this);

		currentPlayer = tileCreator;
		tileCreator.getMove().play();

		currentPlayer = tileCreator;
		tileCreator.getMove().play();

		currentPlayer = tileMover;

		view = null;
	}

	@Override
	public double getHeuristicValue(Player player) {

		double heuristicValue = (getWeightedTileTotal() + 2 * getSmoothness()) / (16 - getEmptyTileCount());

		if (player == tileCreator) {
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
		if (tileMover.getMoves().isEmpty()) {
			return tileCreator;
		} else {
			return null;
		}
	}

	@Override
	public double getVictoryValue() {
		return Double.MAX_VALUE;
	}

	@Override
	public void updateView() {

		if (view != null) {

			int[] displayedTiles = Arrays.copyOf(tiles, 16);
			view.display(displayedTiles);
		}
	}

	/**
	 * @param view
	 */
	public void setView(PuzzleView view) {

		this.view = view;

		updateView();
	}
}