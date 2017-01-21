package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public class NOK extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return "kr";
    }

    @Override
    public String name() {
        return "Norwegian krone";
    }

}
