package decorator;


/**
 * A type Power
 * */
public class DPower extends PowerDecorator {

    public DPower() {
        super();
        setName("D");


    }

	/**
	 * Inject power type
	 * */
    public DPower(PowerUP powertype) {
        this();
        this.powertype = powertype;
    }
	/**
	 * Wrap the score multiplier.
	 * It doesn't change score multiplier
	 * */
    @Override
    public long multiplier() {
		//don't change score multiplier
        return getPowertype().multiplier();
    }
	/**
	 * Get name of Power.
	 * */
    @Override
    public String getName() {

        return "D";
    }


}
