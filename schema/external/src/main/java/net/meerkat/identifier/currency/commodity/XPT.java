package net.meerkat.identifier.currency.commodity;

import net.meerkat.identifier.currency.AbstractCurrencyIso;

/**
 *
 * @author ollie
 */
public class XPT extends AbstractCurrencyIso implements CommodityCurrencyIso {

    private static final long serialVersionUID = 1L;

    XPT() {
    }

    @Override
    public String name() {
        return "Platinum (1oz)";
    }

}
