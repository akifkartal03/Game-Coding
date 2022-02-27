package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import decorator.APower;
import decorator.BPower;
import decorator.CPower;
import decorator.PowerDecorator;
import decorator.PowerUP;
import decorator.Score;
import strategy.HighJump;
import strategy.LowJump;
import strategy.MainCharacter;

/***
 * This is the game screen to put things on.
 * Also, it works as separate thread to create a game loop.
 */
public class MainJPanel extends JPanel implements Runnable, KeyListener {

    //Game variables
    GameState state;
    private int x;
    private int y;
    private int FPS;
    private int currentFPS;
    MainCharacter character;
    private Road road;
    private Obstacle obs;
    private DrawPower powers;
    private PowerUP score;
    private JTextArea logPanel;
    private int distance;
    private int distance2;
    private boolean isJumped;
    private boolean isGameOver;

    public MainJPanel(JTextArea logArea) {
        super(new BorderLayout());
        this.logPanel = logArea;
        initGame();

    }

    /***
     * Init components of the game.
     */
    public void initGame() {

        road = new Road();
        obs = new Obstacle();
        character = new MainCharacter();
        powers = new DrawPower();
        score = new Score();
        state = GameState.Paused;
        x = 0;
        y = 0;
        FPS = 60;
        currentFPS = 60;
        distance = 55;
        distance2 = 45;
        isJumped = false;
        isGameOver = false;
    }

    /***
     * Thread run method to create game loop
     * with reasonable FPS.
     */
    @Override
    public void run() {
        //FPS = 60
        double fpsMS = 1000000000 / FPS;
        double deltaTime = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long counter = 0;
        int numberOfDraw = 0;

        //game will continue until you exit
        while (true) {
            currentTime = System.nanoTime();
            deltaTime += (currentTime - lastTime) / fpsMS;
            counter += (currentTime - lastTime);
            lastTime = currentTime;

            if (deltaTime >= 1) {

                if (character.getTotalLife() <= 0 && !isGameOver) {

                    // finish game
                    logPanel.append("\nGame Over!!");
                    isGameOver = true;
                    state = GameState.Finished;
                }
                //check collision with obstacle
                if (obs.isIntersects(character.getCharacter()) && distance2 > 40) {

                    if (character.getTotalLife() > 0) {
                        logPanel.append("\nYou lost a life.");
                        character.decrementLife();
                    } else {
                        logPanel.append("\nGame Over");
                        isGameOver = true;
                    }
                    distance2 = 0;
                }
                //check collision with powers
                PowerDecorator result = powers.isIntersects(character.getCharacter());
                if (result != null && distance > 50) {
                    //whip the score
                    wrapCharacter(result);
                    logPanel.append("\n" + result.getName() + " power acquired.");
                    distance = 0;
                }
                repaint();
                deltaTime--;
                numberOfDraw++;
            }

            if (counter >= 1000000000) {
                currentFPS = numberOfDraw;
                numberOfDraw = 0;
                counter = 0;
            }


        }

    }

    /***
     * Paint components on screen
     * @param g Graphics object to paint
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("FPS: " + currentFPS, 10, 20);
        g.drawString("Life: " + character.getTotalLife(), 525, 20);
        g.drawString("Score Multiplier : " + score.multiplier(), 10, 40);
        g.drawString("Total Score: " + character.getTotalPoint(), 10, 60);

        //draw things
        character.draw(g);
        road.draw(g);
        obs.draw(g);
        powers.draw(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // intentionally empty
        // by author akif

    }

    /***
     * Catch keyboard event key pressed
     * @param e keyboard event
     */
    @Override
    public void keyPressed(KeyEvent e) {

        if (state == GameState.Playing) {

            //check jump and update distances
            if (e.getKeyCode() == KeyEvent.VK_SPACE && !isJumped) {
                character.jumpChr(85);
                logPanel.append("\n" + character.getJumpBehavior().getType());
                road.updateRoad(5);
                obs.updateObstacle(5);
                powers.updatePowers(5);
                isJumped = true;
            }
            //check move right
            if (e.getKeyCode() == KeyEvent.VK_D && character.getCoordinates().getyStart()==204) {
                road.updateRoad(5);
                obs.updateObstacle(5);
                powers.updatePowers(5);
                distance += 5;
                distance2 += 5;
            }
            //check overlaps of powers and obstacles
            obs.resolveOverLaps(powers.getPowerList());

        } else if (state == GameState.Paused) {
            JOptionPane.showMessageDialog(null, "Please Start Game from Menu!", "Game Paused", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Game Over!\n"
                    + "Start New Game from Menu", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /***
     * Catch keyboard event key released
     * @param e keyboard event
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (state == GameState.Playing) {

            //check space key and fall
            if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                road.updateRoad(5);
                //fall by moving background
                obs.updateObstacle(80);
                powers.updatePowers(80);
                if (obs.isTrueJump(character.getCoordinates().getChr().x)) {
                    logPanel.append("\nYou got " + score.multiplier() + " point! (1*ScoreMultiplier)");
                    character.incremetPoint(score.multiplier());
                }
                distance += 80;
                distance2 += 80;

                character.fallChr(85);
                isJumped = false;
            }
            //check overlaps of powers and obstacles
            obs.resolveOverLaps(powers.getPowerList());
        }else if (state == GameState.Paused) {
            JOptionPane.showMessageDialog(null, "Please Start Game from Menu!", "Game Paused", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Game Over!\n"
                    + "Start New Game from Menu", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        }



    }

    /***
     * Wrap the score according to acquired power type.
     * @param pwrType acquired power type.
     */
    public void wrapCharacter(PowerDecorator pwrType) {

        switch (pwrType.getName()) {
            case "A":
                score = new APower(score); //Wrap
                break;
            case "B":
                score = new BPower(score); //Wrap
                break;
            case "C":
                score = new CPower(score); // Wrap
                break;
            case "D":  // change jump type
                if (character.getJumpBehavior() instanceof LowJump) {
                    character.setJumpBehavior(new HighJump());
                } else {
                    character.setJumpBehavior(new LowJump());
                }

                break;
            default:
        }
    }

    public GameState getState() {
        return state;
    }

    public void setState(GameState state) {
        this.state = state;
    }


}
