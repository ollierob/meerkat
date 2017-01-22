package net.meerkat.identifier.currency.commodity;

import net.meerkat.identifier.currency.CurrencyIso;

/**
 *
 * @author ollie
 */
public interface CommodityCurrencyIso extends CurrencyIso {

    @Override
    default String symbol() {
        return this.getClass().getSimpleName();
    }

    @Override
    default String uniqueSymbol() {
        return this.getClass().getSimpleName();
    }

    XAU XAU = new XAU();
    XAG XAG = new XAG();

}
