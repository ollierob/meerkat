package net.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class EUR extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    EUR() {
    }

    @Override
    public String symbol() {
        return "€";
    }

    @Override
    public String name() {
        return "Euro";
    }

    @Override
    public String uniqueSymbol() {
        return "EUR";
    }

}
