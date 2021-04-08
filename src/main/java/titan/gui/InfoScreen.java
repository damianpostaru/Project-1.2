package titan.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;


public class InfoScreen extends GuiMain {

    public static Label planetLabel, sunLabel, mercuryLabel, venusLabel, earthLabel, moonLabel, marsLabel, jupiterLabel, saturnLabel, titanLabel, probeLabel;

    public static Circle sun2, mercury2, venus2, earth2, moon2, mars2, jupiter2, saturn2, titan2, probe2;

    public static void run() {
        labelCreator();
        circleCreator();

        probeLaunch = new Button("Launch Probe!");
        exitButton = new Button("Exit Simulation!");
        exitButton.setOnAction(e -> {
            System.exit(0);
        });

        sunBox = new HBox(25);
        sunBox.getChildren().addAll(sunLabel, sun2);

        mercuryBox = new HBox(25);
        mercuryBox.getChildren().addAll(mercuryLabel, mercury2);

        venusBox = new HBox(25);
        venusBox.getChildren().addAll(venusLabel, venus2);

        earthBox = new HBox(25);
        earthBox.getChildren().addAll(earthLabel, earth2);

        moonBox = new HBox(25);
        moonBox.getChildren().addAll(moonLabel, moon2);

        marsBox = new HBox(25);
        marsBox.getChildren().addAll(marsLabel, mars2);

        jupiterBox = new HBox(25);
        jupiterBox.getChildren().addAll(jupiterLabel, jupiter2);

        saturnBox = new HBox(25);
        saturnBox.getChildren().addAll(saturnLabel, saturn2);

        titanBox = new HBox(25);
        titanBox.getChildren().addAll(titanLabel, titan2);

        probeBox = new HBox(25);
        probeBox.getChildren().addAll(probeLabel, probe2);

        infoBox = new VBox(screenBounds.getHeight()/(13*2));
        infoBox.getChildren().addAll(planetLabel, sunBox, mercuryBox, venusBox, earthBox, moonBox, marsBox, jupiterBox, saturnBox, titanBox, probeBox, probeLaunch, exitButton);
        infoBox.setPadding(new Insets(0, 50, 0, 0));
        infoBox.getStyleClass().add("Label");
        infoBox.setAlignment(Pos.CENTER);
    }

    public static void labelCreator() {
        planetLabel = new Label("Planets: ");
        planetLabel.getStyleClass().add("planetLabel");

        sunLabel = new Label("Sun");
        sunLabel.getStyleClass().add("planetLabel");

        mercuryLabel = new Label("Mercury");
        mercuryLabel.getStyleClass().add("planetLabel");

        venusLabel = new Label("Venus");
        venusLabel.getStyleClass().add("planetLabel");

        earthLabel = new Label("Earth");
        earthLabel.getStyleClass().add("planetLabel");

        moonLabel = new Label("Moon");
        moonLabel.getStyleClass().add("planetLabel");

        marsLabel = new Label("Mars");
        marsLabel.getStyleClass().add("planetLabel");

        jupiterLabel = new Label("Jupiter");
        jupiterLabel.getStyleClass().add("planetLabel");

        saturnLabel = new Label("Saturn");
        saturnLabel.getStyleClass().add("planetLabel");

        titanLabel = new Label("Titan");
        titanLabel.getStyleClass().add("planetLabel");

        probeLabel = new Label("Probe");
        probeLabel.getStyleClass().add("planetLabel");
    }

    public static void circleCreator() {
        sun2 = new Circle(15);
        sun2.getStyleClass().add("sun");

        mercury2 = new Circle(6);
        mercury2.getStyleClass().add("mercury");

        venus2 = new Circle(7);
        venus2.getStyleClass().add("venus");

        earth2 = new Circle(11);
        earth2.getStyleClass().add("earth");

        moon2 = new Circle(5);
        moon2.getStyleClass().add("moon");

        mars2 = new Circle(7);
        mars2.getStyleClass().add("mars");

        jupiter2 = new Circle(13);
        jupiter2.getStyleClass().add("jupiter");

        saturn2 = new Circle(13);
        saturn2.getStyleClass().add("saturn");

        titan2 = new Circle(6);
        titan2.getStyleClass().add("titan");

        probe2 = new Circle(4);
        probe2.getStyleClass().add("probe");
    }
}
