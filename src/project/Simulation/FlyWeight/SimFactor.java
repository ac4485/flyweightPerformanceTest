package project.Simulation.FlyWeight;

import java.util.Random;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class SimFactor {
    protected Point2D loc;
    protected Land ld;

        /**
     * For display the icon.
     */
    protected static final int ICON_WIDTH = 30;
    protected static final int ICON_HEIGHT = 27;
    protected ImageView iconView;

    protected SimFactor(Land ld,Image img) {
        this.ld = ld;
        this.iconView = new ImageView(img);
        this.iconView.setFitWidth(ICON_WIDTH);
        this.iconView.setFitHeight(ICON_HEIGHT);
    }
    public Point2D getLoc() {
        return this.loc;
    }
    public void addToLand() {
        this.ld.addNodeLand(this.iconView);
    }

    public void removeToLand() {
        this.ld.removeNodeLand(this.iconView);
    }

}
