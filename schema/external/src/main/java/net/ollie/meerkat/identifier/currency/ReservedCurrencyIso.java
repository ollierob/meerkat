package net.ollie.meerkat.identifier.currency;

/**
 *
 * @author ollie
 */
public abstract class ReservedCurrencyIso extends AbstractCurrencyIso {

    private static final long serialVersionUID = 1L;

    @Override
    public String symbol() {
        return this.getClass().getSimpleName();
    }

    @Override
    public String uniqueSymbol() {
        return this.getClass().getSimpleName();
    }

}
