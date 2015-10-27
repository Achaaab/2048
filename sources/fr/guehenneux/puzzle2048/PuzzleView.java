package fr.guehenneux.puzzle2048;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

/**
 * @author Jonathan Guéhenneux
 */
public class PuzzleView extends GridPane {

	private PuzzleModel model;

	private Label[] labels;

	/**
	 * @param model
	 */
	public PuzzleView(PuzzleModel model) {

		this.model = model;

		labels = new Label[16];

		int k = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++, k++) {
				add(labels[k] = new Label(), i, j);
			}
		}
	}

	/**
	 * 
	 */
	public void update() {

		for (int k = 0; k < 16; k++) {
			labels[k].setText(Integer.toString(model.tiles[k]));
		}
	}
}