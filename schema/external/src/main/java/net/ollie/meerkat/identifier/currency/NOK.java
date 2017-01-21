package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class NOK extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    NOK() {
    }

    @Override
    public String symbol() {
        return "kr";
    }

    @Override
    public String name() {
        return "Norwegian krone";
    }

    @Override
    public String uniqueSymbol() {
        return "NOK";
    }

}
