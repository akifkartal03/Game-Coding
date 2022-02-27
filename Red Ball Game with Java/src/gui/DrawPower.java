package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import decorator.APower;
import decorator.BPower;
import decorator.CPower;
import decorator.DPower;
import decorator.PowerDecorator;

/**
 * Draw Powers on screen at random positions
 * */
public class DrawPower {
    //list of powers
    private List<PowerDecorator> powerList;
    private Random randomPosition;

    public DrawPower() {
        powerList = new ArrayList<PowerDecorator>();
        randomPosition = new Random();
        initPowers();
    }
    /**
    * Init position and type of powers
    * */
    public void initPowers() {
        for (int i = 0; i < 5; i++) {
            PowerDecorator power1 = getRandomPower();
            power1.setxStart((i + 1) * getRandomPosition());
            powerList.add(power1);
        }
    }

    /**
     * Update position and type of powers
     * */
    public void updatePowers(int distance) {

        for (PowerDecorator stone : powerList) {
            stone.setxStart(stone.getxStart() - distance);
        }
        PowerDecorator stone2 = powerList.get(0);
        if (stone2.getxStart() + 21 < 0) {
            stone2 = getRandomPower();
            stone2.setxStart(powerList.get(powerList.size() - 1).getxStart() + getRandomPosition() + 30);
            powerList.add(stone2);
            powerList.remove(0);
        }

    }
    /**
     * Check collision with main character.
     * @param chr main character
     * */
    public PowerDecorator isIntersects(Rectangle chr) {
        for (PowerDecorator stone : powerList) {
            if (stone.getPowerArea().intersects(chr)) {
                return stone;
            }
        }
        return null;
    }

    /***
     * Paint powers on screen using Graphics object.
     * @param g Graphics object
     */
    public void draw(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(1f));
        g2.setColor(Color.BLACK);
        for (PowerDecorator obst : powerList) {
            g.drawString(obst.getName(), obst.getxStart() + 7, 228);
            g2.draw(obst.getPowerArea());
        }

    }

    /***
     * Get random number between 400-800
     * @return random number between 400-800
     */
    public int getRandomPosition() {
        //get random number between 400-800
        return randomPosition.nextInt((800) - 400) + 400;
    }

    /***
     * Get random power type
     * @return random power type
     */
    public PowerDecorator getRandomPower() {

        int rnd = randomPosition.nextInt(4);
        switch (rnd) {
            case 0:
                return new APower();
            case 1:
                return new BPower();
            case 2:
                return new CPower();
            case 3:
                return new DPower();
            default:
                return new CPower();
        }
    }

    /***
     * Get whole power list.
     * @return whole power list.
     */
    public List<PowerDecorator> getPowerList() {
        return powerList;
    }


}
