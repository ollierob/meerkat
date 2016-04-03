package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class CHF extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "Fr";
    }

    @Override
    public String name() {
        return "Swiss franc";
    }

}
