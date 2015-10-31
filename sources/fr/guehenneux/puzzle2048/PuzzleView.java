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

	private static final Color[] COLORS = { Color.web("0xbbada0"), Color.web("0xeee4da"), Color.web("0xede0c8"),
			Color.web("0xf2b179"), Color.web("0xf59563"), Color.web("0xf67c5f"), Color.web("0xf65e3b"),
			Color.web("0xedcf72"), Color.web("0xedcc61"), Color.web("0xedc850"), Color.web("0xedc53f"),
			Color.web("0xedc22e"), Color.web("0x000000"), Color.web("0x000000"), Color.web("0x000000"),
			Color.web("0x000000"), Color.web("0x000000") };

	private Label[] labels;

	/**
	 * 
	 */
	public PuzzleView() {

		setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));

		setHgap(2);
		setVgap(2);

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
	 * @param tiles
	 */
	public void display(int[] tiles) {
		Platform.runLater(() -> updatePlatform(tiles));
	}

	/**
	 * @param tiles
	 */
	private void updatePlatform(int[] tiles) {

		int tileValue;

		for (int k = 0; k < 16; k++) {

			tileValue = tiles[k];

			if (tileValue == 0) {
				labels[k].setText("");
			} else {
				labels[k].setText(Integer.toString(1 << tileValue));
			}

			labels[k].setBackground(new Background(new BackgroundFill(COLORS[tileValue], CornerRadii.EMPTY,
					Insets.EMPTY)));
		}
	}
}