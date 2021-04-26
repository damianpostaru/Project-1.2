package titan.gui;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import titan.ProbeSimulator;
import titan.interfaces.Vector3dInterface;
import titan.solver.Solver;
import titan.space.Vector3d;

import java.util.Timer;
import java.util.TimerTask;

public class GuiMain extends Application implements EventHandler<ActionEvent> {

    static CelestialBody sun, earth, mercury, venus, moon, mars, jupiter, saturn, titan, probe,
            neptune, uranus;
    static Rectangle2D screenBounds;
    private Stage singleStage;
    private Scene introScene, visualiserScene;
    public static Button beginButton, probeLaunch, exitButton;
    public static double centerX, centerY, distancePixel;
    public static Path sunPath, mercuryPath, venusPath, earthPath, moonPath, marsPath,
            jupiterPath, saturnPath, titanPath, probePath, neptunePath, uranusPath;
    public static VBox infoBox;
    public static HBox sunBox, mercuryBox, venusBox, earthBox, moonBox, marsBox, jupiterBox,
            saturnBox, titanBox, probeBox, neptuneBox, uranusBox;
    private static Vector3dInterface initialPosition, initialVelocity;
    // Timer Related Variables
    private TimerTask timerTask;
    private Text timeText;
    private Timer timer;
    private String ssl;
    private boolean isTimerTaskRunning;
    private int timerTime;
    // Zoom Related Variables
    private DoubleProperty scrollScale;
    private Pane zoomPane;
    private double initialSceneX, initialSceneY, xTranslation, yTranslation;


    public static void main(String[] args) {
        initialPosition = new Vector3d(0.1, -6371e3, 0.1);
        initialVelocity = new Vector3d(18044.44, -29351.0, -819.35);
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
        setIntroScene();
        setVisualiserScene();

        PlanetTransition.createPath();

        ProbeSimulator probeSimulator = new ProbeSimulator();
        Vector3d[] trajectory =
                (Vector3d[]) probeSimulator.trajectory(initialPosition, initialVelocity, 31556926, 60);

        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        singleStage.setScene(introScene);
        singleStage.show();
    }

    public void setIntroScene() {
        StackPane beginPane = new StackPane();
        introScene = new Scene(beginPane, screenBounds.getWidth(), screenBounds.getHeight());
        introScene.getStylesheets().add("Stylesheet.css");

        VBox introBox = new VBox(50);
        Label introLabel = new Label("A Titanic Space Odyssey!");
        introLabel.getStyleClass().add("introLabel");

        beginButton = new Button("BEGIN!");

        introBox.getChildren().addAll(introLabel, beginButton);

        beginButton.setOnAction(this);
        beginButton.setPrefSize(310, 75);

        introBox.setAlignment(Pos.CENTER);
        beginPane.getChildren().add(introBox);
    }

    public void setVisualiserScene() {

        setCelestialBodies();

        timeText = new Text();
        timeText.setId("timeText");

        probeLaunch = new Button("Launch Probe!");
        exitButton = new Button("Exit Simulation!");
        probeLaunch.setOnAction(this);
        exitButton.setOnAction(this);

        InfoScreen.run();

        scrollScale = new SimpleDoubleProperty(1.0);
        zoomPane = new Pane();
        zoomPane.scaleXProperty().bind(scrollScale);
        zoomPane.scaleYProperty().bind(scrollScale);
        zoomPane.getChildren().addAll(sun.getBody(), earth.getBody(), mercury.getBody(),
                venus.getBody(), moon.getBody(), mars.getBody(), jupiter.getBody(), saturn.getBody(),
                uranus.getBody(), neptune.getBody(), titan.getBody(), probe.getBody());
        // Initially displayed Solar System position
        zoomPane.setTranslateX(-screenBounds.getWidth()/4);
        zoomPane.setTranslateY(-screenBounds.getHeight()/4);

        zoomPane.setOnScroll(scrollHandler);
        zoomPane.setOnMousePressed(pressHandler);
        zoomPane.setOnMouseDragged(dragHandler);

        BorderPane root = new BorderPane();
        root.setRight(infoBox);
        root.setAlignment(timeText, Pos.BOTTOM_RIGHT);
        root.setBottom(timeText);
        root.setCenter(zoomPane);
        // Make the infoBox always appear on top in relation to the zoomPane
        // This allows the the buttons 'probeLaunch' and 'exitButton' to be clickable at all times.
        zoomPane.toBack();

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add("Stylesheet.css");
    }

    /*
     * Sets all the celestial bodies to be seen in the visualiser scene.
     */
    private void setCelestialBodies() {

        distancePixel = 3000 / screenBounds.getHeight();

        sun = new CelestialBody("Sun", centerX + (((-6.806783239281648e8) / (1e9)) / distancePixel),
                centerY - (((1.080005533878725e9) / (1e9)) / distancePixel), 15);
        sun.getBody().getStyleClass().add("sun");

        earth = new CelestialBody("Earth",
                centerX + (((-1.471922101663588e11) / (1e9)) / distancePixel),
                centerY - (((-2.860995816266412e10) / (1e9)) / distancePixel), 11);
        earth.getBody().getStyleClass().add("earth");

        mercury = new CelestialBody("Mercury",
                centerX + (((6.047855986424127e6) / (1e9)) / distancePixel),
                centerY - (((-6.801800047868888e10) / (1e9)) / distancePixel), 6);
        mercury.getBody().getStyleClass().add("mercury");

        venus = new CelestialBody("Venus",
                centerX + (((-9.435345478592035e10) / (1e9)) / distancePixel),
                centerY - (((5.350359551033670e10) / (1e9)) / distancePixel), 7);
        venus.getBody().getStyleClass().add("venus");

        moon = new CelestialBody("Moon", centerX + (((-1.472343904597218e11) / (1e9)) / distancePixel),
                centerY - (((-2.822578361503422e10) / (1e9)) / distancePixel), 5);
        moon.getBody().getStyleClass().add("moon");


        mars = new CelestialBody("Mars", centerX + (((-3.615638921529161e10) / (1e9)) / distancePixel),
                centerY - (((-2.167633037046744e11) / (1e9)) / distancePixel), 7);
        mars.getBody().getStyleClass().add("mars");

        jupiter = new CelestialBody("Jupiter",
                centerX + (((1.781303138592153e11) / (1e9)) / distancePixel),
                centerY - (((-7.551118436250277e11) / (1e9)) / distancePixel), 13);
        jupiter.getBody().getStyleClass().add("jupiter");

        saturn = new CelestialBody("Saturn",
                centerX + (((6.328646641500651e11) / (1e9)) / distancePixel),
                centerY - (((-1.358172804527507e12) / (1e9)) / distancePixel), 13);
        saturn.getBody().getStyleClass().add("saturn");

        titan = new CelestialBody("Titan",
                centerX + (((6.332873118527889e11) / (1e9)) / distancePixel),
                centerY - (((-1.357175556995868e12) / (1e9)) / distancePixel), 6);
        titan.getBody().getStyleClass().add("titan");

        probe = new CelestialBody("Probe",
                centerX + (((-1.471922101663588e11) / (1e9)) / distancePixel) + (0.1/1e9),
                centerY - (((-2.860995816266412e10) / (1e9)) / distancePixel) - (6371e3/1e9), 4);
        probe.getBody().getStyleClass().add("probe");

        neptune = new CelestialBody("Neptune",
                centerX + (((4.382692942729203e12) / (1e9)) / distancePixel),
                centerY - (((-9.093501655486243e11) / (1e9)) / distancePixel), 15);
        neptune.getBody().getStyleClass().add(("neptune"));

        uranus = new CelestialBody("Uranus",
                centerX + (((2.395195786685187e12) / (1e9)) / distancePixel),
                centerY - (((1.744450959214586e12) / (1e9)) / distancePixel), 15);
        uranus.getBody().getStyleClass().add("uranus");
    }

    /*
     * Times and updates the probe journey continuously - seconds since launch.
     */
    public void startTimerTask() {
        if(isTimerTaskRunning) {
            timerTask.cancel();
            isTimerTaskRunning = false;
        }
        timerTime = 0;
        timer = new Timer();
        timerTask = new TimerTask() {
            public void run() {
                isTimerTaskRunning = true;
                /* Basically from accessing the list accessTimes, we conclude that it has 525948 elements.
                 * By dividing this value (number of steps taken) by 12 seconds - the set duration of the visualisation -
                 * we get an integer number, since 525948%43829 = 0 (obtained from 525948/12 = 43829).
                 * This allows us to access the list at that same index each time.
                 * This way, we can make the timer increase harmoniously during the entirety of the probe launch.
                 */
                if(timerTime + 43829 <= Solver.getAccessTimes().size()+1) {
                    timerTime += 43829;
                }
                if (timerTime == 525948) {
                    timerTime -= 2;
                    ssl = "Time Since Launch: " + Solver.getAccessTimes().get(timerTime);
                    timer.cancel();
                    //probeLaunch.setDisable(false);
                }
                ssl = "Time Since Launch: " + Solver.getAccessTimes().get(timerTime);
                timeText.setText(ssl);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    /*
     * Handles all button actions in one single method.
     */
    @Override
    public void handle(ActionEvent e) {
        if (e.getSource() == beginButton) {
            singleStage.setScene(visualiserScene);
            singleStage.setFullScreen(true);
            singleStage.setResizable(false);
            singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        }
        if (e.getSource() == probeLaunch) {
            startTimerTask();
            //timer.scheduleAtFixedRate(timerTask, 0, 1000);
            //probeLaunch.setDisable(true);
            PlanetTransition.transition(mercury, mercuryPath);
            PlanetTransition.transition(venus, venusPath);
            PlanetTransition.transition(earth, earthPath);
            PlanetTransition.transition(moon, moonPath);
            PlanetTransition.transition(mars, marsPath);
            PlanetTransition.transition(jupiter, jupiterPath);
            PlanetTransition.transition(saturn, saturnPath);
            PlanetTransition.transition(titan, titanPath);
            PlanetTransition.transition(probe, probePath);
            PlanetTransition.transition(neptune, neptunePath);
            PlanetTransition.transition(uranus, uranusPath);
        }
        if(e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    /*
     * Handles scrolling related events.
    */
    private final EventHandler<ScrollEvent> scrollHandler = new EventHandler<ScrollEvent>() {

        final double MAX_SCROLL_SCALE = 5.0, MIN_SCROLL_SCALE = 0.7;
        double scaleChanger, xChanger, yChanger;

        @Override
        public void handle(ScrollEvent e) {

            double currentScale = scrollScale.get();
            double prevScale = currentScale;
            double delta = 1.25;

            if (e.getDeltaY() >= 0) {
                currentScale *= delta;
            } else if (e.getDeltaY() < 0){
                currentScale /= delta;
            }

            currentScale = adjust(currentScale);

            scaleChanger = (currentScale/prevScale) - 1;
            xChanger = (e.getSceneX() - (screenBounds.getWidth()/2 + screenBounds.getMinX()));
            yChanger = (e.getSceneY() - (screenBounds.getHeight()/2 + screenBounds.getMinY()));

            scrollScale.set(currentScale);

            zoomPane.setTranslateX(zoomPane.getTranslateX()-scaleChanger*xChanger);
            zoomPane.setTranslateY(zoomPane.getTranslateY()-scaleChanger*yChanger);
        }

        // Prevents going over the scale bounds that were set above.
        private double adjust(double scale) {
            if(scale < MIN_SCROLL_SCALE) {
                return MIN_SCROLL_SCALE;
            }
            if(scale > MAX_SCROLL_SCALE) {
                return MAX_SCROLL_SCALE;
            }
            return scale;
        }
    };

    /*
     * Handles panning related events.
     */
    private final EventHandler<MouseEvent> pressHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            initialSceneX = e.getSceneX();
            initialSceneY = e.getSceneY();

            xTranslation = zoomPane.getTranslateX();
            yTranslation = zoomPane.getTranslateY();
        }

    };
    private final EventHandler<MouseEvent> dragHandler = new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent e) {
            zoomPane.setTranslateX(xTranslation + e.getSceneX() - initialSceneX);
            zoomPane.setTranslateY(yTranslation + e.getSceneY() - initialSceneY);
        }
    };
}
