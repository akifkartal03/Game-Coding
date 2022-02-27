package strategy;

/***
 * Concrete low jump behavior.
 */
public class LowJump implements JumpBehavior {

    @Override
    public void jump(Coordinates coor, int distance) {
        coor.setyStart(coor.getyStart() - distance);
        coor.getChr().y = coor.getyStart();

    }

    @Override
    public void fall(Coordinates coor, int distance) {
        coor.setyStart(coor.getyStart() + distance);
        coor.getChr().y = coor.getyStart();

    }

    @Override
    public String getType() {
        return "low jump";
    }


}
