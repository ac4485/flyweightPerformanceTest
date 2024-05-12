package project.Simulation.FlyWeight.Ants;

import javafx.scene.image.Image;
import project.Simulation.FlyWeight.Land;
import project.Simulation.FlyWeight.SimFactor;

public abstract class Ant extends SimFactor{

    protected Ant(Land ld, Image img) {
        super(ld, img);
    }
    public abstract void move();
    protected abstract void setIconPosition();
}
