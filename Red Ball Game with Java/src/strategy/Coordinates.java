package strategy;

import java.awt.Rectangle;

/***
 * Coordinates of the main character
 */
public class Coordinates {

    private Rectangle chr;
    private int yStart;

    public Coordinates() {

        chr = new Rectangle();
        yStart = 204;
        chr.x = 150;
        chr.y = yStart;
        chr.width = 35;
        chr.height = 35;
    }

    public Rectangle getChr() {
        return chr;
    }

    public int getyStart() {
        return yStart;
    }

    public void setyStart(int yStart) {
        this.yStart = yStart;
    }

}
