package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class DKK extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    DKK() {
    }

    @Override
    public String symbol() {
        return "kr";
    }

    @Override
    public String name() {
        return "Danish krone";
    }

}
