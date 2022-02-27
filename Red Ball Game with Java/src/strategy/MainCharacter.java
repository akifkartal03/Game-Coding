package strategy;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;

/***
 * Concrete main character.
 */

public class MainCharacter extends Character {

    private long totalPoint;
    private int totalLife;
    private Coordinates cor;

    public MainCharacter() {
        //initially it jumps low
        setJumpBehavior(new LowJump());
        totalLife = 3;
        totalPoint = 0;
        cor = new Coordinates();
    }


    public Rectangle getCharacter() {
        return cor.getChr();
    }

    public Coordinates getCoordinates() {
        return cor;
    }

	/***
	 * Paint main character(circle) on screen using Graphics object.
	 * @param g Graphics object
	 */
    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3f));
        g2.setColor(Color.decode("#f84545"));
        g2.fill(new Ellipse2D.Double(150, cor.getyStart(), 30, 30));
    }

    public void decrementLife() {
        totalLife--;
    }

    public void incremetPoint(long point) {
        totalPoint += point;
    }

    public long getTotalPoint() {
        return totalPoint;
    }


    public int getTotalLife() {
        return totalLife;
    }

	/***
	 * Main character jumps using jump behaviour
	 * @param distance distance to be jump above
	 */
    @Override
    public void jumpChr(int distance) {
        //use jump behavior
        getJumpBehavior().jump(cor, distance);
    }

	/***
	 * Main character falls using fall behaviour
	 * @param distance distance to be fall below
	 */
    @Override
    public void fallChr(int distance) {
        //use fall behavior
        getJumpBehavior().fall(cor, distance);

    }


}
