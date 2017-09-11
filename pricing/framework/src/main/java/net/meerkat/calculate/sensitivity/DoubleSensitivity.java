package net.meerkat.calculate.sensitivity;

/**
 *
 * @author ollie
 */
public abstract class DoubleSensitivity implements Sensitivity {

    private final double value;

    protected DoubleSensitivity(double value) {
        this.value = value;
    }

    public double value() {
        return value;
    }

}
