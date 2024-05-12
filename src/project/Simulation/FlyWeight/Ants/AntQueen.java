package project.Simulation.FlyWeight.Ants;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import project.Simulation.FlyWeight.Food;
import project.Simulation.FlyWeight.Land;

public class AntQueen extends Ant {

    private Set<AntWorker> antSet = new HashSet<AntWorker>();
    private Food candy;
    private static Image img = new Image(Land.class.getResource("../../../img/antQueen.jpg").toString());
    public AntQueen(Land ld, Point2D pos, Food candy) throws IOException{
        super(ld, AntQueen.img);
        this.loc = pos;
        this.addFood(candy);
        this.candy = candy;
        this.setIconPosition();
        this.addAntEco();
    }

    // public void addAntWorkers(AntWorker awk){
    // this.workers.add(awk);
    // }

    public void addAntEco() throws IOException {
        long usedMem = 0;
        List<Long> mems = new LinkedList<>();
        for (int i = 0; i < 10000; i++) {
            usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            mems.add(usedMem);
            AntWorker aw1 = new AntWorkerDynamic(this.ld,this,this.candy);
            this.addAnt(aw1);
        }
        FileWriter f = new FileWriter("./flyweight.csv");
        for (Long mem: mems){
            f.write(Long.toString(mem) + ',');
        }
        f.close();
    }

    public void addAnt(AntWorker a1) {
        a1.addToLand();
        this.antSet.add(a1);
    }

    public void removeAnt(Ant a1) {
        a1.removeToLand();
        this.antSet.remove(a1);
    }

    public void addFood(Food f) {
        f.addToLand();
        this.candy = f;
    }

    public void removeFood() {
        this.candy.removeToLand();
        this.candy = null;
    }

    public Point2D getFoodPos() {
        return this.candy.getLoc();
    }

    @Override
    public void move() {
        for (AntWorker aa : this.antSet) {
            aa.move();
        }
    }

    @Override
    protected void setIconPosition() {
        double iconCornerX = this.loc.getX() - this.iconView.boundsInParentProperty().get().getWidth() / 2;
        double iconCornerY = this.loc.getY() - this.iconView.boundsInParentProperty().get().getHeight() / 2;
        this.iconView.relocate(iconCornerX, iconCornerY);
    }

    
}
