package decorator;

/**
 * B type Power
 * */
public class BPower extends PowerDecorator {

    public BPower() {
        super();
        setName("B");
    }

    /**
     * Inject power type
     * */
    public BPower(PowerUP powertype) {
        this();
        this.powertype = powertype;
    }

    /**
     * Wrap the score multiplier
     * */
    @Override
    public long multiplier() {
        return getPowertype().multiplier() * 5;
    }

    /**
     * Get name of Power
     * */
    @Override
    public String getName() {
        return "B";
    }


}
