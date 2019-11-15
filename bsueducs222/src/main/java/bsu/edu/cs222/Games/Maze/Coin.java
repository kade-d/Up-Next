package bsu.edu.cs222.Games.Maze;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Coin {

    private Circle circle = new Circle();

    Coin() {
        Color color = Color.YELLOW;
        double radius = 10.0;
        circle.setRadius(radius);
        circle.setFill(color);
        circle.setCenterX(0);
        circle.setCenterY(0);
    }

    void setTranslateX(double x) {
        circle.setTranslateX(x);
    }

    void setTranslateY(double y) {
        circle.setTranslateY(y);
    }

    double getTranslateX() {
        return circle.getTranslateX();
    }

    double getTranslateY() {
        return circle.getTranslateY();
    }

    double getRadius() {
        return circle.getRadius();
    }

    void setVisible(boolean bool) {
        circle.setVisible(bool);
    }

    void setParent(Pane pane) {
        pane.getChildren().add(circle);
    }

}
