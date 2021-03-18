import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Timer;
import java.util.TimerTask;

public class GuiMain extends Application implements EventHandler<ActionEvent> {

    static CelestialBody sun, earth, mercury, venus, moon, mars, jupiter, saturn, titan, probe;
    static Rectangle2D screenBounds;
    private BorderPane root;
    private Stage singleStage;
    private Scene introScene;
    private Scene visualiserScene;
    public static Button launchButton, probeLaunch, exitButton;
    public static Text timeText;
    public static double centerX;
    public static double centerY;
    public static double distancePixel;
    public static Path sunPath, mercuryPath, venusPath, earthPath, moonPath, marsPath,
            jupiterPath, saturnPath, titanPath, probePath;
    public static VBox infoBox;
    public static HBox sunBox, mercuryBox, venusBox, earthBox, moonBox, marsBox, jupiterBox,
            saturnBox, titanBox, probeBox;
    private static Vector3dInterface initialPosition;
    private static Vector3dInterface initialVelocity;


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


//        Solver solver = new Solver();
//        State state0 = new State(initialPosition, initialVelocity);
//        StateInterface[] states = solver.solve(new Function(), state0, 31556926, 3600);

        Solver solver = new Solver();
        ProbeSimulator probeSimulator = new ProbeSimulator();
        State state0 = new State(initialPosition, initialVelocity);
        Vector3d[] trajectory = (Vector3d[]) probeSimulator.trajectory(initialPosition, initialVelocity, 31556926, 60);


        probeLaunch.setOnAction(e -> {
            //PlanetTransition.transition(sun, sunPath);
            PlanetTransition.transition(mercury, mercuryPath);
            PlanetTransition.transition(venus, venusPath);
            PlanetTransition.transition(earth, earthPath);
            PlanetTransition.transition(moon, moonPath);
            PlanetTransition.transition(mars, marsPath);
            PlanetTransition.transition(jupiter, jupiterPath);
            PlanetTransition.transition(saturn, saturnPath);
            PlanetTransition.transition(titan, titanPath);
            PlanetTransition.transition(probe, probePath);
        });


//        for (int i = 0; i < sunPath.getElements().size(); i++) {
//            System.out.println(titanPath.getElements().get(i));
//        }

        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        singleStage.setScene(introScene);
        singleStage.show();
    }

    public void setIntroScene() {
        StackPane beginPane = new StackPane();
        introScene = new Scene(beginPane, screenBounds.getWidth(), screenBounds.getHeight());
        introScene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());


        VBox introBox = new VBox(50);
        Label introLabel = new Label("A Titanic Space Odyssey!");
        introLabel.getStyleClass().add("introLabel");


        launchButton = new Button("LAUNCH PROBE!");

        introBox.getChildren().addAll(introLabel, launchButton);

        launchButton.setOnAction((EventHandler<ActionEvent>) this);
        launchButton.setPrefSize(310, 75);

        introBox.setAlignment(Pos.CENTER);
        beginPane.getChildren().add(introBox);
    }

    public void setVisualiserScene() {

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

        timeText = new Text();
        timeText.setId("timeText");

        InfoScreen.run();

        root = new BorderPane();
        root.setAlignment(timeText, Pos.BOTTOM_RIGHT);
        root.setRight(infoBox);
        root.setBottom(timeText);
        root.getChildren().addAll(sun.getBody(), earth.getBody(), mercury.getBody(),
                venus.getBody(), moon.getBody(), mars.getBody(), jupiter.getBody(),
                saturn.getBody(), titan.getBody(), probe.getBody());

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());
    }

    /*
     * Times and updates the probe journey continuously - seconds since launch.
     * (at the moment, it's not configured properly, but should be easy to use the actual
     * probe travel variable, instead of this 'seconds' variable)
     */
    int i = 0;
    String ssl;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
        	/* Basically from accessing the list accessTimes, we conclude that it has 525948 elements.
        	 * By dividing this value (number of steps taken) by 12 seconds - the set duration of the visualisation -
        	 * we get an integer number, since 525948%43829 = 0 (obtained from 525948/12 = 43829).
        	 * This allows us to access the list at that same index each time.
        	 * This way, we can make the timer increase harmoniously during the entirety of the probe launch.
        	 */
            i += 43829;
            if(i == 525948) {
            	i--;
            	ssl = "TimeSteps Since Launch: " + Solver.getAccessTimes().get(i);
            	timer.cancel();
            }
            ssl = "TimeSteps Since Launch: " + Solver.getAccessTimes().get(i);
            timeText.setText(ssl);
        }
    };

    /*
     * Handles all button actions in one single method.
     */
    public void handle(ActionEvent e) {
        if (e.getSource() == launchButton) {
            singleStage.setScene(visualiserScene);
            singleStage.setFullScreen(true);
            singleStage.setResizable(false);
            singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            timer.scheduleAtFixedRate(task, 0, 1000);
            //System.out.println("ELEMENTS " + Solver.getAccessTimes().size());
        }
    }


}
