package net.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class PLN extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    PLN() {
    }

    @Override
    public String symbol() {
        return "zł";
    }

    @Override
    public String name() {
        return "Polish złoty";
    }

}
