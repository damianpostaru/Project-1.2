package GUI;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.ArcTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GuiMain extends Application implements EventHandler<ActionEvent>{

    static CelestialBody sun, earth;
    static Rectangle2D screenBounds;
    private Stage singleStage;
    private Scene introScene;
    private Scene visualiserScene;
    private Button launchButton;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	
    	singleStage = primaryStage;    	
        singleStage.setTitle("A Titanic Space Odyssey!");
        singleStage.setResizable(false);
        
        // Sets all the scenes that will be (eventually) seen.
        setIntroScene();
        setVisualiserScene();

        singleStage.setScene(introScene);
        singleStage.show();
    }
    
    public void setIntroScene() {
    	String introTitle = "     A Titanic" + System.getProperty("line.separator") + "Space Odyssey!";
    	Pane beginPane = new Pane();
	Text introText = new Text(introTitle);
	launchButton = new Button("LAUNCH PROBE!");
	introScene = new Scene(beginPane,640,480);
		
	introText.setId("introText");
	introText.setX(40);
	introText.setY(120);
		
    	launchButton.setOnAction((EventHandler<ActionEvent>) this);
    	launchButton.setPrefSize(310, 75);
    	launchButton.setLayoutX(166);
    	launchButton.setLayoutY(320);

	beginPane.getChildren().add(introText);
	beginPane.getChildren().add(launchButton);
    	
    	introScene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());
    }
    
    public void setVisualiserScene() {

        screenBounds = Screen.getPrimary().getBounds();

        sun = new CelestialBody("Sun", screenBounds.getWidth()/2,
                screenBounds.getHeight()/2);
        sun.getBody().getStyleClass().add("sun");

        earth = new CelestialBody("Earth", screenBounds.getWidth()/4,
                screenBounds.getHeight()/2);
        earth.getBody().getStyleClass().add("earth");
        
        transition();
    	
        BorderPane root = new BorderPane();
        root.getChildren().addAll(sun.getBody(), earth.getBody());
        
        visualiserScene = new Scene(root, screenBounds.getWidth(), screenBounds.getHeight());
        visualiserScene.getStylesheets().add(GuiMain.class.getResource("/Stylesheet.css").toExternalForm());
    }
    
    /*
     * Handles all button actions in one single method.
     */
    public void handle(ActionEvent e) {
    	if(e.getSource() == launchButton) {
    		singleStage.setScene(visualiserScene);
            singleStage.setFullScreen(true);
            singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
    	}
    }

    /*
     * Creates animation for earth.
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
