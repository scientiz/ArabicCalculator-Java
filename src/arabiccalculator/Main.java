/*
 * Program: Arabic Calculator
 * Author: Cartis Burton, COSC 211, F25
 *
 * Main JavaFX class that launches my Arabic Calculator.
 * It creates a window, puts the ArabicCalculator pane inside it,
 * and shows the stage. I wrote and tested everything in NetBeans IDE 16 with JavaFX libraries manually added.
 */


package arabiccalculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        // ArabicCalculator is my custom GridPane with all the controls
        ArabicCalculator calcPane = new ArabicCalculator();

        // Scene holds the root pane and sets the initial window size
        // 400x350 seems to be the sweet spot 7 NOV 2025
        Scene scene = new Scene(calcPane, 400, 350);

        primaryStage.setTitle("Cartis Burton: Arabic Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}