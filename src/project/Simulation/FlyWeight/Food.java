package project.Simulation.FlyWeight;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class Food extends SimFactor {

    private static Image img = new Image(Land.class.getResource("../../../img/candy.jpg").toString());
    public Food(Land ld,Point2D pos) {
        super(ld, Food.img);
        this.loc = pos;
        this.setIconPosition();
    }
    
    protected void setIconPosition() {
        double iconCornerX = this.loc.getX() - this.iconView.boundsInParentProperty().get().getWidth() / 2;
        double iconCornerY = this.loc.getY() - this.iconView.boundsInParentProperty().get().getHeight() / 2;
        // double indicatorCornerX = this.loc.getX() - this.energyIndicator.boundsInParentProperty().get().getWidth() / 2;
        this.iconView.relocate(iconCornerX, iconCornerY);
        // this.energyIndicator.relocate(indicatorCornerX, this.loc.getY() - (double) ICON_HEIGHT / 2);
    }
    
}
