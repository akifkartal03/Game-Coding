package strategy;

/***
 * Jump behavior of the character
 */
public interface JumpBehavior {

	/***
	 * Increment y coordinate of character
	 * @param coor coordinates of character
	 * @param distance to be incremented
	 */
    void jump(Coordinates coor, int distance);

	/***
	 * Decrement y coordinate of character
	 * @param coor coordinates of character
	 * @param distance to be decremented
	 */
    void fall(Coordinates coor, int distance);

	/***
	 * Get type of jump
	 * @return type of jump as a string
	 */
    String getType();

}
