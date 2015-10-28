package fr.guehenneux.puzzle2048;

import java.util.Arrays;

import fr.guehenneux.alphabeta.Move;

/**
 * @author Jonathan Guéhenneux
 */
public class TileMove implements Move {

	private PuzzleModel puzzle;
	private Direction direction;

	private int[] savedTiles;

	/**
	 * @param puzzle
	 * @param direction
	 */
	public TileMove(PuzzleModel puzzle, Direction direction) {

		this.puzzle = puzzle;
		this.direction = direction;
	}

	@Override
	public void play() {

		int[] tiles = puzzle.tiles;
		savedTiles = Arrays.copyOf(tiles, 16);

		int i, oldI, newI, j, oldJ, newJ;
		int tileValue;

		switch (direction) {

		case LEFT:

			for (i = 0; i < 4; i++) {

				newJ = 0;

				for (oldJ = 0; oldJ < 4; oldJ++) {

					tileValue = tiles[4 * i + oldJ];

					if (tileValue != 0) {

						if (newJ < oldJ) {

							tiles[4 * i + newJ] = tileValue;
							tiles[4 * i + oldJ] = 0;
						}

						newJ++;
					}
				}

				if (tiles[4 * i] != 0) {

					if (tiles[4 * i] == tiles[4 * i + 1]) {

						if (tiles[4 * i + 2] != 0 && tiles[4 * i + 2] == tiles[4 * i + 3]) {

							// 2300 <- 1122

							tiles[4 * i]++;
							tiles[4 * i + 1] = tiles[4 * i + 2] + 1;
							tiles[4 * i + 2] = 0;
							tiles[4 * i + 3] = 0;

						} else {

							// 2000 <- 1100
							// 2230 <- 1123

							tiles[4 * i]++;
							tiles[4 * i + 1] = tiles[4 * i + 2];
							tiles[4 * i + 2] = tiles[4 * i + 3];
							tiles[4 * i + 3] = 0;
						}

					} else if (tiles[4 * i + 1] != 0) {

						if (tiles[4 * i + 1] == tiles[4 * i + 2]) {

							// 1320 <- 1222
							// 1330 <- 1223

							tiles[4 * i + 1]++;
							tiles[4 * i + 2] = tiles[4 * i + 3];
							tiles[4 * i + 3] = 0;

						} else if (tiles[4 * i + 2] != 0 && tiles[4 * i + 2] == tiles[4 * i + 3]) {

							// 1240 <- 1233

							tiles[4 * i + 2]++;
							tiles[4 * i + 3] = 0;
						}
					}
				}
			}

			break;

		case UP:

			for (j = 0; j < 4; j++) {

				newI = 0;

				for (oldI = 0; oldI < 4; oldI++) {

					tileValue = tiles[4 * oldI + j];

					if (tileValue != 0) {

						if (newI < oldI) {

							tiles[4 * newI + j] = tileValue;
							tiles[4 * oldI + j] = 0;
						}

						newI++;
					}
				}

				if (tiles[j] != 0) {

					if (tiles[j] == tiles[4 + j]) {

						if (tiles[8 + j] != 0 && tiles[8 + j] == tiles[12 + j]) {

							// 2300 <- 1122

							tiles[j]++;
							tiles[4 + j] = tiles[8 + j] + 1;
							tiles[8 + j] = 0;
							tiles[12 + j] = 0;

						} else {

							// 2000 <- 1100
							// 2230 <- 1123

							tiles[j]++;
							tiles[4 + j] = tiles[8 + j];
							tiles[8 + j] = tiles[12 + j];
							tiles[12 + j] = 0;
						}

					} else if (tiles[4 + j] != 0) {

						if (tiles[4 + j] == tiles[8 + j]) {

							// 1320 <- 1222
							// 1330 <- 1223

							tiles[4 + j]++;
							tiles[8 + j] = tiles[12 + j];
							tiles[12 + j] = 0;

						} else if (tiles[8 + j] != 0 && tiles[8 + j] == tiles[12 + j]) {

							// 1240 <- 1233

							tiles[8 + j]++;
							tiles[12 + j] = 0;
						}
					}
				}
			}

			break;

		case RIGHT:

			for (i = 0; i < 4; i++) {

				newJ = 3;

				for (oldJ = 3; oldJ >= 0; oldJ--) {

					tileValue = tiles[4 * i + oldJ];

					if (tileValue != 0) {

						if (newJ > oldJ) {

							tiles[4 * i + newJ] = tileValue;
							tiles[4 * i + oldJ] = 0;
						}

						newJ--;
					}
				}

				if (tiles[4 * i + 3] != 0) {

					if (tiles[4 * i + 3] == tiles[4 * i + 2]) {

						if (tiles[4 * i + 1] != 0 && tiles[4 * i + 1] == tiles[4 * i]) {

							// 2211 -> 0032

							tiles[4 * i + 3]++;
							tiles[4 * i + 2] = tiles[4 * i + 1] + 1;
							tiles[4 * i + 1] = 0;
							tiles[4 * i] = 0;

						} else {

							// 0011 -> 0002
							// 3211 -> 0322

							tiles[4 * i + 3]++;
							tiles[4 * i + 2] = tiles[4 * i + 1];
							tiles[4 * i + 1] = tiles[4 * i];
							tiles[4 * i] = 0;
						}

					} else if (tiles[4 * i + 2] != 0) {

						if (tiles[4 * i + 2] == tiles[4 * i + 1]) {

							// 2221 -> 0231
							// 3221 -> 0331

							tiles[4 * i + 2]++;
							tiles[4 * i + 1] = tiles[4 * i];
							tiles[4 * i] = 0;

						} else if (tiles[4 * i + 1] != 0 && tiles[4 * i + 1] == tiles[4 * i]) {

							// 3321 -> 0421

							tiles[4 * i + 1]++;
							tiles[4 * i] = 0;
						}
					}
				}
			}

			break;

		case DOWN:

			for (j = 0; j < 4; j++) {

				newI = 3;

				for (oldI = 3; oldI >= 0; oldI--) {

					tileValue = tiles[4 * oldI + j];

					if (tileValue != 0) {

						if (newI > oldI) {

							tiles[4 * newI + j] = tileValue;
							tiles[4 * oldI + j] = 0;
						}

						newI--;
					}
				}

				if (tiles[12 + j] != 0) {

					if (tiles[12 + j] == tiles[8 + j]) {

						if (tiles[4 + j] != 0 && tiles[4 + j] == tiles[j]) {

							// 2211 -> 0032

							tiles[12 + j]++;
							tiles[8 + j] = tiles[4 + j] + 1;
							tiles[4 + j] = 0;
							tiles[j] = 0;

						} else {

							// 0011 -> 0002
							// 0111 -> 0012
							// 3211 -> 0322

							tiles[12 + j]++;
							tiles[8 + j] = tiles[4 + j];
							tiles[4 + j] = tiles[j];
							tiles[j] = 0;
						}

					} else if (tiles[8 + j] != 0) {

						if (tiles[8 + j] == tiles[4 + j]) {

							// 2221 -> 0231
							// 3221 -> 0331

							tiles[8 + j]++;
							tiles[4 + j] = tiles[j];
							tiles[j] = 0;

						} else if (tiles[4 + j] != 0 && tiles[4 + j] == tiles[j]) {

							// 3321 -> 0421

							tiles[4 + j]++;
							tiles[j] = 0;
						}
					}
				}
			}

			break;

		default:
			break;
		}

		puzzle.nextPlayer();
	}

	/**
	 * @return the direction
	 */
	public Direction getDirection() {
		return direction;
	}

	@Override
	public void cancel() {

		puzzle.tiles = savedTiles;
		puzzle.previousPlayer();
	}

	@Override
	public String toString() {
		return direction.name();
	}
}