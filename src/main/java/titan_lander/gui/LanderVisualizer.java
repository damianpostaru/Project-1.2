package titan_lander.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import titan.interfaces.StateInterface;
import titan.space.Vector3d;
import titan_lander.solver.Lander;

//Class to visualize a path, will create a window and show the lander at each position including the previous path

public class LanderVisualizer extends Application
{
    protected  static Vector3d[] landerPathVectors;

    protected static Stage singleStage;
    static Rectangle2D screenBounds;
    public static double centerX, centerY;
    protected static Scene introScene, visualiserScene;
    protected static Button beginButton, probeLaunch, exitButton;
    public static VBox buttonBox;
    static final double CONVERSION_FACTOR = 3.2000e-03;
    public static Pane pathLines;
    public static Path landerPath;
    public static Vector3d firstVector;
    public static Image landerSprite;
    public static ImageView landerView;
    public static final double imgSize = 100;
    public static final double totalTime = 15*1000;
    @Override
    public void start(Stage primaryStage)
    {
        singleStage = primaryStage;
        singleStage.setTitle("Titan Landing!");
        screenBounds = Screen.getPrimary().getBounds();

        //position of the landing pad
        centerX = screenBounds.getWidth()/2;
        centerY = centerX;


        //get the path of the lander from the simulation:

        //temporary solution until we made the simulation work
        landerPathVectors = getPathVectors();

        firstVector = metersToPixels(landerPathVectors[0]);

        LanderIntroScene.setIntroScene();
        LanderSimScene.setVisualizerScene();

        landerSprite = new Image("lander.png");
        landerView = new ImageView(landerSprite);
        setLanderVector(landerPathVectors[0]);
        landerView.setPreserveRatio(true);
        landerView.setFitHeight(imgSize);

        pathLines.getChildren().add(landerView);

        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        singleStage.setScene(introScene);
        singleStage.show();
    }

    protected static Vector3d metersToPixels(Vector3d pos)
    {
        Vector3d newVector = new Vector3d(centerX + pos.getX() * CONVERSION_FACTOR ,centerY - pos.getY() * CONVERSION_FACTOR,pos.getZ());
        return newVector;
    }

    public static void main(String[] args)
    {
        launch(args);
    }

    public static void setLanderVector(Vector3d state)
    {
        Vector3d pixelCoor = metersToPixels(state);
        landerView.setRotate(pixelCoor.getZ());//maybe update to degrees, depends on lander implementation
        landerView.setX(pixelCoor.getX() - imgSize/2);
        landerView.setY(pixelCoor.getY() - imgSize/2);
    }

    private Vector3d[] getPathVectors()
    {
        Vector3d[] output = new Vector3d[1000];
        double height = 200000;
        for (int i = 0; i < output.length; i++) {
        output[i] = new Vector3d(0,height - (Math.pow(1.3,i)),i);
        }
        return output;
    }

    private Vector3d[] getPathVectors(StateInterface[] states){
        Vector3d[] path = new Vector3d[states.length];
        for (int i = 0; i < states.length; i++) {
            path[i] = ((Lander)states[i]).getPosition();
        }
        return path;
    }
}
