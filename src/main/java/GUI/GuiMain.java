package GUI;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;


public class GuiMain extends Application {

    static CelestialBody sun, earth;
    static Rectangle2D screenBounds;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("A Titanic Space Odyssey!");

        screenBounds = Screen.getPrimary().getBounds();

        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        sun = new CelestialBody("Sun", screenBounds.getWidth()/2,
                screenBounds.getHeight()/2);
        sun.getBody().getStyleClass().add("sun");

        earth = new CelestialBody("Earth", screenBounds.getWidth()/4,
                screenBounds.getHeight()/2);
        earth.getBody().getStyleClass().add("earth");

        transition();

        BorderPane root = new BorderPane();
        root.getChildren().addAll(sun.getBody(), earth.getBody());
        Scene scene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());

        scene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /*
        Creates animation for earth.
     */
    public static void transition() {
        Path path = new Path();
        PathTransition pathTransition = new PathTransition();
        pathTransition.setNode(earth.getBody());
        pathTransition.setDuration(Duration.millis(10000));
        MoveTo moveTo = new MoveTo(earth.getX(), earth.getY());
        ArcTo arcTo = new ArcTo();
        arcTo.setX(screenBounds.getWidth()*0.75);
        arcTo.setY(screenBounds.getHeight()/2);
        arcTo.setRadiusX(50.0);
        arcTo.setRadiusY(50.0);

        ArcTo arcTo2 = new ArcTo();
        arcTo2.setX(screenBounds.getWidth()/4);
        arcTo2.setY(screenBounds.getHeight()/2);
        arcTo2.setRadiusX(50.0);
        arcTo2.setRadiusY(50.0);

        path.getElements().addAll(moveTo, arcTo, arcTo2);
        pathTransition.setPath(path);
        pathTransition.setCycleCount(Animation.INDEFINITE);
        pathTransition.setInterpolator(Interpolator.LINEAR);

        pathTransition.play();
    }
}

