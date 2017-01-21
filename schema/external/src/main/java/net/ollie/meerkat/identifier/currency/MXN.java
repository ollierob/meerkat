package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class MXN extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    MXN() {
    }

    @Override
    public String symbol() {
        return "$";
    }

    @Override
    public String name() {
        return "Mexican peso";
    }

    @Override
    public String uniqueSymbol() {
        return "Mex$";
    }

}
