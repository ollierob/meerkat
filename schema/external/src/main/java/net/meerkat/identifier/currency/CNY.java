package net.meerkat.identifier.currency;

/**
 *
 * @author Ollie
 */
public class CNY extends NationalCurrencyIso {

    private static final long serialVersionUID = 1L;

    CNY() {
    }

    @Override
    public String symbol() {
        return "¥"; //Or 元
    }

    @Override
    public String uniqueSymbol() {
        return "CN¥";
    }

    @Override
    public String name() {
        return "Renminbi";
    }

}
