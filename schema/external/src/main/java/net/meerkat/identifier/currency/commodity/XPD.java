package net.meerkat.identifier.currency.commodity;

import net.meerkat.identifier.currency.AbstractCurrencyIso;

/**
 *
 * @author ollie
 */
public class XPD extends AbstractCurrencyIso implements CommodityCurrencyIso {

    private static final long serialVersionUID = 1L;

    XPD() {
    }

    @Override
    public String name() {
        return "Palladium (1oz)";
    }

}
