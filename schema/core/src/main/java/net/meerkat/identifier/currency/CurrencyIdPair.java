package net.meerkat.identifier.currency;

import javax.annotation.Nonnull;

import net.coljate.set.Set;

/**
 *
 * @author ollie
 */
public interface CurrencyIdPair<B extends CurrencyId, C extends CurrencyId>
        extends CurrencyIds {

    @Nonnull
    B base();

    @Nonnull
    C counter();

    @Override
    default Set<? extends CurrencyId> values() {
        return Set.of(this.base(), this.counter());
    }

    @Override
    @Deprecated
    default CurrencyIdPair<B, C> currencyIds() {
        return this;
    }

    static <B extends CurrencyId, C extends CurrencyId> CurrencyIdPair<B, C> of(final B baseId, final C counterId) {
        throw new UnsupportedOperationException();
    }

}
