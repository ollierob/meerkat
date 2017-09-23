package net.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class INR extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    INR() {
    }

    @Override
    public String symbol() {
        return "₹";
    }

    @Override
    public String name() {
        return "Indian rupee";
    }

}
