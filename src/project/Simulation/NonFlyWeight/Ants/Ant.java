package project.Simulation.NonFlyWeight.Ants;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import project.Simulation.NonFlyWeight.Land;
import project.Simulation.NonFlyWeight.SimFactor;

public abstract class Ant extends SimFactor{

    protected Ant(Land ld, Image img) {
        super(ld, img);
    }
    public abstract void move();
    protected abstract void setIconPosition();
}
