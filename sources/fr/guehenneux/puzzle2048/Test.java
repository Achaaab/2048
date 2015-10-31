package fr.guehenneux.puzzle2048;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 * @author Jonathan Guéhenneux
 */
public class Test extends Application {

	/**
	 * @param arguments
	 *            none
	 */
	public static void main(String... arguments) {
		launch(arguments);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		try {

			PuzzleModel model = new PuzzleModel();
			PuzzleView view = new PuzzleView();

			model.setView(view);

			Group root = new Group(view);
			Scene scene = new Scene(root);

			TileMover tileMover = model.getTileMoverKeyBoard();

			if (tileMover instanceof EventHandler<?>) {
				view.setOnKeyPressed((EventHandler<KeyEvent>) tileMover);
			}

			view.requestFocus();

			primaryStage.setScene(scene);
			primaryStage.setTitle("2048 test");
			primaryStage.show();

			new Thread(model).start();

		} catch (Throwable throwable) {

			throwable.printStackTrace();
		}
	}

	@Override
	public void stop() throws Exception {

		Platform.exit();
		System.exit(0);
	}
}