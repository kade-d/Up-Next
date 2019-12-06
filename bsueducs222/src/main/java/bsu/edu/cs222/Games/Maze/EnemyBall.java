package bsu.edu.cs222.Games.Maze;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

class EnemyBall {

    private Circle circle = new Circle();

    EnemyBall() {
        Color color = Color.RED;
        double radius = 15.0;
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

    void setVisible() {
        circle.setVisible(true);
    }

    void setParent(Pane pane) {
        pane.getChildren().add(circle);
    }

}
