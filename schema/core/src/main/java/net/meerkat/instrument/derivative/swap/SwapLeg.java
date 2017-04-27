package net.meerkat.instrument.derivative.swap;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;

/**
 *
 * @author ollie
 */
public interface SwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends HasCurrencyIds {

    P payCurrency();

    R receiveCurrency();

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.payCurrency(), this.receiveCurrency());
    }

}
