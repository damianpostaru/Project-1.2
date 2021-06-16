package titan_lander.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class LanderIntroScene extends  LanderVisualizer
{
        public static void setIntroScene()
        {
            StackPane beginPane = new StackPane();
            introScene = new Scene(beginPane, screenBounds.getWidth(), screenBounds.getHeight());
            introScene.getStylesheets().add("Stylesheet.css");

            VBox introBox = new VBox(50);
            Label introLabel = new Label("Titan Landing!");
            introLabel.getStyleClass().add("introLabel");
            beginButton = new Button("Start!");

            introBox.getChildren().addAll(introLabel, beginButton);

            beginButton.setOnAction(e -> {
                singleStage.setScene(visualiserScene);
                singleStage.setFullScreen(true);
                singleStage.setResizable(false);
                singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            });
            beginButton.setPrefSize(310, 75);

            introBox.setAlignment(Pos.CENTER);
            beginPane.getChildren().add(introBox);
        }

}
