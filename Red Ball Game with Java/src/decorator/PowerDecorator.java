package decorator;


import java.awt.Rectangle;

/**
 * Abstract Decorator Class for Powers
 * */
public abstract class PowerDecorator extends PowerUP {
    protected PowerUP powertype; //has a relation
    protected int xStart;
    //in order to catch collisions with power
    protected Rectangle powerArea;

    public PowerDecorator() {
        setName("U");
        xStart = 400;
        powerArea = new Rectangle();
        powerArea.width = 20;
        powerArea.height = 20;
        powerArea.x = xStart;
        powerArea.y = 212;

    }

    public abstract String getName();

    public PowerUP getPowertype() {
        return powertype;
    }

    public void setPowertype(PowerUP powertype) {
        this.powertype = powertype;
    }

    public int getxStart() {
        return xStart;
    }

    public void setxStart(int xStart) {
        this.xStart = xStart;
        powerArea.x = xStart;
    }

    public Rectangle getPowerArea() {
        return powerArea;
    }

    public void setPowerArea(Rectangle powerArea) {
        this.powerArea = powerArea;
    }


}
