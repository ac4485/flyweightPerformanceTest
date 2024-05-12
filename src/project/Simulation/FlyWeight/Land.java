package project.Simulation.FlyWeight;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import project.Simulation.FlyWeight.Ants.AntQueen;

public class Land extends Application {

    private Set<AntQueen> antSet = new HashSet<AntQueen>();
    // private Food food;
    // private AntQueen aq1;
    private Pane basePane;

    /**
     * Please change the default window width here.
     */
    private static int WINWIDTH = 800;

    /**
     * Please change the default window height here.
     */
    private static int WINHEIGHT = 600;

    /**
     * Please change the poillinate trigger distance here.
     */
    private static int TRIGGER_DISTANCE = 40;

    /**
     * The main class.
     * 
     * @param args
     */
    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primStage) throws Exception {
        this.initGUI(primStage);
        Land.setStage(primStage);
        primStage.show();
        addAntEco();
        // long usedMem = 0;
        // List<Long> mems = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            this.arrowEventHandler();
            // usedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
            // mems.add(usedMem);
            // System.out.println(usedMem);
        }

        // FileWriter f = new FileWriter("./flyweight.csv");
        //     for (Long mem: mems){
        //         f.write(Long.toString(mem) + ',');
        //     }
        //     f.close();
        // System.exit(0);
        primStage.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
        if (e.getCode() == KeyCode.RIGHT) {
        this.arrowEventHandler();
        }
        });
        
    }

    public void nextTurn() {
        for (AntQueen aa : this.antSet) {
            aa.move();
        }
    }

    public void addAntEco() {
        try {
            this.addAntQueen(new AntQueen(this, new Point2D(100, 100),
                    new Food(this, new Point2D(500, 100))));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addAntQueen(AntQueen aq1) {
        aq1.addToLand();
        this.antSet.add(aq1);
    }

    public void removeAntQueen(AntQueen aq1) {
        aq1.removeToLand();
        this.antSet.remove(aq1);
    }

    public void addNodeLand(Node node) {
        this.basePane.getChildren().addAll(node);
    }

    public void removeNodeLand(Node node) {
        this.basePane.getChildren().addAll(node);
    }

    public double getWidth() {
        return this.basePane.getWidth();
    }

    public double getHeight() {
        return this.basePane.getHeight();
    }

    /**
     * Determine whether the Flower and the Bee is attached or not.
     * 
     * @param p1
     * @param p2
     * @return
     */
    public static boolean attached(SimFactor a1, SimFactor a2) {
        Point2D p1 = a1.getLoc();
        Point2D p2 = a2.getLoc();
        return Math.abs(p1.getX() - p2.getX()) < TRIGGER_DISTANCE &&
                Math.abs(p1.getY() - p2.getY()) < TRIGGER_DISTANCE;
    }

    private void arrowEventHandler() {
        for (AntQueen aw : this.antSet) {
            aw.move();
        }
    }

    private void initGUI(Stage st) {
        this.basePane = new Pane();
        this.basePane.setFocusTraversable(true);
        st.setScene(new Scene(basePane));
    }

    private static void setStage(Stage st) {
        st.setWidth(WINWIDTH);
        st.setHeight(WINHEIGHT);
        st.setTitle("Flyweight");
        st.setResizable(true);
    }

}