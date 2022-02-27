package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Draw road on screen.
 * This class helps the crate moving animation.
 * Road moves,while the character always remains
 * at a fixed spot on the screen
 */
public class Road {

    //stones are rectangles
    private List<RoadStone> stoneList;
    private Random randomColor;

    public Road() {
        stoneList = new ArrayList<RoadStone>();
        randomColor = new Random();
        for (int i = 0; i < 13; i++) {
            RoadStone stone1 = new RoadStone();
            stone1.setX(i * 50);
            stone1.setColor(getRandomColor());
            stoneList.add(stone1);
        }
    }

    /***
     * Paint road on screen using Graphics object.
     * @param g Graphics object
     */
    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(10f));

        for (RoadStone stone : stoneList) {
            g2.setColor(stone.getColor());
            g2.draw(new Line2D.Double(stone.getX(), 239, stone.getX() + 51, 239));
        }

    }

    /**
     * Update position of road
     * Stones are simple rectangles
     * */
    public void updateRoad(int distance) {
        for (RoadStone stone : stoneList) {
            stone.setX(stone.getX() - distance);
        }
        RoadStone stone2 = stoneList.get(0);
        if (stone2.getX() + 50 < 0) {
            stone2.setX(stoneList.get(stoneList.size() - 1).getX() + 50);
            stoneList.add(stone2);
            stoneList.remove(0);
        }
    }

    /***
     * Get random color to paint on screen
     * @return random color
     */
    public Color getRandomColor() {
        int number = randomColor.nextInt(3);
        switch (number) {
            case 0:
                return Color.ORANGE;
            case 1:
                return Color.GREEN;
            default:
                return Color.BLUE;
        }
    }

}
