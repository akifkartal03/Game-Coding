package decorator;

/**
 * C type Power
 * */
public class CPower extends PowerDecorator {

    public CPower() {
        super();
        setName("C");


    }

	/**
	 * Inject power type
	 * */
    public CPower(PowerUP powertype) {
        this();
        this.powertype = powertype;
    }

	/**
	 * Wrap the score multiplier
	 * */
    @Override
    public long multiplier() {
        return getPowertype().multiplier() * 10;
    }

	/**
	 * Get name of Power.
	 * */
    @Override
    public String getName() {
        return "C";
    }


}
