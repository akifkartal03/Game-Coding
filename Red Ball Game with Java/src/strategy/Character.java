package strategy;

/***
 * Abstract Character class
 */
public abstract class Character {

	// has a relation with jump behaviour
    private JumpBehavior jumpBehavior;

	/***
	 * Increment y coordinate of character
	 * @param distance distance to be increment
	 */
    public abstract void jumpChr(int distance);

	/***
	 * Decrement y coordinate of character
	 * @param distance distance to be decremented
	 */
    public abstract void fallChr(int distance);

    public void setJumpBehavior(JumpBehavior jumpBehavior) {
        this.jumpBehavior = jumpBehavior;
    }

    public JumpBehavior getJumpBehavior() {
        return jumpBehavior;
    }


}
