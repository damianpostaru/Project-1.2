package titan.gui;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class InfoScreen extends GuiMain {

    public static Label[] planetLabels = new Label[13];//planetLabel, sunLabel, mercuryLabel, venusLabel, earthLabel, moonLabel,
   // marsLabel, jupiterLabel, saturnLabel, titanLabel, probeLabel, uranusLabel, neptuneLabel;

    public static Circle[] circles = new Circle[12];//sun2, mercury2, venus2, earth2, moon2, mars2, jupiter2, saturn2, titan2,
    // probe2, uranus2, neptune2;

    public static void run() {
        labelCreator();
        circleCreator();

        for (int q = 1; q < planetLabels.length; q++) {
            planetContainers[q-1] = new HBox(25);
            planetContainers[q-1].getChildren().addAll(planetLabels[q], circles[q-1]);
        }


        infoBox = new VBox(screenBounds.getHeight()/(20*2));
        ObservableList<Node> children = infoBox.getChildren();
        children.add(planetLabels[0]);
        for (int i = 0; i < planetContainers.length; i++) {
            children.add(planetContainers[i]);
        }
        children.addAll(probeLaunch,exitButton);
        infoBox.setPadding(new Insets(0, 50, 0, 0));
        infoBox.getStyleClass().add("Label");
        infoBox.setAlignment(Pos.CENTER);
    }

    public static void labelCreator() {
        planetLabels[0] = new Label("Planets: ");
        planetLabels[0].getStyleClass().add("planetLabel");

        planetLabels[1] = new Label("Sun");
        planetLabels[1].getStyleClass().add("planetLabel");

        planetLabels[2] = new Label("Mercury");
        planetLabels[2].getStyleClass().add("planetLabel");

        planetLabels[3] = new Label("Venus");
        planetLabels[3].getStyleClass().add("planetLabel");

        planetLabels[4] = new Label("Earth");
        planetLabels[4].getStyleClass().add("planetLabel");

        planetLabels[5] = new Label("Moon");
        planetLabels[5].getStyleClass().add("planetLabel");

        planetLabels[6] = new Label("Mars");
        planetLabels[6].getStyleClass().add("planetLabel");

        planetLabels[7] = new Label("Jupiter");
        planetLabels[7].getStyleClass().add("planetLabel");

        planetLabels[8] = new Label("Saturn");
        planetLabels[8].getStyleClass().add("planetLabel");

        planetLabels[9] = new Label("Titan");
        planetLabels[9].getStyleClass().add("planetLabel");

        planetLabels[10] = new Label("Probe");
        planetLabels[10].getStyleClass().add("planetLabel");

        planetLabels[11] = new Label("Uranus");
        planetLabels[11].getStyleClass().add("planetLabel");

        planetLabels[12] = new Label("Neptune");
        planetLabels[12].getStyleClass().add("planetLabel");
    }

    public static void circleCreator() {
        circles[0] = new Circle(15);
        circles[0].getStyleClass().add("sun");

        circles[1] = new Circle(6);
        circles[1].getStyleClass().add("mercury");

        circles[2] = new Circle(7);
        circles[2].getStyleClass().add("venus");

        circles[3] = new Circle(11);
        circles[3].getStyleClass().add("earth");

        circles[4] = new Circle(5);
        circles[4].getStyleClass().add("moon");

        circles[5] = new Circle(7);
        circles[5].getStyleClass().add("mars");

        circles[6] = new Circle(13);
        circles[6].getStyleClass().add("jupiter");

        circles[7] = new Circle(13);
        circles[7].getStyleClass().add("saturn");

        circles[8] = new Circle(6);
        circles[8].getStyleClass().add("titan");

        circles[9] = new Circle(4);
        circles[9].getStyleClass().add("probe");

        circles[10] = new Circle(15);
        circles[10].getStyleClass().add("uranus");

        circles[11] = new Circle(15);
        circles[11].getStyleClass().add("neptune");
    }
}
