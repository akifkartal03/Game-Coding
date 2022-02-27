package gui;

import java.awt.Rectangle;

/***
 * Position of the Obstacles
 */
public class ObsPosition {
    private int xStart;
    //to catch collisions
    private Rectangle obs;

    public ObsPosition() {
        xStart = 200;
        obs = new Rectangle();
        obs.width = 10;
        obs.height = 69;
        obs.x = xStart;
        obs.y = 160;
    }

    public ObsPosition(int xStart, Rectangle obs) {
        this.xStart = xStart;
        this.obs = obs;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
        obs.x = xStart;
    }

    public Rectangle getObs() {
        return obs;
    }

    public void setObs(Rectangle obs) {
        this.obs = obs;
    }


}
