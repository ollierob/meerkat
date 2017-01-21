package net.ollie.meerkat.identifier.currency.commodity;

import net.ollie.meerkat.identifier.currency.AbstractCurrencyIso;

/**
 *
 * @author ollie
 */
public class XAU extends AbstractCurrencyIso implements CommodityCurrencyIso {

    private static final long serialVersionUID = 1L;

    XAU() {
    }

    @Override
    public String name() {
        return "Gold (1oz)";
    }

}
