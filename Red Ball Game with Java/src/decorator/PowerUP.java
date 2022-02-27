package decorator;


/**
 * Abstract Power Component Class
 * */
public abstract class PowerUP {
    private String name = "Unknown name";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     *Wrap over score multiplier
     * */
    public abstract long multiplier();

}
