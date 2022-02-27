package gui;

import java.awt.Color;

/**
 * Little rectangles to create a road
 */
public class RoadStone {

    private Color color;
    private int x;

    public RoadStone() {
        this.color = Color.BLUE;
        this.x = 0;
    }

    public RoadStone(Color color, int x) {
        this.color = color;
        this.x = x;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }


}
