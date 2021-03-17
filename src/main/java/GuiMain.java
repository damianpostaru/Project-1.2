import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import java.io.InputStream;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.util.Duration;
import java.util.Timer;
import java.util.TimerTask;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class GuiMain extends Application implements EventHandler<ActionEvent>{

    static CelestialBody sun, earth, mercury, venus, moon, mars, jupiter, saturn, titan;
    static Rectangle2D screenBounds;
    private BorderPane root;
    private Stage singleStage;
    private Scene introScene;
    private Scene visualiserScene;
    private Button launchButton;
    private Text timeText;
    private double centerX;
    private double centerY;

    public static void main(String[] args) {
        launch(args);
//        Solver s = new Solver();
//        Vector3dInterface p0 = null;
//        Vector3dInterface v0 = null;
//        State state0 = new State(p0, v0);
//        StateInterface[] states = s.solve(new Function(), state0,31556926 , 86400);
    }

    @Override
    public void start(Stage primaryStage) {

        singleStage = primaryStage;
        singleStage.setTitle("A Titanic Space Odyssey!");

        screenBounds = Screen.getPrimary().getBounds();

        centerX = screenBounds.getWidth()/2;
        centerY = screenBounds.getHeight()/2;

        // Sets all the scenes that will be (eventually) seen.
        setIntroScene();
        setVisualiserScene();

        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        singleStage.setScene(introScene);
        singleStage.show();
    }

    public void setIntroScene() {
        String introTitle = "A Titanic" + System.getProperty("line.separator") + "Space Odyssey!";
        String introT = "Have a good trip Astronauts!\n" +
                "I hope to see you back";
        Pane beginPane = new Pane();
        Text introText = new Text(introTitle);
        Text introText2 = new Text(introT);

        launchButton = new Button("LAUNCH PROBE!");
        introScene = new Scene(beginPane,screenBounds.getWidth(),screenBounds.getHeight());

        ImageView imageView2 = null;

        try {
            InputStream stream = new FileInputStream("pngegg.png");
            Image image = new Image(stream);

            imageView2 = new ImageView();

            imageView2.setImage(image);

            imageView2.setX(100);
            imageView2.setY(200);
            imageView2.setFitWidth(300);
            imageView2.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ImageView imageView3 = null;

        try {
            InputStream stream = new FileInputStream("planet2.png");
            Image image = new Image(stream);

            imageView3 = new ImageView();

            imageView3.setImage(image);

            imageView3.setX(1200);
            imageView3.setY(300);
            imageView3.setFitWidth(300);
            imageView3.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        introText.setId("introText");
        introText.setTextAlignment(TextAlignment.CENTER);
        introText.setX(screenBounds.getWidth()/2 - 400);
        introText.setY(screenBounds.getHeight()/2 - 200);

        introText2.setId("introText");
        introText2.setTextAlignment(TextAlignment.CENTER);
        introText2.setX(200);
        introText2.setY(800);

        launchButton.setOnAction((EventHandler<ActionEvent>) this);
        launchButton.setPrefSize(310, 75);
        launchButton.setLayoutX(628);
        launchButton.setLayoutY(560);



        beginPane.getChildren().add(introText);
        beginPane.getChildren().add(introText2);
        beginPane.getChildren().add(launchButton);
        beginPane.getChildren().add(imageView2);
        beginPane.getChildren().add(imageView3);



        introScene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());
    }

    public void setVisualiserScene() {

        double distancePixel = 3000/screenBounds.getHeight();

        sun = new CelestialBody("Sun", centerX - ((0.680678)/distancePixel),
                centerY - ((1.0800055)/distancePixel), 15);
        sun.getBody().getStyleClass().add("sun");

        earth = new CelestialBody("Earth", centerX - ((147.1922)/distancePixel),
                centerY + ((28.609958)/distancePixel), 10);
        earth.getBody().getStyleClass().add("earth");

        mercury = new CelestialBody("Mercury", centerX + ((0.0060478)/distancePixel),
                centerY + ((68.0180)/distancePixel), 10);
        mercury.getBody().getStyleClass().add("mercury");

        venus = new CelestialBody("Venus", centerX - ((94.3535)/distancePixel),
                centerY - ((53.5035)/distancePixel), 10);
        venus.getBody().getStyleClass().add("venus");

        moon = new CelestialBody("Moon", centerX - ((147.2344)/distancePixel),
                centerY + ((28.2258)/distancePixel) ,5);
        moon.getBody().getStyleClass().add("moon");


        mars = new CelestialBody("Mars", centerX - ((36.1564)/distancePixel),
                centerY + ((216.7633)/ distancePixel), 10);
        mars.getBody().getStyleClass().add("mars");

        jupiter = new CelestialBody("Jupiter", centerX + ((178.1303)/distancePixel),
                centerY + ((755.1118)/ distancePixel), 10);
        jupiter.getBody().getStyleClass().add("jupiter");

        saturn = new CelestialBody("Saturn", centerX + ((632.8647)/ distancePixel),
                centerY + ((1358.1728)/ distancePixel), 10);
        saturn.getBody().getStyleClass().add("saturn");

        titan = new CelestialBody("Titan", centerX + ((633.2873)/ distancePixel),
                centerY + ((1357.1756)/distancePixel), 5);
        titan.getBody().getStyleClass().add("titan");

        timeText = new Text();
        timeText.setId("timeText");

        root = new BorderPane();
        root.setAlignment(timeText,Pos.BOTTOM_RIGHT);
        root.setBottom(timeText);
        root.getChildren().addAll(sun.getBody(), earth.getBody(), mercury.getBody(),
                venus.getBody(), moon.getBody(), mars.getBody(), jupiter.getBody(),
                saturn.getBody(), titan.getBody());

        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());
    }

    /*
     * Times and updates the probe journey continuously - seconds since launch.
     * (at the moment, it's not configured properly, but should be easy to use the actual
     * probe travel variable, instead of this 'seconds' variable)
     */
    int seconds = 0;
    String ssl;
    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        public void run() {
            seconds++;
            ssl = "Time Past Since Launch: " + seconds + "sec";
            timeText.setText(ssl);
        }
    };

    /*
     * Handles all button actions in one single method.
     */
    public void handle(ActionEvent e) {
        if(e.getSource() == launchButton) {
            singleStage.setScene(visualiserScene);
            singleStage.setFullScreen(true);
            singleStage.setResizable(false);
            singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
            timer.scheduleAtFixedRate(task,0,1000);
        }
    }

    /*
     * Creates animation for earth.
     */
    public static void transition(State position) {
        SolarSystem solar = position.getSolarSystem();
        Planet earthPlanet = solar.get(3);
        Vector3d newPosition = (Vector3d) earthPlanet.getPos();

        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(1));
        transition.setToX(newPosition.getX());
        transition.setToY(newPosition.getY());
        transition.setNode(earth.getBody());
        transition.play();
    }
}
