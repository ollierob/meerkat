package net.meerkat.identifier.currency.commodity;

import net.meerkat.identifier.currency.AbstractCurrencyIso;

/**
 *
 * @author ollie
 */
public class XAG extends AbstractCurrencyIso implements CommodityCurrencyIso {

    private static final long serialVersionUID = 1L;

    XAG() {
    }

    @Override
    public String name() {
        return "Silver (1oz)";
    }

}
