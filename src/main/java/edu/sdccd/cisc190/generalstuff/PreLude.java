package edu.sdccd.cisc190.generalstuff;

import edu.sdccd.cisc190.scenes.TwelveMorning;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The PreLude class represents the introductory scene of the game "NightShift at Seven Guys".
 * It initializes the game interface that introduces the player to their new office environment
 * and displays their current conviction and madness statistics.
 *
 * <p>This class sets up the user interface elements, including the game status text, player stats,
 * and an action button that leads to the next scene once clicked. The action button transitions
 * the player to the {@link TwelveMorning} scene.</p>
 *
 * <p>Usage:</p>
 * <pre>
 * Stage primaryStage = new Stage();
 * PreLude preludeScene = new PreLude(primaryStage, conviction, madness);
 * primaryStage.setScene(preludeScene.getScene());
 * primaryStage.show();
 * </pre>
 *
 * @see TwelveMorning
 */
public class PreLude {
    private Scene scene;
    private final String[] playerItems = {"Flashlight", "Badge", "Water Bottle"}; // Array to store player items
    private int conviction; // Player's conviction
    private int madness;    // Player's madness

    // Constructor to accept conviction and madness
    public PreLude(Stage primaryStage, int conviction, int madness) {
        this.conviction = conviction;
        this.madness = madness;

        // Create the game status text
        Text gameStatus = new Text("What was that? Did the stat points that should be appearing on a “screen” increase. " +
                "Apparently, everything you do is judge. Of Judgey devs. Huh what did we do-.\n" +
                "“Whatever, I can do this” you say anxiously of the sanity meter that is also there.\n" +
                "“But we'll probably avoid getting that meter down to 0. Just a thought”\n" +
                "(We won’t tell the nightguard this but, you’ll die. Don’t do dumb sounding stuff and you won’t die. " +
                "I mean unless you want to :p)\nHere you are, sitting in your new office. " +
                "There’s the door to the main stage on your left, a table filled with monitors of the different cameras around the restaurant, " +
                "a fan on top that barely works, and of course, a flashlight and a badge on your moldy chair " +
                "that you’re sure hasn’t been cleaned in weeks… you have a bad feeling about this. How… cliche.\n");
        gameStatus.setStyle("-fx-font-size: 9px; -fx-font-weight: bold;");

        // Create the display for stats (Conviction and Madness)
        Text statsText = new Text("Conviction: " + conviction + " | Madness: " + madness);
        statsText.setStyle("-fx-font-size: 14px; -fx-font-weight: bold;");

        // Create the action button
        Button actionButton = new Button("Feeling a little 12AM?");
        actionButton.setStyle("-fx-font-size: 14px;");
        actionButton.setOnAction(e -> primaryStage.setScene(new TwelveMorning(primaryStage).getScene()));

        // Set up the BorderPane layout
        BorderPane layout = new BorderPane();

        // Add the gameStatus text to the top of the BorderPane
        layout.setTop(gameStatus);

        // Add the statsText below the gameStatus
        layout.setBottom(statsText);

        // Add the actionButton to the center of the BorderPane
        VBox buttonLayout = new VBox(10, actionButton);
        layout.setCenter(buttonLayout);

        // Create the scene with the layout and set the preferred size
        scene = new Scene(layout, 300, 400);

        // Start a background task to monitor player stats
        startBackgroundTask(statsText);
    }

    /**
     * Starts a background task that periodically updates player stats.
     *
     * @param statsText the Text element displaying player stats
     */
    private void startBackgroundTask(Text statsText) {
        new Thread(() -> {
            try {
                while (true) {
                    // Sleep for 10 seconds before the next update
                    Thread.sleep(10000); // Update every 10 seconds

                    // Simulate stat changes
                    conviction += 1; // Increase conviction over time
                    madness += 1;    // Increase madness over time

                    // Update the UI with new stats
                    Platform.runLater(() -> {
                        statsText.setText("Conviction: " + conviction + " | Madness: " + madness);
                    });
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public PreLude(Stage primaryStage) {
        // Default constructor
    }

    // Getter for the scene
    public Scene getScene() {
        return scene;
    }
}