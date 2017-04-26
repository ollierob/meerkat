package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

/**
 *
 * @author ollie
 */
public interface CurrencyIdPair<B extends CurrencyId, C extends CurrencyId>
        extends HasCurrencyIds {

    @Nonnull
    B base();

    @Nonnull
    C counter();

    @Override
    default CurrencyIds currencyIds() {
        return CurrencyIds.of(this.base(), this.counter());
    }

    interface Untyped extends CurrencyIdPair<CurrencyId, CurrencyId> {

    }

}
