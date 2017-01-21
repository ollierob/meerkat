package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class GBX extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    GBX() {
    }

    @Override
    public String symbol() {
        return "p";
    }

    @Override
    public String uniqueSymbol() {
        return "GBp";
    }

    @Override
    public String name() {
        return "Penny sterling";
    }

}
