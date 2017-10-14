package net.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class StubCurrencyId implements CurrencyId {

    private final String symbol;

    public StubCurrencyId(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String symbol() {
        return symbol;
    }

    @Override
    public String uniqueSymbol() {
        return symbol;
    }

}
