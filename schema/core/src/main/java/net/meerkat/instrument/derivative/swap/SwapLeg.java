package net.meerkat.instrument.derivative.swap;

import javax.annotation.Nonnull;

import net.meerkat.identifier.currency.CurrencyId;
import net.meerkat.identifier.currency.CurrencyIds;
import net.meerkat.identifier.currency.HasCurrencyIds;

/**
 *
 * @author ollie
 */
public interface SwapLeg<P extends CurrencyId, R extends CurrencyId>
        extends HasCurrencyIds {

    @Nonnull
    P payCurrency();

    @Nonnull
    R receiveCurrency();

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.payCurrency(), this.receiveCurrency());
    }

}
