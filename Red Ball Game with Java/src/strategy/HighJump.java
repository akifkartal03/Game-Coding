package strategy;

/***
 * Concrete high jump behavior.
 */
public class HighJump implements JumpBehavior {

    @Override
    public void jump(Coordinates coor, int distance) {
        //jump 30 feet above
        coor.setyStart(coor.getyStart() - (distance + 30));
        coor.getChr().y = coor.getyStart();


    }

    @Override
    public void fall(Coordinates coor, int distance) {
        coor.setyStart(coor.getyStart() + (distance + 30));
        coor.getChr().y = coor.getyStart();

    }

    @Override
    public String getType() {
        return "high jump";
    }

}
