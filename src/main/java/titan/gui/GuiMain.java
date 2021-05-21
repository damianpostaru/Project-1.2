package titan.gui;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import titan.ProbeSimulator;
import titan.interfaces.Vector3dInterface;
import titan.space.Vector3d;

import java.util.Timer;
import java.util.TimerTask;

public class GuiMain extends Application {

    static CelestialBody sun, earth, mercury, venus, moon, mars, jupiter, saturn, titan, probe, neptune, uranus;
    static Rectangle2D screenBounds;
    protected static Stage singleStage;
    protected static Scene introScene, visualiserScene;
    public static Button beginButton, probeLaunch, exitButton;
    public static double centerX, centerY, distancePixel;
    public static Path sunPath, mercuryPath, venusPath, earthPath, moonPath, marsPath, jupiterPath, saturnPath, titanPath, probePath, neptunePath, uranusPath;
    public static VBox infoBox;
    public static HBox sunBox, mercuryBox, venusBox, earthBox, moonBox, marsBox, jupiterBox, saturnBox, titanBox, probeBox, neptuneBox, uranusBox;
    private static Vector3dInterface initialPosition, initialVelocity;

    // Timer Related Variables
    protected static TimerTask timerTask;
    protected static Text timeText;
    protected static Timer timer;
    protected static String ssl;
    protected static boolean isTimerTaskRunning;
    protected static int timerTime;

    // Zoom Related Variables
    protected static DoubleProperty scrollScale;
    protected static Pane zoomPane;
    private static double initialSceneX, initialSceneY, xTranslation, yTranslation;


    public static void main(String[] args) {
        initialPosition = new Vector3d(-6371e3, 0.1, 0.1);
        initialVelocity = new Vector3d(0, 0, 0);//new Vector3d(18044.44, -29351.0, -819.35);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        singleStage = primaryStage;
        singleStage.setTitle("A Titanic Space Odyssey!");

        screenBounds = Screen.getPrimary().getBounds();

        centerX = screenBounds.getWidth() / 2;
        centerY = screenBounds.getHeight() / 2;

        // Sets all the scenes that will be (eventually) seen.
        IntroScene.setIntroScene();
        VisualiserScene.setVisualiserScene();

        PlanetTransition.createPath();

        (new ProbeSimulator()).trajectory(initialPosition, initialVelocity, 270000000, 500);//260000000

        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        singleStage.setScene(introScene);
        singleStage.show();
    }

    /**
     * Handles scrolling related events.
     */
    protected static final EventHandler<ScrollEvent> scrollHandler = new EventHandler<>() {

        final double MAX_SCROLL_SCALE = 5.0, MIN_SCROLL_SCALE = 0.7;
        double scaleChanger, xChanger, yChanger;

        @Override
        public void handle(ScrollEvent e) {

            double currentScale = scrollScale.get();
            double prevScale = currentScale;
            double delta = 1.25;

            if (e.getDeltaY() >= 0) {
                currentScale *= delta;
            } else if (e.getDeltaY() < 0) {
                currentScale /= delta;
            }

            currentScale = adjust(currentScale);

            scaleChanger = (currentScale / prevScale) - 1;
            xChanger = (e.getSceneX() - (screenBounds.getWidth() / 2 + screenBounds.getMinX()));
            yChanger = (e.getSceneY() - (screenBounds.getHeight() / 2 + screenBounds.getMinY()));

            scrollScale.set(currentScale);

            zoomPane.setTranslateX(zoomPane.getTranslateX() - scaleChanger * xChanger);
            zoomPane.setTranslateY(zoomPane.getTranslateY() - scaleChanger * yChanger);
        }

        // Prevents going over the scale bounds that were set above.
        private double adjust(double scale) {
            if (scale < MIN_SCROLL_SCALE) {
                return MIN_SCROLL_SCALE;
            }
            return Math.min(scale, MAX_SCROLL_SCALE);
        }
    };

    /**
     * Handles panning related events.
     */
    protected static final EventHandler<MouseEvent> pressHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent e) {
            initialSceneX = e.getSceneX();
            initialSceneY = e.getSceneY();

            xTranslation = zoomPane.getTranslateX();
            yTranslation = zoomPane.getTranslateY();
        }

    };
    protected static final EventHandler<MouseEvent> dragHandler = new EventHandler<>() {

        @Override
        public void handle(MouseEvent e) {
            zoomPane.setTranslateX(xTranslation + e.getSceneX() - initialSceneX);
            zoomPane.setTranslateY(yTranslation + e.getSceneY() - initialSceneY);
        }
    };
}
