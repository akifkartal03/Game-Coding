package decorator;

/**
* Concrete component score class.
* */
public class Score extends PowerUP {

    public Score() {
        setName("Total Score");
    }

    @Override
    public long multiplier() {
        return 1;
    }

}
