package titan_lander.gui;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import titan.space.Vector3d;

import java.awt.*;

//Class to visualize a path, will create a window and show the lander at each position including the previous path

public class LanderVisualizer extends Application
{
    protected  static Vector3d[] landerPath;

    protected static Stage singleStage;
    static Rectangle2D screenBounds;
    public static double centerX, centerY;
    protected static Scene introScene, visualiserScene;
    protected static Button beginButton, probeLaunch, exitButton;
    public static VBox buttonBox;
    @Override
    public void start(Stage primaryStage)
    {
        centerX = 960;//position of the landing pad
        centerY = 960;

        singleStage = primaryStage;
        singleStage.setTitle("Titan Landing!");
        screenBounds = Screen.getPrimary().getBounds();

        centerX = screenBounds.getWidth() / 2;
        centerY = screenBounds.getHeight() / 2;

        LanderIntroScene.setIntroScene();
        LanderSimScene.setVisualizerScene();

        //get the path of the lander from the simulation:

        //temporary solution until we made the simulation work
        landerPath = new Vector3d[1000];
        double height = 10000;

        for (int i = 0; i < landerPath.length; i++)
        {
            landerPath[i] = new Vector3d(0,height - (i * (height/landerPath.length)),0);
        }

        singleStage.setFullScreen(true);
        singleStage.setResizable(false);
        singleStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        singleStage.setScene(introScene);
        singleStage.show();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
