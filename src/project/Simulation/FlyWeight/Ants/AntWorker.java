package project.Simulation.FlyWeight.Ants;

import javafx.scene.image.Image;
import project.Simulation.FlyWeight.Land;

public abstract class AntWorker extends Ant{

    private static Image img = new Image(Land.class.getResource("../../../img/ant.jpg").toString());
    protected static int MAXSPEED = 10;
    protected AntWorker(Land ld) {
        super(ld, AntWorker.img);
    }

    // @Override
    // public void addToLand() {
    //     super.addToLand();
        
    // }

    // @Override
    // public void removeToLand() {
    //     super.removeToLand();
    // }
    // @Override
    // public void move() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'move'");
    // }

    // @Override
    // protected void setIconPosition() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'setIconPosition'");
    // }
    
}
