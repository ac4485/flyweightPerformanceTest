package project.Simulation.NonFlyWeight.Ants;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import project.Simulation.NonFlyWeight.Food;
import project.Simulation.NonFlyWeight.Land;

public class AntWorker extends Ant {

    private boolean energyLoaded;
    private Circle energyIndicator;
    private AntQueen antQ;
    private Food candy;

    private int MAXSPEED = 10;

    public AntWorker(Land ld, AntQueen antQ, Food candy) {
        super(ld, new Image(Land.class.getResource("../../../img/ant.jpg").toString()));
        this.antQ = antQ;
        this.candy = candy;
        this.loc = this.antQ.getLoc();
        this.energyIndicator = new Circle(2, Color.RED);
        this.setIndicatorColor(Color.RED);
        this.setIconPosition();
        this.energyLoaded = false;
    }

    @Override
    public void move() {
        if (this.energyLoaded) {
            locTo(this.antQ.getLoc());
        } else {
            locTo(this.candy.getLoc());
        }
        if (Land.attached(this, this.antQ)) {
            this.energyLoaded = false;
            this.setIndicatorColor(Color.RED);
        }
        if (Land.attached(this, this.candy)) {
            this.energyLoaded = true;
            this.setIndicatorColor(Color.GREEN);
        }
        setIconPosition();
    }
    
    @Override
    public void addToLand() {
        super.addToLand();
        this.ld.addNodeLand(this.energyIndicator);
    }

    @Override
    public void removeToLand() {
        super.removeToLand();
        this.ld.removeNodeLand(this.energyIndicator);
    }

    @Override
    protected void setIconPosition() {
        double iconCornerX = this.loc.getX() - this.iconView.boundsInParentProperty().get().getWidth() / 2;
        double iconCornerY = this.loc.getY() - this.iconView.boundsInParentProperty().get().getHeight() / 2;
        double indicatorCornerX = this.loc.getX() -
                this.energyIndicator.boundsInParentProperty().get().getWidth() / 2;
        this.iconView.relocate(iconCornerX, iconCornerY);
        this.energyIndicator.relocate(indicatorCornerX - (double) ICON_WIDTH / 3, this.loc.getY() - (double) ICON_HEIGHT / 3);
    }

    private void setIndicatorColor(Color c) {
        this.energyIndicator.setStrokeWidth(0);
        this.energyIndicator.setStroke(c);
        this.energyIndicator.setFill(c);
    }

    private void locTo(Point2D pos) {
        double vecX = pos.getX() - this.loc.getX();
        double vecY = pos.getY() - this.loc.getY();
        double len = Math.sqrt(Math.pow(vecX, 2) + Math.pow(vecY, 2));
        Random r = new Random();
        double rX = Math.min(this.ld.getWidth(), this.loc.getX() + vecX / len * MAXSPEED * r.nextDouble());
        double rY = Math.min(this.ld.getHeight(), this.loc.getY() + vecY / len * MAXSPEED * r.nextDouble());
        Point2D res = new Point2D(rX, rY);

        while (!this.isInBound(res)) {
            rX = Math.min(this.ld.getWidth(), this.loc.getX() + vecX / len * MAXSPEED * r.nextDouble());
            rY = Math.min(this.ld.getHeight(), this.loc.getY() + vecY / len * MAXSPEED * r.nextDouble());
            res = new Point2D(rX, rY);
        }
        this.loc = res;
    }

    private boolean isInBound(Point2D loc) {
        double xx = loc.getX();
        double yy = loc.getY();
        return (xx > ICON_WIDTH / 2 && yy > ICON_HEIGHT / 2 && xx < this.ld.getWidth() - ICON_WIDTH / 2
                && yy < this.ld.getHeight() - ICON_HEIGHT / 2);
    }


}
