package decorator;

/**
 * A type Power
 * */
public class APower extends PowerDecorator {

    public APower() {
        super();
        setName("A");


    }

    /**
     * Inject power type
     * */
    public APower(PowerUP powertype) {
        this();
        this.powertype = powertype;
    }

    /**
     * Wrap the score multiplier
     * */
    @Override
    public long multiplier() {
        return getPowertype().multiplier() * 2;
    }

    /**
     * Get name of Power.
     * */
    @Override
    public String getName() {
        return "A";
    }


}
