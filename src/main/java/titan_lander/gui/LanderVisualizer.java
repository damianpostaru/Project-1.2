package titan_lander.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.Path;
import javafx.stage.Screen;
import javafx.stage.Stage;
import titan.space.Vector3d;

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
    public static Group pathLines;
    public static Path landerPath;
    @Override
    public void start(Stage primaryStage)
    {
        //get the path of the lander from the simulation:

        //temporary solution until we made the simulation work
        landerPathVectors = new Vector3d[1000];
        double height = 100000;

        for (int i = 0; i < landerPathVectors.length; i++)
        {
            landerPathVectors[i] = new Vector3d(0,height - (i * (height/ landerPathVectors.length)),0);
        }
        landerPathVectors[0] = new Vector3d(0,100000,0);
        landerPathVectors[1] = new Vector3d(0,0,0);


        singleStage = primaryStage;
        singleStage.setTitle("Titan Landing!");
        screenBounds = Screen.getPrimary().getBounds();

        //position of the landing pad
        centerX = 100;
        centerY = screenBounds.getHeight();

        LanderIntroScene.setIntroScene();
        LanderSimScene.setVisualizerScene();



        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        singleStage.setScene(introScene);
        singleStage.show();
    }

    protected static Vector3d metersToPixels(Vector3d pos)
    {
        Vector3d newVector = new Vector3d(centerX + pos.getX() * CONVERSION_FACTOR ,centerY - pos.getY() * CONVERSION_FACTOR,0);
        return newVector;
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
