package fr.guehenneux.puzzle2048;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * @author Jonathan Guéhenneux
 */
public class PuzzleView extends GridPane {

	private static final Color[] COLORS = { Color.LIGHTGRAY, Color.WHITE, Color.BEIGE, Color.SANDYBROWN, Color.ORANGE,
			Color.TOMATO, Color.RED, Color.LIGHTYELLOW, Color.LEMONCHIFFON, Color.YELLOW, Color.GOLD, Color.MAGENTA,
			Color.MEDIUMPURPLE };

	private PuzzleModel model;

	private Label[] labels;

	/**
	 * @param model
	 */
	public PuzzleView(PuzzleModel model) {

		setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		setHgap(2);
		setVgap(2);

		this.model = model;
		model.setView(this);

		labels = new Label[16];
		Label label;

		int k = 0;

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++, k++) {

				label = new Label();
				label.setPrefSize(100, 100);
				label.setAlignment(Pos.CENTER);
				label.setFont(new Font(40));

				add(labels[k] = label, j, i);
			}
		}
	}

	/**
	 * 
	 */
	public void update() {
		Platform.runLater(() -> updatePlatform());
	}

	/**
	 * 
	 */
	private void updatePlatform() {

		int tileValue;

		for (int k = 0; k < 16; k++) {

			tileValue = model.tiles[k];

			labels[k].setText(Integer.toString(1 << tileValue));
			labels[k].setBackground(new Background(new BackgroundFill(COLORS[tileValue], CornerRadii.EMPTY,
					Insets.EMPTY)));
		}
	}
}